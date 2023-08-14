package Authentication.security.service;

import org.springframework.data.domain.Page;
import Authentication.security.entity.User;

public interface UserService {
    User save(User user);
//    Page<User> getUsers(Integer pageSize, Integer page);

//    User saveDoctorRole(User user);
    public User getUser(Long id);
}
