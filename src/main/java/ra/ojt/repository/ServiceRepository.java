package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ojt.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findById(long id);
}
