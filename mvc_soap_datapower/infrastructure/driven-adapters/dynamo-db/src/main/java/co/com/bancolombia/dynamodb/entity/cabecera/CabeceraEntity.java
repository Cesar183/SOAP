package co.com.bancolombia.dynamodb.entity.cabecera;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.time.ZonedDateTime;
import java.util.List;

@DynamoDbBean
public class CabeceraEntity {
    private String systemId;
    private String messageId;
    private ZonedDateTime timestamp;
    private MessageContextEntity messageContext;
    private UserIdEntity userId;
    private DestinationEntity destination;
    private List <ClassificationEntity> classifications;

    public CabeceraEntity() {
    }

    public CabeceraEntity(String systemId, String messageId, ZonedDateTime timestamp, MessageContextEntity messageContext, UserIdEntity userId, DestinationEntity destination, List<ClassificationEntity> classifications) {
        this.systemId = systemId;
        this.messageId = messageId;
        this.timestamp = timestamp;
        this.messageContext = messageContext;
        this.userId = userId;
        this.destination = destination;
        this.classifications = classifications;
    }

    @DynamoDbAttribute("systemId")
    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId;
    }

    @DynamoDbAttribute("messageId")
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @DynamoDbAttribute("timestamp")
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @DynamoDbAttribute("messageContext")
    public MessageContextEntity getMessageContext() {
        return messageContext;
    }

    public void setMessageContext(MessageContextEntity messageContext) {
        this.messageContext = messageContext;
    }

    @DynamoDbAttribute("userId")
    public UserIdEntity getUserId() {
        return userId;
    }


    public void setUserId(UserIdEntity userId) {
        this.userId = userId;
    }

    @DynamoDbAttribute("destination")
    public DestinationEntity getDestination() {
        return destination;
    }

    public void setDestination(DestinationEntity destination) {
        this.destination = destination;
    }

    @DynamoDbAttribute("classifications")
    public List<ClassificationEntity> getClassifications() {
        return classifications;
    }

    public void setClassifications(List<ClassificationEntity> classifications) {
        this.classifications = classifications;
    }
}
