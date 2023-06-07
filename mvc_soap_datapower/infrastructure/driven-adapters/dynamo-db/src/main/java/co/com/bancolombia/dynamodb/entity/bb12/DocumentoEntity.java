package co.com.bancolombia.dynamodb.entity.bb12;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class DocumentoEntity {
    private String codigoDocumento;
    private String subTipoDocumento;
    private String nombreArchivo;
    private List<EventoEntity> eventos;

    public DocumentoEntity() {
    }

    public DocumentoEntity(String codigoDocumento, String subTipoDocumento, String nombreArchivo, List<EventoEntity> eventos) {
        this.codigoDocumento = codigoDocumento;
        this.subTipoDocumento = subTipoDocumento;
        this.nombreArchivo = nombreArchivo;
        this.eventos = eventos;
    }

    @DynamoDbAttribute("codigoDocumento")
    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    @DynamoDbAttribute("subTipoDocumento")
    public String getSubTipoDocumento() {
        return subTipoDocumento;
    }

    public void setSubTipoDocumento(String subTipoDocumento) {
        this.subTipoDocumento = subTipoDocumento;
    }

    @DynamoDbAttribute("nombreArchivo")
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @DynamoDbAttribute("eventos")
    public List<EventoEntity> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoEntity> eventos) {
        this.eventos = eventos;
    }
}
