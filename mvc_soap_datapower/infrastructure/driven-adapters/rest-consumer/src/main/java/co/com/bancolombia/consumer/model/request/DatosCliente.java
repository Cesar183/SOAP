package co.com.bancolombia.consumer.model.request;

import co.com.bancolombia.consumer.model.IdentificacionCliente;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DatosCliente {

    private IdentificacionCliente identificacionCliente;
    private Integer cantidadDocumentosRecibidos;
    private String nombreCliente;
    private String tipoCliente;
    private List<DocumentoRequerido> documentoRequerido;
}
