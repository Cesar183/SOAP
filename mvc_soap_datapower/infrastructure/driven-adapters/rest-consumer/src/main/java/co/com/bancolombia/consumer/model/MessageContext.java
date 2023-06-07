package co.com.bancolombia.consumer.model;


import lombok.*;

import java.util.List;
//import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MessageContext {
    private List<Property> property;
}
