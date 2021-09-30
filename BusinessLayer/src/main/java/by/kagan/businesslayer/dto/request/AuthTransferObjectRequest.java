package by.kagan.businesslayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//TODO: упростить название классов-dto
public class AuthTransferObjectRequest {
    private String email;
    private String password;
}
