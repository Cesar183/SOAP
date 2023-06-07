package co.com.bancolombia.model.bb12.gateways;

import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.model.bb12.Bb12;
import reactor.core.publisher.Mono;

public interface Bb12Repository {
    Mono<Bb12> getBb12ByBb11(Bb11 bb11);
}
