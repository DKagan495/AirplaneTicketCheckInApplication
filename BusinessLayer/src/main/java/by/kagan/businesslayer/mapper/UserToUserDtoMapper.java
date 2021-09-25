package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.auth.token.service.AccountAuthorizationService;
import by.kagan.businesslayer.domain.Role;
import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import by.kagan.businesslayer.repository.RoleRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserToUserDtoMapper {

    public static UserDto map(User user){
        UserDto resultUserDto = new UserDto();
        resultUserDto.setFirstName(user.getFirstName());
        resultUserDto.setLastName(user.getLastName());
        resultUserDto.setDateOfBirth(user.getDateOfBirth());
        resultUserDto.setEmail(user.getEmail());
        resultUserDto.setPassword(user.getPassword());
        resultUserDto.setConfirmPassword(user.getPassword());
        resultUserDto.setAccountEnabled(user.isAccountEnabled());
        return resultUserDto;
    }

    public static User unMap(UserDto userDto){
        User resultUser = new User();
        resultUser.setFirstName(userDto.getFirstName());
        resultUser.setLastName(userDto.getLastName());
        resultUser.setDateOfBirth(userDto.getDateOfBirth());
        resultUser.setEmail(userDto.getEmail());
        resultUser.setPassword(userDto.getPassword());
        resultUser.setAccountEnabled(userDto.isAccountEnabled());;
        return resultUser;
    }
}
