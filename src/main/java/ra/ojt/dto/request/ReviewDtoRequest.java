package ra.ojt.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDtoRequest {
    @NotNull(message = "User ID không được để trống")
    private Long userId;

    @NotNull(message = "Service ID không được để trống")
    private Long serviceId;

    @NotBlank(message = "Nội dung đánh giá không được để trống")
    @Size(max = 1000, message = "Nội dung không vượt quá 1000 ký tự")
    private String content;

    @NotNull(message = "Đánh giá không được để trống")
    @Min(value = 1, message = "Đánh giá phải từ 1 đến 5") // (★✰✰✰✰)
    @Max(value = 5, message = "Đánh giá phải từ 1 đến 5") // (★★★★★)
    private Integer rating;
}
