package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserEntityObjectRequest;

public class RequestToUserMapper {
    public static User map(UserEntityObjectRequest entityUserEntityObjectRequest){
        User resultUser = new User();

        resultUser.setFirstName(entityUserEntityObjectRequest.getFirstName());
        resultUser.setLastName(entityUserEntityObjectRequest.getLastName());
        resultUser.setDateOfBirth(entityUserEntityObjectRequest.getDateOfBirth());
        resultUser.setEmail(entityUserEntityObjectRequest.getEmail());
        resultUser.setPassword(entityUserEntityObjectRequest.getPassword());

        return resultUser;
    }
}
