package by.kagan.businesslayer.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String email;

}
