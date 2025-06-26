package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ra.ojt.config.enums.BookingStatus;
import ra.ojt.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking findById(long id);
//    @Query("select count(b) from Booking b where b.status = ra.ojt.config.enums.BookingStatus.COMPLETED and b.user.id = :userId and b.service = :serviceId")
//    Long countCompleteBookingByUserIdAndServiceId(@Param("userId") Long userId, @Param("serviceId") Long serviceId);
    @Query("select count(b) from Booking b where b.status = :status and b.user.id = :userId and b.service.id = :serviceId")
    Long countByStatusAnduserIddAndserviceId(@Param("status") BookingStatus status,@Param("userId") Long userId, @Param("serviceId") Long serviceId);

}
