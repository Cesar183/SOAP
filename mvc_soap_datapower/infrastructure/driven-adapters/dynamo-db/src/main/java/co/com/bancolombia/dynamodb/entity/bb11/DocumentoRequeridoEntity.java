package co.com.bancolombia.dynamodb.entity.bb11;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class DocumentoRequeridoEntity {
    private String subTipoDocumento;
    private String codigoDocumento;
    private String observacion;
    private String esObligatorio;
    private String esEnviado;

    public DocumentoRequeridoEntity() {
    }

    public DocumentoRequeridoEntity(String subTipoDocumento, String codigoDocumento, String observacion, String esObligatorio, String esEnviado) {
        this.subTipoDocumento = subTipoDocumento;
        this.codigoDocumento = codigoDocumento;
        this.observacion = observacion;
        this.esObligatorio = esObligatorio;
        this.esEnviado = esEnviado;
    }

    @DynamoDbAttribute("subTipoDocumento")
    public String getSubTipoDocumento() {
        return subTipoDocumento;
    }

    public void setSubTipoDocumento(String subTipoDocumento) {
        this.subTipoDocumento = subTipoDocumento;
    }

    @DynamoDbAttribute("codigoDocumento")
    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    @DynamoDbAttribute("observacion")
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @DynamoDbAttribute("esObligatorio")
    public String getEsObligatorio() {
        return esObligatorio;
    }

    public void setEsObligatorio(String esObligatorio) {
        this.esObligatorio = esObligatorio;
    }

    @DynamoDbAttribute("esEnviado")
    public String getEsEnviado() {
        return esEnviado;
    }

    public void setEsEnviado(String esEnviado) {
        this.esEnviado = esEnviado;
    }

}
