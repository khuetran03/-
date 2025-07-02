package ra.ojt.service.imp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.ojt.config.enums.BookingStatus;
import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.ReviewDtoResponse;
import ra.ojt.entity.Booking;
import ra.ojt.entity.Review;
import ra.ojt.entity.User;
import ra.ojt.exception.ExistsException;
import ra.ojt.exception.NotAllowedException;
import ra.ojt.exception.NotFoundException;
import ra.ojt.mapper.ReviewMapper;
import ra.ojt.repository.BookingRepository;
import ra.ojt.repository.ReviewRepository;
import ra.ojt.repository.UserRepository;
import ra.ojt.service.ReviewService;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    @Override
    public Map<String,ReviewDtoResponse> newReview(ReviewDtoRequest request, Long userId, Long bookingId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new NotFoundException("Not found User"));
        Booking booking = bookingRepository.findByIdAndUserId(bookingId,userId)
                .orElseThrow(()-> new NotFoundException("Not found Booking"));
        if (reviewRepository.existsByBookingId(bookingId)) {
            throw new ExistsException("Review already exists");
        }else if (booking.getStatus() != BookingStatus.COMPLETED) {
            throw new NotAllowedException("Booking is not completed");
        }
        Review review = ReviewMapper.mapReviewDtoRequestToEntity(request);
        review.setBooking(booking);
        Review newReview;
        try{
           newReview = reviewRepository.save(review);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
        ReviewDtoResponse response = ReviewMapper.mapReviewToReviewDtoResponse(newReview);
        Map<String, ReviewDtoResponse> map = new HashMap<>();
        map.put("review", response);
        return map;
    }

}
