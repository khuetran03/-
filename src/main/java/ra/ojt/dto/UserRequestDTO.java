package ra.ojt.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import ra.ojt.config.enums.UserStatus;

@Data
public class UserRequestDTO {
    @NotBlank(message = "Họ tên là bắt buộc")
    @Size(max = 64, message = "Họ tên không được vượt quá 64 ký tự")
    private String fullName;

    @NotBlank(message = "Email là bắt buộc")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Mật khẩu là bắt buộc")
    private String password;

    @Size(max = 15, message = "Số điện thoại không được vượt quá 15 ký tự")
    private String phone;

    @NotBlank(message = "Trạng thái là bắt buộc")
    private UserStatus userStatus;
}
