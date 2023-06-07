package co.com.bancolombia.dynamodb.entity.bb11;

import co.com.bancolombia.dynamodb.entity.cabecera.CabeceraEntity;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class Bb11Entity {
    private CabeceraEntity cabecera;
    private String numeroRadicado;
    private String numeroRadicadoDos;
    private String fechaSolicitud;
    private Integer version;
    private List<DatosClienteEntity> datosCliente;

    public Bb11Entity() {
    }

    public Bb11Entity(CabeceraEntity cabecera, String numeroRadicado, String numeroRadicadoDos, String fechaSolicitud, Integer version, List<DatosClienteEntity> datosCliente) {
        this.cabecera = cabecera;
        this.numeroRadicado = numeroRadicado;
        this.numeroRadicadoDos = numeroRadicadoDos;
        this.fechaSolicitud = fechaSolicitud;
        this.version = version;
        this.datosCliente = datosCliente;
    }

    @DynamoDbAttribute("cabecera")
    public CabeceraEntity getCabecera() {
        return cabecera;
    }

    public void setCabecera(CabeceraEntity cabecera) {
        this.cabecera = cabecera;
    }
    @DynamoDbAttribute("numeroRadicado")
    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    @DynamoDbAttribute("numeroRadicadoDos")
    public String getNumeroRadicadoDos() {
        return numeroRadicadoDos;
    }

    public void setNumeroRadicadoDos(String numeroRadicadoDos) {
        this.numeroRadicadoDos = numeroRadicadoDos;
    }

    @DynamoDbAttribute("fechaSolicitud")
    public String getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(String fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    @DynamoDbAttribute("version")
    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @DynamoDbAttribute("datosCliente")
    public List<DatosClienteEntity> getDatosCliente() {
        return datosCliente;
    }

    public void setDatosCliente(List<DatosClienteEntity> datosCliente) {
        this.datosCliente = datosCliente;
    }
}
