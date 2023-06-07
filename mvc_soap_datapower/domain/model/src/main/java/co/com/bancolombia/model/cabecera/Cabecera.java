package co.com.bancolombia.model.cabecera;
import co.com.bancolombia.model.classification.Classification;
import co.com.bancolombia.model.destination.Destination;
import co.com.bancolombia.model.messagecontext.MessageContext;
import co.com.bancolombia.model.userid.UserId;
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
