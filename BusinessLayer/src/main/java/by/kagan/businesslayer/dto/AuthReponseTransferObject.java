package by.kagan.businesslayer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
//TODO: более общий идентификатор класса - в конец. Напр.:AuthTransferObjectResponse
public class AuthReponseTransferObject {
    private String token;
}
