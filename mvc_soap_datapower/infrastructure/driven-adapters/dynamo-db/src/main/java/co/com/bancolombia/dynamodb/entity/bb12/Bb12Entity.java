package co.com.bancolombia.dynamodb.entity.bb12;

import co.com.bancolombia.dynamodb.entity.cabecera.CabeceraEntity;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class Bb12Entity {

    private CabeceraEntity cabecera;
    private String numeroRadicado;
    private String version;
    private List<ClienteEntity> clientes;

    public Bb12Entity() {
    }

    public Bb12Entity(CabeceraEntity cabecera, String numeroRadicado, String version, List<ClienteEntity> clientes) {
        this.cabecera = cabecera;
        this.numeroRadicado = numeroRadicado;
        this.version = version;
        this.clientes = clientes;
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

    @DynamoDbAttribute("version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @DynamoDbAttribute("clientes")
    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }

}
