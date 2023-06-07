package co.com.bancolombia.dynamodb.entity;

import co.com.bancolombia.dynamodb.entity.bb11.Bb11Entity;
import co.com.bancolombia.dynamodb.entity.bb12.Bb12Entity;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.*;


@DynamoDbBean
public class RadicadoEntity {

    String numeroRadicado;
    Integer version;
    String estadoBb11;
    String observacionesBb11;
    String estadoBb12;
    String observacionesBb12;
    Bb11Entity bb11;
    Bb12Entity bb12Informar;
    Bb12Entity bb12Notificar;

    public RadicadoEntity() {
    }

    public RadicadoEntity(String numeroRadicado, String estadoBb11, String observacionesBb11,
                           Integer version, Bb11Entity bb11, Bb12Entity bb12Informar,
                          Bb12Entity bb12Notificar) {
        this.numeroRadicado = numeroRadicado;
        this.estadoBb11 = estadoBb11;
        this.observacionesBb11 = observacionesBb11;
        this.version = version;
        this.bb11 = bb11;
        this.bb12Informar = bb12Informar;
        this.bb12Notificar = bb12Notificar;
    }

    @DynamoDbPartitionKey
    @DynamoDbAttribute("numeroRadicado")
    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    @DynamoDbAttribute("version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @DynamoDbAttribute("estadoB11")
    public String getEstadoBb11() {
        return estadoBb11;
    }

    public void setEstadoBb11(String estadoBb11) {
        this.estadoBb11 = estadoBb11;
    }

    @DynamoDbAttribute("observacionesBb11")
    public String getObservacionesBb11() { return observacionesBb11; }

    public void setObservacionesBb11(String observacionesBb11) { this.observacionesBb11 = observacionesBb11; }

    @DynamoDbAttribute("bb11")
    public Bb11Entity getBb11() {
        return bb11;
    }

    public void setBb11(Bb11Entity bb11) {
        this.bb11 = bb11;
    }

    @DynamoDbAttribute("bb12Informar")
    public Bb12Entity getBb12Informar() {
        return bb12Informar;
    }

    public void setBb12Informar(Bb12Entity bb12Informar) {
        this.bb12Informar = bb12Informar;
    }

    @DynamoDbAttribute("bb12Notificar")
    public Bb12Entity getBb12Notificar() {
        return bb12Notificar;
    }

    public void setBb12Notificar(Bb12Entity bb12Notificar) {
        this.bb12Notificar = bb12Notificar;
    }

    @DynamoDbAttribute("estadoB12")
    public String getEstadoBb12() {
        return estadoBb12;
    }

    public void setEstadoBb12(String estadoBb11) {
        this.estadoBb12 = estadoBb11;
    }

    @DynamoDbAttribute("observacionesBb12")
    public String getObservacionesBb12() { return observacionesBb12; }

    public void setObservacionesBb12(String observacionesBb12) { this.observacionesBb12 = observacionesBb12;}
}
