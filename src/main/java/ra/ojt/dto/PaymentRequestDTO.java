package ra.ojt.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
    @NotNull(message = "Mã đơn đặt là bắt buộc")
    private Long bookingId;

    @NotNull(message = "Số tiền là bắt buộc")
    @DecimalMin(value = "0.0", inclusive = false, message = "Số tiền phải lớn hơn 0")
    private BigDecimal amount;

    @NotBlank(message = "Phương thức thanh toán là bắt buộc")
    private String paymentMethod;

    @NotBlank(message = "Mã giao dịch là bắt buộc")
    private String transactionId;
}
