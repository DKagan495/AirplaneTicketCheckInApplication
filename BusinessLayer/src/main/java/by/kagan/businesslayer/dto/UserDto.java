package by.kagan.businesslayer.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private String email;
    private String password;
    private String confirmPassword;
}
