package co.com.bancolombia.model.bb12;
import co.com.bancolombia.model.cabecera.Cabecera;
import co.com.bancolombia.model.cliente.Cliente;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Bb12 {
    private Cabecera cabecera;
    private String numeroRadicado;
    private String version;
    private List<Cliente> clientes;
}
