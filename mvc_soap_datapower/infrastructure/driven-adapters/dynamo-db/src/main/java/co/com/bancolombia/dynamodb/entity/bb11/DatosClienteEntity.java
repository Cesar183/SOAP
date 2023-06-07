package co.com.bancolombia.dynamodb.entity.bb11;

import co.com.bancolombia.dynamodb.entity.IdentificacionClienteEntity;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class DatosClienteEntity {
    private IdentificacionClienteEntity identificacionCliente;
    private Integer cantidadDocumentosRecibidos;
    private String nombreCliente;
    private String tipoCliente;
    private List<DocumentoRequeridoEntity> documentoRequerido;

    public DatosClienteEntity() {
    }

    public DatosClienteEntity(IdentificacionClienteEntity identificacionCliente, Integer cantidadDocumentosRecibidos, String nombreCliente, String tipoCliente, List<DocumentoRequeridoEntity> documentoRequerido) {
        this.identificacionCliente = identificacionCliente;
        this.cantidadDocumentosRecibidos = cantidadDocumentosRecibidos;
        this.nombreCliente = nombreCliente;
        this.tipoCliente = tipoCliente;
        this.documentoRequerido = documentoRequerido;
    }

    @DynamoDbAttribute("identificacionCliente")
    public IdentificacionClienteEntity getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(IdentificacionClienteEntity identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    @DynamoDbAttribute("cantidadDocumentosRecibidos")
    public Integer getCantidadDocumentosRecibidos() {
        return cantidadDocumentosRecibidos;
    }

    public void setCantidadDocumentosRecibidos(Integer cantidadDocumentosRecibidos) {
        this.cantidadDocumentosRecibidos = cantidadDocumentosRecibidos;
    }

    @DynamoDbAttribute("nombreCliente")
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @DynamoDbAttribute("tipoCliente")
    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    @DynamoDbAttribute("documentoRequerido")
    public List<DocumentoRequeridoEntity> getDocumentoRequerido() {
        return documentoRequerido;
    }

    public void setDocumentoRequerido(List<DocumentoRequeridoEntity> documentoRequerido) {
        this.documentoRequerido = documentoRequerido;
    }

}
