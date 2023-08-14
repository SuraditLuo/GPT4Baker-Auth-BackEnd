package Authentication.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import Authentication.DTO.PatientDTO;
import Authentication.entity.*;
import Authentication.security.dto.UserAuthDTO;
import Authentication.security.dto.UserDTO;
import Authentication.security.entity.User;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(imports = Collectors.class)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    PatientDTO getPatientDTO(Patient patient);

    UserDTO getUserDTO(User user);

    List<UserDTO> getUserDTO(List<User> users);

    List<PatientDTO> getPatientDTO(List<Patient> patients);

    @Mapping(target = "authorities", expression = "java(user.getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    UserAuthDTO getUserAuthDTO(User user);

}
