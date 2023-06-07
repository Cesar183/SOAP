package co.com.bancolombia.model.destination;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Destination {
    private String name;
    private String namespace;
    private String operation;
}