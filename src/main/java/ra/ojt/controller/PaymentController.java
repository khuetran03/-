package ra.ojt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.ojt.dto.PaymentReceiptRequestDTO;
import ra.ojt.service.imp.PaymentReceiptService;

import java.util.Random;

@RestController
@RequiredArgsConstructor
@RequestMapping("/receipt")
public class PaymentController {
    /*
     * Tiến hành thanh toán.
     *
     * @param request thông tin thanh toán (số thẻ, ví điện tử...)
     * @return xác nhận thanh toán hoặc lỗi
     */

    /*
     * Gửi biên lai thanh toán sau khi giao dịch thành công.
     *
     * @param transactionId mã giao dịch
     * @return biên lai dưới dạng email hoặc dữ liệu trả về
     */
    private final PaymentReceiptService paymentReceiptService;

    @PostMapping("/send-push")
    public ResponseEntity<String> sendPush(@RequestBody @Valid
                                           PaymentReceiptRequestDTO request) {
        paymentReceiptService.sendPushNotification(request.getId());
        return ResponseEntity.ok("Đã gửi thông báo push (mock)");
    }
    // Gửi form lên console check format, check dữ liệu.

    @PostMapping("/send-email")
    public ResponseEntity<String> sendReceiptEmail(@RequestBody @Valid
                                                   PaymentReceiptRequestDTO request) {
        paymentReceiptService.sendReceiptMail(request.getId());
        return ResponseEntity.ok("Biên lai đã được gửi qua Email");
    }

    // Gửi biên lai thanh toán thành công qua Email
}
