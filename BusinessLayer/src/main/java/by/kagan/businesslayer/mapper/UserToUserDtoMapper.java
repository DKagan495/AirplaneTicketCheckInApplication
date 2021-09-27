package by.kagan.businesslayer.mapper;

import by.kagan.businesslayer.domain.User;
import by.kagan.businesslayer.dto.UserDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
// TODO: использовать MapStruct. Осмысленное название методов
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
        resultUserDto.setRole(user.getRole());
        return resultUserDto;
    }

    public static User unMap(UserDto userDto){
        User resultUser = new User();
        resultUser.setFirstName(userDto.getFirstName());
        resultUser.setLastName(userDto.getLastName());
        resultUser.setDateOfBirth(userDto.getDateOfBirth());
        resultUser.setEmail(userDto.getEmail());
        resultUser.setPassword(userDto.getPassword());
        resultUser.setAccountEnabled(userDto.isAccountEnabled());
        resultUser.setRole(userDto.getRole());
        return resultUser;
    }
}
