package ra.ojt.service;

import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.NewReviewResponse;


public interface ReviewService {
    NewReviewResponse newReview(ReviewDtoRequest request);
}
