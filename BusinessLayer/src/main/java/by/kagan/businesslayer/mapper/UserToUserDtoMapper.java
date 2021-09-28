package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserToUserDtoMapper {

    public static UserRequest map(User user){
        UserRequest resultEntityUserRequest = new UserRequest();
        resultEntityUserRequest.setFirstName(user.getFirstName());
        resultEntityUserRequest.setLastName(user.getLastName());
        resultEntityUserRequest.setDateOfBirth(user.getDateOfBirth());
        resultEntityUserRequest.setEmail(user.getEmail());
        resultEntityUserRequest.setPassword(user.getPassword());
        resultEntityUserRequest.setConfirmPassword(user.getPassword());
        resultEntityUserRequest.setAccountEnabled(user.isAccountEnabled());
        return resultEntityUserRequest;
    }

    public static User unMap(UserRequest entityUserRequest){
        User resultUser = new User();
        resultUser.setFirstName(entityUserRequest.getFirstName());
        resultUser.setLastName(entityUserRequest.getLastName());
        resultUser.setDateOfBirth(entityUserRequest.getDateOfBirth());
        resultUser.setEmail(entityUserRequest.getEmail());
        resultUser.setPassword(entityUserRequest.getPassword());
        resultUser.setAccountEnabled(entityUserRequest.isAccountEnabled());
        return resultUser;
    }
}
