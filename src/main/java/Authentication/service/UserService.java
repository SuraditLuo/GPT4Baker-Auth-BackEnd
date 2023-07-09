package Authentication.service;

import Authentication.dto.UserDto;
import Authentication.entity.User;
import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}