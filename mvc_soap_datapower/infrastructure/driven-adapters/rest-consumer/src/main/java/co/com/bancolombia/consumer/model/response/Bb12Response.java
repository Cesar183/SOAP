package co.com.bancolombia.consumer.model.response;

import co.com.bancolombia.consumer.model.Cabecera;
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
public class Bb12Response {
    private Cabecera cabecera;
    private String numeroRadicado;
    private String version;
    private List<Cliente> clientes;

}