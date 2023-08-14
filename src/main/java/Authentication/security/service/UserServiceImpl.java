package Authentication.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import Authentication.DAO.PatientDao;
import Authentication.entity.Doctor;
import Authentication.entity.Patient;
import Authentication.repository.PatientRepository;
import Authentication.security.dao.AuthorityDao;
import Authentication.security.dao.UserDao;
import Authentication.security.entity.Authority;
import Authentication.security.entity.User;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;

    @Autowired
    PatientDao patientDao;
    @Autowired
    AuthorityDao authorityDao;
    @Autowired
    PatientRepository patientRepository;

    @Override
    @Transactional
    public User save(User user) {
        return userDao.save(user);
    }
    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
}
