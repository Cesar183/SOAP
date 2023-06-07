package co.com.bancolombia.consumer;

import co.com.bancolombia.consumer.model.request.Bb11Request;
import co.com.bancolombia.consumer.model.response.Bb12Response;
import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.model.bb12.Bb12;
import co.com.bancolombia.model.bb12.gateways.Bb12Repository;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements Bb12Repository/* implements Gateway from domain */{

    private final WebClient client;
    private final ObjectMapper mapper;

    @Override
    public Mono<Bb12> getBb12ByBb11(Bb11 bb11) {
        return client
                .post()
                //.uri("/api/bb12")
                .uri("/api/bb12/{num}", bb11.getNumeroRadicado()) // mock
                .body(toEntityBb11(bb11), Bb11Request.class)
                .retrieve()
                .bodyToMono(Bb12Response.class)
                .map(this::toDomainBb12);
    }

    private Bb12 toDomainBb12(Bb12Response response) {
        return mapper.map(response, Bb12.class);
    }

    private Mono<Bb11Request> toEntityBb11(Bb11 request) {
        return Mono.just(mapper.map(request, Bb11Request.class));
    }
}