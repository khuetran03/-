package ra.ojt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.ojt.dto.request.ReviewDtoRequest;
import ra.ojt.dto.response.ReviewDtoResponse;
import ra.ojt.service.ReviewService;

import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/bookingmassage")
public class ReviewController {

    /*
     * Gửi đánh giá cho một dịch vụ.
     *
     * @param request nội dung đánh giá (userId, serviceId, số sao, nhận xét)
     *
     * @return đánh giá đã lưu
     */

    private final ReviewService reviewService;
    @PostMapping("/user/{userId}/bookings/{bookingId}/new-review")
    public ResponseEntity<Map<String,ReviewDtoResponse>> newReview(@Valid @RequestBody ReviewDtoRequest request,
                                                @PathVariable Long bookingId,@PathVariable Long userId) {
        Map<String,ReviewDtoResponse> response = reviewService.newReview(request, userId, bookingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
     * Lấy danh sách đánh giá theo ID dịch vụ.
     *
     * @param serviceId ID dịch vụ
     * @return danh sách đánh giá
     */
}
