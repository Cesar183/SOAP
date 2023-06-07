package co.com.bancolombia.model.evento;
import lombok.Builder;
import lombok.AllArgsConstructor;
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
