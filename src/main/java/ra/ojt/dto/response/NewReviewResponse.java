package ra.ojt.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NewReviewResponse {
    private Long userId;
    private Long serviceId;
}
