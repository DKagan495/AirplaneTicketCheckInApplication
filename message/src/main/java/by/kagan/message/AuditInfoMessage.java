package by.kagan.message;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class AuditInfoMessage {

    @Id
    private String email;

    private String endpoint;

    private LocalDateTime requestTime;

    private String responseCode;
}
