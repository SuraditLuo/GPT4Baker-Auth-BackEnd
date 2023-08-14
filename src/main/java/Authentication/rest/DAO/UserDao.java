package Authentication.rest.DAO;

import Authentication.rest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDao {
    Page<User> getUser(Pageable pageRequest);
    Optional<User> findById(Long id);
    User save(User user);
}
