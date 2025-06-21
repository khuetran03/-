package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ojt.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByUserIdAndServiceId(Long userId, Long serviceId);
}
