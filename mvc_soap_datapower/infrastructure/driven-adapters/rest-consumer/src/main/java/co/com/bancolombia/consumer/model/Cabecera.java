package co.com.bancolombia.consumer.model;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Cabecera {
    private String systemId;
    private String messageId;
    private ZonedDateTime timestamp;
    private MessageContext messageContext;
    private UserId userId;
    private Destination destination;
    private List<Classification> classifications;


}
