package ra.ojt.mapper;

import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.ReviewDtoResponse;
import ra.ojt.entity.Review;

public class ReviewMapper {
    public static Review mapReviewDtoRequestToEntity(ReviewDtoRequest request){
        Review review = new Review();
        review.setRating(request.getRating());
        review.setComment(request.getComment());
        return review;
    }
    public static ReviewDtoResponse mapReviewToReviewDtoResponse(Review review){
        ReviewDtoResponse response = new ReviewDtoResponse();
        response.setReviewId(review.getId());
        response.setComment(review.getComment());
        response.setRating(review.getRating());
        response.setRateTime(review.getRateTime());
        response.setBookingId(review.getBooking().getId());
        response.setUserName(review.getBooking().getUser().getFullName());
        response.setServiceName(review.getBooking().getService().getName());
        return response;
    }
}
