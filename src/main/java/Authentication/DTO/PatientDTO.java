package Authentication.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    Long id;
    String firstname;
    String lastname;
    String age;
    String address;
    String status;
    PatientDoctorDTO doctor;
    List<PatientCommentDTO> comments;
}