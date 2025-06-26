package ra.ojt.service;

import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.RaResponse;

public interface ReviewService {
    RaResponse newReview(ReviewDtoRequest request, Long userId, Long bookingId);
}
