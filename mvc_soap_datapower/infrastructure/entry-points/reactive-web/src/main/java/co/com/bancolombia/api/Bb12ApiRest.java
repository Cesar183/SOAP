package co.com.bancolombia.api;
import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.usecase.bb12.Bb12UseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/bb12", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class Bb12ApiRest {
    private final Bb12UseCase useCase;


    @PostMapping
    public Mono<ResponseEntity<Boolean>> requestBb12Timer(@RequestBody Bb11 bb11) {
        return Mono.fromRunnable(() -> useCase.getBb12timer(bb11))
                .map(a -> ResponseEntity.ok(true));
    }
}
