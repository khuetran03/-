package ra.ojt.service.imp;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.RaResponse;
import ra.ojt.entity.Review;
import ra.ojt.entity.User;
import ra.ojt.exception.ExistsException;
import ra.ojt.exception.NotFoundException;
import ra.ojt.mapper.ReviewMapper;
import ra.ojt.repository.ReviewRepository;
import ra.ojt.repository.ServiceRepository;
import ra.ojt.repository.UserRepository;
import ra.ojt.service.ReviewService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ServiceRepository serviceRepository;
    @Override
    public RaResponse newReview(ReviewDtoRequest request) {
        if (reviewRepository.existsByUserIdAndServiceId(request.getUserId(), request.getServiceId())) {
            throw new ExistsException("Review already exists");
        }
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(()-> new NotFoundException("Not found User"));
        ra.ojt.entity.Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(()-> new NotFoundException("Not found Service"));
        Review review = ReviewMapper.mapReviewDtoRequestToEntity(request);
        review.setUser(user);
        review.setService(service);
        Review newReview;
        try{
            newReview = reviewRepository.save(review);
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }

        RaResponse response = new RaResponse();
        response.setType("Review");
        response.setId(newReview.getId());
        response.setData(Map.of(
                "userId",newReview.getUser().getId(),
                "serviceId",newReview.getService().getId(),
                "rating",newReview.getRating(),
                "content",newReview.getContent(),
                "rateTime",newReview.getRateTime()
        ));
        return response;
    }

}
