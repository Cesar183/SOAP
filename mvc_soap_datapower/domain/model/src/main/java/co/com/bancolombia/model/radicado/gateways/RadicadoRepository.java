package co.com.bancolombia.model.radicado.gateways;

import co.com.bancolombia.model.radicado.Radicado;
import reactor.core.publisher.Mono;

public interface RadicadoRepository {
    Mono<Radicado> getRadicadoById(String id);
    Mono<Radicado> saveRadicado(Radicado radicado);
}
