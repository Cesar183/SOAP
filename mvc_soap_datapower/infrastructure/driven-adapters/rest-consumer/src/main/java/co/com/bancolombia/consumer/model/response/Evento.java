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
public class Evento {
    private String indicadorEvento;
    private String fechaEvento;
    private String estadoEvento;
    private List<String> descripcionEstadoEvento;
}
