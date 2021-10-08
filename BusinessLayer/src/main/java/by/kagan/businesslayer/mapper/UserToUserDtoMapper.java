package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.response.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserToUserDtoMapper {
    UserDto map(User user);
}
