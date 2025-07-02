package ra.ojt.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReviewDtoResponse {
    private Long reviewId;
    private String comment;
    private Integer rating;
    private LocalDateTime rateTime;
    private Long bookingId;
    private String userName;
    private String serviceName;
}
