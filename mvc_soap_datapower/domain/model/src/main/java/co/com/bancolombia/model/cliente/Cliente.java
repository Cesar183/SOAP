package co.com.bancolombia.model.cliente;
import co.com.bancolombia.model.documento.Documento;
import co.com.bancolombia.model.identificacioncliente.IdentificacionCliente;
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
public class Cliente {
    private IdentificacionCliente identificacionCliente;
    private List<Documento> documentos;
}
