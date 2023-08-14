package Authentication.rest.service;
import Authentication.rest.entity.User;
import java.util.List;

public interface UserService {
    List<User> getUsers();
}
