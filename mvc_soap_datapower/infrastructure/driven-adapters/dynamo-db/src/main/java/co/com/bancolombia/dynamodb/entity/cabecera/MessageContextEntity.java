package co.com.bancolombia.dynamodb.entity.cabecera;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.util.List;

@DynamoDbBean
public class MessageContextEntity {
    private List<PropertyEntity> property;

    public MessageContextEntity(){

    }

    public MessageContextEntity(List<PropertyEntity> property) {
        this.property = property;
    }

    @DynamoDbAttribute("property")
    public List<PropertyEntity> getProperty() {
        return property;
    }

    public void setProperty(List<PropertyEntity> property) {
        this.property = property;
    }
}