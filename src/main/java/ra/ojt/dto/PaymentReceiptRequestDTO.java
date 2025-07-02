package ra.ojt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentReceiptRequestDTO {
    @NotNull(message = "Booking ID là bắt buộc")
    private Long id;
}
