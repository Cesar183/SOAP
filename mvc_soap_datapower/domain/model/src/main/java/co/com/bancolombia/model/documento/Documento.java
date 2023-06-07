package co.com.bancolombia.model.documento;
import co.com.bancolombia.model.evento.Evento;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode
public class Documento {
    private String codigoDocumento;
    private String subTipoDocumento;
    private String nombreArchivo;
    private List<Evento> eventos;
}
