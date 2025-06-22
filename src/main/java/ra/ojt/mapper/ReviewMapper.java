package ra.ojt.mapper;

import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.entity.Review;

public class ReviewMapper {
    public static Review mapReviewDtoRequestToEntity(ReviewDtoRequest request){
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return review;
    }
}
