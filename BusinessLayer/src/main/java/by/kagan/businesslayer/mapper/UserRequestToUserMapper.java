package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring")
public interface UserRequestToUserMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "tickets", ignore = true),
            @Mapping(target = "role", ignore = true),
            @Mapping(target = "enabled", ignore = true),
            @Mapping(target = "deleted", ignore = true)
    })
    User map(UserRequest request);
}
