package co.com.bancolombia.model.bb12validado;
import co.com.bancolombia.model.bb12.Bb12;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
public class Bb12Validado {
    public Bb12 bb12;
    public String Observaciones;
    public boolean valido;
}
