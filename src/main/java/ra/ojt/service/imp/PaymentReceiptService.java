package ra.ojt.service.imp;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import ra.ojt.config.enums.PaymentStatus;
import ra.ojt.entity.Booking;
import ra.ojt.entity.Payment;
import ra.ojt.exception.BusinessException;
import ra.ojt.exception.ResourceNotFoundException;
import ra.ojt.repository.BookingRepository;
import ra.ojt.repository.PaymentRepository;
import ra.ojt.service.PaymentReceiptServiceImp;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PaymentReceiptService implements PaymentReceiptServiceImp {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    //    Gửi thông báo push mock ra console.
    public void sendPushNotification(Long bookingId) {
        Payment payment = getCompletedPayment(bookingId);
        Booking booking = payment.getBooking();

        String message = String.format("""
                        ━━━━━━━━━━━━━━━━━━━━━━
                                領収書（Receipt）
                        ━━━━━━━━━━━━━━━━━━━━━━
                        
                        【注文情報】
                        注文番号　　　: #%d
                        予約日時　　　: %s ～ %s
                        サービス　　　: %s
                        担当スタッフ　: %s
                        
                        【お客様情報】
                        氏名　　　　　: %s
                        メール　　　　: %s
                        
                        【お支払い情報】
                        支払金額　　　: %,d円
                        支払方法　　　: %s
                        支払ステータス: %s
                        支払日時　　　: %s
                        
                        ━━━━━━━━━━━━━━━━━━━━━━
                        ご利用ありがとうございました。
                        またのご予約をお待ちしております。
                        ━━━━━━━━━━━━━━━━━━━━━━
                        """,
                booking.getId(),
                booking.getStartAt(),
                booking.getEndAt(),
                booking.getService().getName(),
                booking.getStaff().getFullName(),
                booking.getUser().getFullName(),
                booking.getUser().getEmail(),
                payment.getAmount().intValue(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getPaidAt()
        );
        //Đây là gửi push
        System.out.println("PUSH NOTIFICATION:");
        System.out.println("To: " + booking.getUser().getFullName());
        System.out.println("Message: \n" + message);
    }
//    Gửi thông báo với format đơn giản
//    Đối với sử dụng thông báo này để kiểm tra thông tin thanh toán có đúng hay không

    /**
     * Gửi biên lai qua email sử dụng template HTML
     * Chỉ gửi nếu trạng thái thanh toán là COMPLETED
     */
    public void sendReceiptMail(Long bookingId) {
        String codePass = generationVerificationCode();
//        Gửi biên lai qua email dưới dạng HTML.
        Payment payment = getCompletedPayment(bookingId);
        Booking booking = payment.getBooking();
        Context context = new Context();
        context.setVariable("booking", booking);
        context.setVariable("payment", payment);
        context.setVariable("codePass", codePass);
//        Thêm logo
        context.setVariable("logoUrl", "https://upload.wikimedia.org/wikipedia/commons/b/ba/Logo-Rikkei.png");
//        Render template biên lai HTML từ Thymeleaf
        String html = templateEngine.process("receipt-templates", context);
        try {
//            Gửi email sử dụng JavaMailSender
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(booking.getUser().getEmail());
            helper.setSubject("マッサージ予約領収書");
            helper.setText(html, true); // true = gửi dưới dạng HTML
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi gửi biên lai: " + e.getMessage());// レシート送信エラー
        }

    }

    //    Lấy payment đã COMPLETED từ bookingId, đồng thời kiểm tra hợp lệ.
    private Payment getCompletedPayment(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn không tồn tại (Not found)")); //注文番号が存在しません

        Payment payment = paymentRepository.findByBooking(booking)
                .orElseThrow(() -> new BusinessException("Thông tin thanh toán chưa được đăng ký hoặc không chính xác."));
// 支払情報が未登録、または不正な状態です

        if (payment.getPaymentStatus() == null || payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new BusinessException("Trạng thái thanh toán chưa hoàn tất"); // 支払ステータスが未完了です
        }

        return payment;
    }
    public String generationVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(1000000);
        return String.valueOf(code);
    }

}
