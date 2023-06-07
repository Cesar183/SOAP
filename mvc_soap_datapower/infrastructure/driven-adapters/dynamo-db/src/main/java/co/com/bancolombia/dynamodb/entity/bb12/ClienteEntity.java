package co.com.bancolombia.dynamodb.entity.bb12;

import co.com.bancolombia.dynamodb.entity.IdentificacionClienteEntity;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class ClienteEntity {

    private IdentificacionClienteEntity identificacionCliente;
    private List<DocumentoEntity> documentos;

    public ClienteEntity() {
    }

    public ClienteEntity(IdentificacionClienteEntity identificacionCliente, List<DocumentoEntity> documentos) {
        this.identificacionCliente = identificacionCliente;
        this.documentos = documentos;
    }

    @DynamoDbAttribute("identificacionCliente")
    public IdentificacionClienteEntity getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(IdentificacionClienteEntity identificacionCliente) {
        this.identificacionCliente = identificacionCliente;
    }

    @DynamoDbAttribute("documentos")
    public List<DocumentoEntity> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoEntity> documentos) {
        this.documentos = documentos;
    }
}
