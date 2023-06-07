package co.com.bancolombia.model.messagecontext;
import co.com.bancolombia.model.property.Property;
import lombok.*;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageContext {
    private List<Property> property;
}