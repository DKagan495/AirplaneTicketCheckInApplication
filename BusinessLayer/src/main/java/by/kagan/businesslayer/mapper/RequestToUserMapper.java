package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.request.UserRequest;

public class RequestToUserMapper {
    public static User map(UserRequest entityUserRequest){
        User resultUser = new User();

        resultUser.setFirstName(entityUserRequest.getFirstName());
        resultUser.setLastName(entityUserRequest.getLastName());
        resultUser.setDateOfBirth(entityUserRequest.getDateOfBirth());
        resultUser.setEmail(entityUserRequest.getEmail());
        resultUser.setPassword(entityUserRequest.getPassword());

        return resultUser;
    }
}
