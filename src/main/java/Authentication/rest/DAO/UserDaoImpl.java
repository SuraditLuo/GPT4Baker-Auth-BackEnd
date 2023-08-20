package Authentication.rest.DAO;

import Authentication.rest.entity.User;
import Authentication.rest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> getUser(Pageable pageRequest) {
        return userRepository.findAll(pageRequest);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
