package Authentication.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Authentication.security.entity.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAll();
}
