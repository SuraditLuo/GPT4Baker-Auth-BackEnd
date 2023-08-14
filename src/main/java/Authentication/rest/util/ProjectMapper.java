package Authentication.rest.util;

import Authentication.rest.DTO.UserDto;
import Authentication.rest.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import Authentication.rest.entity.*;
import java.util.List;


@Mapper
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);
    UserDto getUserDto(User user);
    List<UserDto> getUserDto(List<User> users);
}
