package Authentication.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import Authentication.security.entity.Authority;
import Authentication.security.repository.AuthorityRepository;


@Repository
public class AuthorityDaoImpl implements AuthorityDao{

    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public Authority getAuthority(Long id) {
        return authorityRepository.findById(id).orElse(null);
    }


}