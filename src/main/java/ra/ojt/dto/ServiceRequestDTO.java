package ra.ojt.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ra.ojt.config.enums.Duration;

import java.math.BigDecimal;

@Data
public class ServiceRequestDTO {
    @NotBlank(message = "Tên dịch vụ là bắt buộc")
    @Size(max = 64, message = "Tên dịch vụ không được vượt quá 64 ký tự")
    private String name;

    @NotBlank(message = "Mô tả dịch vụ là bắt buộc")
    @Size(max = 254, message = "Mô tả không được vượt quá 254 ký tự")
    private String description;

    @NotNull(message = "Giá dịch vụ là bắt buộc")
    @DecimalMin("0.0")
    @Digits(integer = 8, fraction = 2, message = "Định dạng giá không hợp lệ")
    private BigDecimal price;

    @NotNull(message = "Thời lượng dịch vụ là bắt buộc")
    private Duration duration;

    @NotNull(message = "Mã nhân viên là bắt buộc")
    private Long staffId;
}
