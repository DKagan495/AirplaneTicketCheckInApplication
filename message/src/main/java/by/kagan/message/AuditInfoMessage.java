package by.kagan.message;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class AuditInfoMessage implements Serializable {

    @Id
    private String email;

    private String endpoint;

    private LocalDateTime requestTime;

    private int responseCode;
}
