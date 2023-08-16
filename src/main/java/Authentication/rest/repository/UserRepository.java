package Authentication.rest.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import Authentication.rest.entity.User;

import java.util.List;
public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAll();
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
