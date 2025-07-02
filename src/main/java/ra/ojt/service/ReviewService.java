package ra.ojt.service;

import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.ReviewDtoResponse;

import java.util.Map;

public interface ReviewService {
    Map<String, ReviewDtoResponse> newReview(ReviewDtoRequest request, Long userId, Long bookingId);
}
