package Authentication.rest.service;

import Authentication.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import Authentication.rest.DAO.UserDao;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
}
