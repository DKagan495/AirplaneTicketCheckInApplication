package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserEntityObjectResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserToUserDtoMapper {

    public static UserEntityObjectResponse mapToResponse(User user){
        UserEntityObjectResponse resultResponse = new UserEntityObjectResponse();

        resultResponse.setFirstName(user.getFirstName());
        resultResponse.setLastName(user.getLastName());
        resultResponse.setDateOfBirth(user.getDateOfBirth());
        resultResponse.setEmail(user.getEmail());

        return resultResponse;
    }
}
