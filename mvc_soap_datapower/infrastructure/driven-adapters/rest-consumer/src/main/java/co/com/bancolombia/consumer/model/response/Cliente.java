package co.com.bancolombia.consumer.model.response;

import co.com.bancolombia.consumer.model.IdentificacionCliente;
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
public class Cliente {
    private IdentificacionCliente identificacionCliente;
    private List<Documento> documentos;

}
