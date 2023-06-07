package co.com.bancolombia.dynamodb.entity.bb12;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class EventoEntity {
    private String indicadorEvento;
    private String fechaEvento;
    private String estadoEvento;
    private List<String> descripcionEstadoEvento;

    public EventoEntity() {
    }

    public EventoEntity(String indicadorEvento, String fechaEvento, String estadoEvento, List<String> descripcionEstadoEvento) {
        this.indicadorEvento = indicadorEvento;
        this.fechaEvento = fechaEvento;
        this.estadoEvento = estadoEvento;
        this.descripcionEstadoEvento = descripcionEstadoEvento;
    }

    @DynamoDbAttribute("indicadorEvento")
    public String getIndicadorEvento() {
        return indicadorEvento;
    }

    public void setIndicadorEvento(String indicadorEvento) {
        this.indicadorEvento = indicadorEvento;
    }

    @DynamoDbAttribute("fechaEvento")
    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    @DynamoDbAttribute("estadoEvento")
    public String getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    @DynamoDbAttribute("descripcionEstadoEvento")
    public List<String> getDescripcionEstadoEvento() {
        return descripcionEstadoEvento;
    }

    public void setDescripcionEstadoEvento(List<String> descripcionEstadoEvento) {
        this.descripcionEstadoEvento = descripcionEstadoEvento;
    }

}
