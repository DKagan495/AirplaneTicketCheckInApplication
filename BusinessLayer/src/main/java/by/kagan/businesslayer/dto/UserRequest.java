package by.kagan.businesslayer.dto;

import by.kagan.businesslayer.validator.annotaion.Match;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Match(field = "password", fieldMatch = "confirmPassword", message = "Password not matches with confirmation")
public class UserRequest {

    @NotEmpty(message = "Field firstName cannot be empty")
    @Size(min = 2, max = 21, message = "Length of the firstName cannot be <2 & >21")
    private String firstName;

    @NotEmpty(message = "Field lastName cannot be empty")
    @Size(min = 2, max = 21, message = "Length of the lastName cannot be <2 & >21")
    private String lastName;

    private Date dateOfBirth;

    @NotEmpty(message = "Field email cannot be empty")
    @Email(message = "Email should be email")
    private String email;

    @NotEmpty(message = "Field password cannot be empty")
    @Size(min = 8, message = "Password cannot be less than 8 symbols")
    private String password;

    @JsonProperty("confirmPassword")
    @NotEmpty(message = "Field confirmPassword cannot be empty")
    private String confirmPassword;

    @JsonIgnore
    private boolean isAccountEnabled;

}

