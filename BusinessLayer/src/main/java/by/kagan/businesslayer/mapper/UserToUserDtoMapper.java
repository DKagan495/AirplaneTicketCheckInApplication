package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.response.UserDto;

public class UserToUserDtoMapper {

    public static UserDto mapToResponse(User user){
        UserDto resultResponse = new UserDto();

        resultResponse.setFirstName(user.getFirstName());
        resultResponse.setLastName(user.getLastName());
        resultResponse.setDateOfBirth(user.getDateOfBirth());
        resultResponse.setEmail(user.getEmail());

        return resultResponse;
    }
}
