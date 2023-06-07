package co.com.bancolombia.model.validacionesbb;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class ValidacionesBb {
    private boolean valido;
    private String mensaje;
}
