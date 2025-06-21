package ra.ojt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.RaResponse;
import ra.ojt.service.ReviewService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookingmassage/review")
public class ReviewController {

    /*
     * Gửi đánh giá cho một dịch vụ.
     *
     * @param request nội dung đánh giá (userId, serviceId, số sao, nhận xét)
     *
     * @return đánh giá đã lưu
     */
//    return
//    {
//        "type": "Review",
//            "id": 7,
//            "data": {
//                      "serviceId": 2,
//                      "rating": 5,
//                      "rateTime": "2025-06-20T21:57:17.3988826",
//                      "userId": 3,
//                      "content": "content 1"
//                  }
//    }
    //2025.06.18 Vinh_HD
    private final ReviewService reviewService;
    @PostMapping("/new-review")
    public ResponseEntity<RaResponse> newReview(@Valid @RequestBody ReviewDtoRequest request) {
        RaResponse response = reviewService.newReview(request);
        return ResponseEntity.ok(response);
    }

    /*
     * Lấy danh sách đánh giá theo ID dịch vụ.
     *
     * @param serviceId ID dịch vụ
     * @return danh sách đánh giá
     */

}
