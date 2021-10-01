package by.kagan.businesslayer.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "Field firstName cannot be empty")
    @NotNull(message = "Field firstName is null")
    @Size(min = 2, max = 21, message = "Length of the firstName cannot be <2 & >21")
    private String firstName;

    @NotBlank(message = "Field lastName cannot be empty")
    @NotNull(message = "Field lastName is null")
    @Size(min = 2, max = 21, message = "Length of the lastName cannot be <2 & >21")
    private String lastName;

    private Date dateOfBirth;

    @NotBlank(message = "Field email cannot be empty")
    @NotNull(message = "Field email is null")
    @Email(message = "Email should be email")
    private String email;

    @NotBlank(message = "Field password cannot be empty")
    @NotNull(message = "Field password is null")
    @Size(min = 8, message = "Password cannot be less than 8 symbols")
    private String password;
}

