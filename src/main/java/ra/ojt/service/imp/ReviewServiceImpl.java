package ra.ojt.service.imp;


import org.springframework.stereotype.Service;
import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.NewReviewResponse;
import ra.ojt.service.ReviewService;
@Service

public class ReviewServiceImpl implements ReviewService {

    @Override
    public NewReviewResponse newReview(ReviewDtoRequest request) {
        return null;
    }
}
