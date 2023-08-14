package Authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Authentication.entity.Vaccine;

import java.util.List;
public interface VaccineRepository extends JpaRepository<Vaccine,Long>{
    List<Vaccine> findAll();
}
