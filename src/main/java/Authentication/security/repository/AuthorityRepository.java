package Authentication.security.repository;


import org.springframework.data.repository.CrudRepository;
import Authentication.security.entity.Authority;
import java.util.List;

public interface AuthorityRepository extends CrudRepository<Authority, Long> {
    List<Authority> findAll();
}
