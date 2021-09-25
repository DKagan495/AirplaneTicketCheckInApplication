package by.kagan.businesslayer.dto;

import by.kagan.businesslayer.domain.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean isAccountEnabled;
    @JsonIgnore
    private Role role;
}
