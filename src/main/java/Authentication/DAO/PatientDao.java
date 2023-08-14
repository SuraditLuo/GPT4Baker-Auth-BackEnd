package Authentication.DAO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import Authentication.entity.Patient;

import java.util.Optional;

public interface PatientDao {
    Integer getPatientSize();
    Page<Patient> getPatients(Integer pageSize, Integer page);
    Patient getPatients(Long id);

    Patient save(Patient patient);
    Page<Patient> getPatients(String name, Pageable page);

    Optional<Patient> findById(Long id);
}
