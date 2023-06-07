package co.com.bancolombia.consumer.model.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class DocumentoRequerido {
    private String subTipoDocumento;
    private String codigoDocumento;
    private String observacion;
    private String esObligatorio;
    private String esEnviado;
}
