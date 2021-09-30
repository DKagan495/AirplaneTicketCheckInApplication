package by.kagan.businesslayer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserEntityObjectResponse {

    private String firstName;

    private String lastName;

    private Date dateOfBirth;

    private String email;

}
