package Authentication.security.dao;


import Authentication.security.entity.Authority;

public interface AuthorityDao {
    Authority getAuthority(Long id);
}