package co.com.bancolombia.dynamodb.entity.cabecera;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class PropertyEntity {

    private String key;
    private String value;


    public PropertyEntity() {

    }

    public PropertyEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @DynamoDbAttribute("key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    @DynamoDbAttribute("value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}