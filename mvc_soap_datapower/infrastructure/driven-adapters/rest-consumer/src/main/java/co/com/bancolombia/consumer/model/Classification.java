package co.com.bancolombia.consumer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Classification {
    private String clasificacion;
}
