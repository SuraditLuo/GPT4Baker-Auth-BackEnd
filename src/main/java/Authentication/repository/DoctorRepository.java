package Authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import Authentication.entity.Doctor;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Long>{
    List<Doctor> findAll();
    //Page<Doctor> findByInCharge_Patient_FirstnameIgnoreCaseContainingOrInCharge_Patient_LastnameIgnoreCaseContaining(String firstName, String lastName, Pageable pageRequest);

}
