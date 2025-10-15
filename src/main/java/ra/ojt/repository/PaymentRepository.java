package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ojt.entity.Booking;
import ra.ojt.entity.Payment;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByBooking(Booking booking);
}
