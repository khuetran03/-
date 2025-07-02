package ra.ojt.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingRequestDTO {
    @NotNull(message = "User ID bắt buộc")
    private Long userId;

    @NotNull(message = "Service ID bắt buộc")
    private Long serviceId;

    @NotNull(message = "Staff ID bắt buộc")
    private Long staffId;

    @NotNull(message = "Start time bắt buộc")
    private LocalDateTime startAt;

    @NotNull(message = "End time bắt buộc")
    private LocalDateTime endAt;
}
