package ra.ojt.service.imp;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ra.ojt.config.enums.PaymentStatus;
import ra.ojt.entity.Booking;
import ra.ojt.entity.Payment;
import ra.ojt.repository.BookingRepository;
import ra.ojt.repository.PaymentRepository;
import ra.ojt.exception.BusinessException;
import ra.ojt.exception.ResourceNotFoundException;
import ra.ojt.service.PaymentReceiptServiceImp;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentReceiptService implements PaymentReceiptServiceImp {
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;
    private final JavaMailSender mailSender;

    public String generateReceiptMessage(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn không tồn tại (Not found)")); //注文番号が存在しません

        Optional<Payment> optionalPayment = paymentRepository.findByBooking(booking);
        if (optionalPayment.isEmpty()) {
            throw new BusinessException("Thông tin thanh toán chưa được đăng ký hoặc không chính xác."); // 支払情報が未登録、または不正な状態です
        }

        Payment payment = optionalPayment.get();

        if (payment.getPaymentStatus() == null || payment.getPaymentStatus() != PaymentStatus.COMPLETED) {
            throw new BusinessException("Trạng thái thanh toán chưa hoàn tất"); // 支払ステータスが未完了です
        }

        return String.format("""
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
    }

    public void sendReceiptMail(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn không tồn tại (Not found)")); // 注文番号が存在しません

        String content = generateReceiptMessage(bookingId);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(booking.getUser().getEmail());
            message.setSubject("Biên lai lịch Massage"); // マッサージ予約領収書
            message.setText(content);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi gửi biên lai: " + e.getMessage());// レシート送信エラー
        }
    }
    public void sendPushNotification(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Mã đơn không tồn tại (not found) ")); // 注文番号が存在しません
        String content = generateReceiptMessage(bookingId);
        //Đây là gửi push
        System.out.println("PUSH NOTIFICATION:");
        System.out.println("To: " + booking.getUser().getFullName());
        System.out.println("Message: \n" + content);
    }
}
