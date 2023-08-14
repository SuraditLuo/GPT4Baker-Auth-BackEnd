package Authentication.security.dao;


import org.springframework.data.domain.Page;
import Authentication.security.entity.User;

import java.util.Optional;

public interface UserDao {
    User getUser(Long id);
    User save(User user);
    Optional<User> findById(Long id);
    Page<User> getUsers(Integer pageSize, Integer page);
}