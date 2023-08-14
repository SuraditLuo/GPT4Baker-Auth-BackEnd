package Authentication.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import Authentication.entity.Patient;

public interface PatientService {
    Integer getPatientSize();

    Page<Patient> getPatients(Integer pageSize, Integer page);

    Patient getPatient(Long id);
    Patient saveDoctor(Patient patient);
    Page<Patient> getPatients(String name, Pageable pageable);

    Patient saveVaccine(Patient patient);
}
