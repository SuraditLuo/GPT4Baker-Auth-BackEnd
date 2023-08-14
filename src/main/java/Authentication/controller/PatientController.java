package Authentication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import Authentication.entity.Patient;
import Authentication.service.PatientService;
import Authentication.util.ProjectMapper;

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("patient/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {
        Patient output = patientService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

}
