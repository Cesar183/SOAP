package co.com.bancolombia.model.bb11;
import co.com.bancolombia.model.cabecera.Cabecera;
import co.com.bancolombia.model.datoscliente.DatosCliente;
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
public class Bb11 {
    private Cabecera cabecera;
    private String numeroRadicado;
    private String numeroRadicadoDos;
    private String fechaSolicitud;
    private Integer version;
    private List<DatosCliente> datosCliente;
}
