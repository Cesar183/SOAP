package co.com.bancolombia.dynamodb.entity.cabecera;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class ClassificationEntity {

    private String clasificacion;

    public ClassificationEntity() {

    }

    public ClassificationEntity(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    @DynamoDbAttribute("clasificacion")
    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
}
