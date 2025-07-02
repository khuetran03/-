package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.ojt.entity.Booking;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    @Query("select b from Booking b where b.id = :id and b.user.id = :userId")
    Optional<Booking> findByIdAndUserId(@Param("id") long id,@Param("userId") long userId);

}
