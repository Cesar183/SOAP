package co.com.bancolombia.consumer.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Documento {
    private String codigoDocumento;
    private String subTipoDocumento;
    private String nombreArchivo;
    private List<Evento> eventos;

}
