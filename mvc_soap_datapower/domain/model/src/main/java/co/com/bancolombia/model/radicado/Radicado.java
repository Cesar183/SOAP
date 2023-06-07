package co.com.bancolombia.model.radicado;
import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.model.bb12.Bb12;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Radicado {
    String numeroRadicado;
    Integer version;
    String estadoBb11;
    String observacionesBb11;
    String estadoBb12;
    String observacionesbb12;
    Bb11 bb11;
    Bb12 bb12Informar;
    Bb12 bb12Notificar;
}
