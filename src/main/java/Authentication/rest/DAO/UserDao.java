package Authentication.rest.DAO;

import Authentication.rest.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UserDao {
    User getUser(Long id);
}
