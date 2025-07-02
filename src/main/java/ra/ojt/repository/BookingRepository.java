package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ojt.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
