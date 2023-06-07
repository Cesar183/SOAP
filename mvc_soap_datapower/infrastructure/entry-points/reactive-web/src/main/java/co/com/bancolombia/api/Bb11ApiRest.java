package co.com.bancolombia.api;
import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.usecase.bb11.Bb11UseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/bb11", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class Bb11ApiRest {
    private final Bb11UseCase useCase;

    @PostMapping
    public Mono<ResponseEntity<Boolean>> requestBb11(@RequestBody Bb11 bb11) {
        return useCase.requestBb11(bb11)
                .map(ResponseEntity::ok);
    }

}
