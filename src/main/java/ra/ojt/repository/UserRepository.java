package ra.ojt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.ojt.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
