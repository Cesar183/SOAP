package co.com.bancolombia.model.property;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Property {
    private String key;
    private String value;
}
