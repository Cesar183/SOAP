package co.com.bancolombia.model.datoscliente;

import co.com.bancolombia.model.documentorequerido.DocumentoRequerido;
import co.com.bancolombia.model.identificacioncliente.IdentificacionCliente;
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
public class DatosCliente {

    private IdentificacionCliente identificacionCliente;
    private Integer cantidadDocumentosRecibidos;
    private String nombreCliente;
    private String tipoCliente;
    private List<DocumentoRequerido> documentoRequerido;
}
