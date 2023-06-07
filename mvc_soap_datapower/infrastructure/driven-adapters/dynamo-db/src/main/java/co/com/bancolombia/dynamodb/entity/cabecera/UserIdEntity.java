package co.com.bancolombia.dynamodb.entity.cabecera;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

@DynamoDbBean
public class UserIdEntity {
    private String userName;
    private String userToken;

    public UserIdEntity() {
    }

    public UserIdEntity(String userName, String userToken) {
        this.userName = userName;
        this.userToken = userToken;
    }

    @DynamoDbAttribute("userName")
    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    @DynamoDbAttribute("userToken")
    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
