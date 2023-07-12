package Authentication.service;

import Authentication.dto.UserDto;
import Authentication.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

}