package co.com.bancolombia.model.userid;
import lombok.*;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserId {
    private String userName;
    private String userToken;
}
