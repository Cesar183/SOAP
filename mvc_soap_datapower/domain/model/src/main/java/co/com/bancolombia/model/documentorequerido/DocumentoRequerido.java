package co.com.bancolombia.model.documentorequerido;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class DocumentoRequerido {
    private String subTipoDocumento;
    private String codigoDocumento;
    private String observacion;
    private String esObligatorio;
    private String esEnviado;
}
