package co.com.bancolombia.consumer.model.request;


import co.com.bancolombia.consumer.model.Cabecera;
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
public class Bb11Request {
    private Cabecera cabecera;
    private String numeroRadicado;
    private String numeroRadicadoDos;
    private String fechaSolicitud;
    private Integer version;
    private List<DatosCliente> datosCliente;
}
