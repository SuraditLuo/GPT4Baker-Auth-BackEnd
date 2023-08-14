package Authentication.service;

import org.springframework.data.domain.Page;
import Authentication.entity.Doctor;

public interface DoctorService {
    Integer getDoctorSize();

    Page<Doctor> getDoctors(Integer pageSize, Integer page);

    Doctor getDoctor(Long id);

    Doctor save(Doctor doctor);
    /*Page<Doctor> getDoctors(String name, Pageable pageable);*/

    void removeDoctor(Long id);
}
