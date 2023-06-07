package co.com.bancolombia.usecase.radicado;

import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.model.bb12.Bb12;
import co.com.bancolombia.model.cabecera.Cabecera;
import co.com.bancolombia.model.cliente.Cliente;
import co.com.bancolombia.model.datoscliente.DatosCliente;
import co.com.bancolombia.model.documento.Documento;
import co.com.bancolombia.model.documentorequerido.DocumentoRequerido;
import co.com.bancolombia.model.estado.Estado;
import co.com.bancolombia.model.evento.Evento;
import co.com.bancolombia.model.identificacioncliente.IdentificacionCliente;
import co.com.bancolombia.model.radicado.Radicado;
import co.com.bancolombia.model.radicado.gateways.RadicadoRepository;
import co.com.bancolombia.model.userid.UserId;
import co.com.bancolombia.model.validacionbb.ValidacionBb;
import co.com.bancolombia.model.validacionesbb.ValidacionesBb;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RadicadoUseCase {
    private final RadicadoRepository radicadoRepository;
    private static final String DOCUMENTO_OBLIGATORIO = "si";

    public Mono<Radicado> saveBb11(Bb11 bb11, String observaciones) {
            return Mono.just(bb11)
                    .map(bb11Request ->
                            Radicado.builder()
                                    .bb11(bb11Request)
                                    .estadoBb11(Estado.OPEN.toString())
                                    .numeroRadicado(bb11Request.getNumeroRadicado())
                                    .version(bb11.getVersion())
                                    .observacionesBb11(observaciones)
                                    .build()
                    )
                    .flatMap(radicadoRepository::saveRadicado);

    }

    public Mono<Radicado> saveBb11Error(Bb11 bb11, String observaciones) {
        return Mono.just(bb11)
                .map(bb11Request ->
                        Radicado.builder()
                                .bb11(bb11Request)
                                .estadoBb11(Estado.FAILURE.toString())
                                .numeroRadicado(bb11Request.getNumeroRadicado())
                                .version(bb11.getVersion())
                                .observacionesBb11(observaciones)
                                .build()
                )
                .flatMap(radicadoRepository::saveRadicado);

    }

    public Mono<Radicado> getRadicadoById(String numeroRadicado) {
        return radicadoRepository.getRadicadoById(numeroRadicado);
    }

    public Mono<Radicado> saveBb12(Bb12 bb12, String observacionesBb12) {
        Mono<Radicado> radicado = getRadicadoById(bb12.getNumeroRadicado())
                .map(radicadoRequest -> {
                    radicadoRequest.setEstadoBb12(observacionesBb12);
                    radicadoRequest.setBb12Informar(bb12);
                    return radicadoRequest;
                });

        Mono<Estado> estado = radicado.flatMap(rad -> validateBb12(rad.getBb11(), rad.getBb12Informar()));

        return radicado.zipWith(estado)
                .map(tuple -> {
                    Radicado radicadoRequest = tuple.getT1();
                    Estado estadoRequest = tuple.getT2();
                    radicadoRequest.setEstadoBb12(estadoRequest.toString());
                    return radicadoRequest;
                })
                .flatMap(radicadoRepository::saveRadicado);
    }

    private Mono<Estado> validateBb12(Bb11 bb11, Bb12 bb12) {
        List<DatosCliente> datosClienteBb11 = bb11.getDatosCliente();
        List<Cliente> clientesBb12 = bb12.getClientes();

        for (DatosCliente datoClienteBb11 : datosClienteBb11) {
            Optional<Cliente> clienteBb12 = clientesBb12.stream()
                    .filter(cliente -> cliente.getIdentificacionCliente().equals(datoClienteBb11.getIdentificacionCliente()))
                    .findFirst();
            if (clienteBb12.isPresent()) {
                if (!isDocumentsComplete(datoClienteBb11.getDocumentoRequerido(), clienteBb12.get().getDocumentos())) {
                    return Mono.just(Estado.MISSING);
                }
                if (!isAllDocumentsOk(datoClienteBb11.getDocumentoRequerido(), clienteBb12.get().getDocumentos())) {
                    return Mono.just(Estado.RETURN);
                }
            }
        }
        return Mono.just(Estado.CLOSE);

    }


    private boolean isDocumentsComplete(List<DocumentoRequerido> docsReqBb11, List<Documento> docsBb12) {
        return docsReqBb11.stream()
                .allMatch(docBb11 -> docsBb12.stream()
                        .anyMatch(docBb12 -> docBb12.getCodigoDocumento().equals(docBb11.getCodigoDocumento()) &&
                                docBb12.getSubTipoDocumento().equals(docBb11.getSubTipoDocumento())));
    }

    private boolean isAllDocumentsOk(List<DocumentoRequerido> docsReqBb11, List<Documento> docsBb12) {
        List<DocumentoRequerido> filtrado = docsReqBb11.stream().filter(doc -> doc.getEsObligatorio().equals(DOCUMENTO_OBLIGATORIO)).toList();
        return filtrado.stream().allMatch(docReqBb11 -> docsBb12.stream()
                .filter(documento -> documento.getCodigoDocumento().equals(docReqBb11.getCodigoDocumento()))
                .filter(documento -> documento.getSubTipoDocumento().equals(docReqBb11.getSubTipoDocumento()))
                .filter(documento -> !documento.getEventos().isEmpty() && documento.getEventos().size() == 4)
                .findFirst()
                .map(doc -> isValidEvents(doc.getEventos()))
                .orElse(false));
    }

    private boolean isValidEvents(List<Evento> eventos) {
        return eventos.stream()
                .allMatch(evento -> evento.getEstadoEvento().equals("OK"));
    }

    public ValidacionesBb isIdentificacionClienteValid(IdentificacionCliente identificacionCliente){
        if(identificacionCliente == null){
            return new ValidacionesBb(false, ValidacionBb.IDENTIFICACION_CLIENTE_NULA.getMensaje());
        }
        if(identificacionCliente.getNumeroDocumento() == null || identificacionCliente.getNumeroDocumento().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.NUMERO_DOCUMENTO_CLIENTE_INVALIDO.getMensaje());
        }
        if(identificacionCliente.getTipoDocumento() == null || identificacionCliente.getTipoDocumento().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.TIPO_DOCUMENTO_CLIENTE_INVALIDO.getMensaje());
        }
        return new ValidacionesBb(true, ValidacionBb.IDENTIFICACION_CLIENTE_VALIDA.getMensaje());
    }

    public ValidacionesBb isCabeceraValid(Cabecera cabecera) {
        if (cabecera == null) {
            return new ValidacionesBb(false, ValidacionBb.CABECERA_NULA.getMensaje());
        }
        if (cabecera.getSystemId() == null || cabecera.getSystemId().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.SYSTEM_ID_CABECERA_INVALIDO.getMensaje());
        }
        if (cabecera.getMessageId() == null || cabecera.getMessageId().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.MESSAGE_ID_CABECERA_INVALIDO.getMensaje());
        }
        ValidacionesBb isUserIdValid = isUserIdvalid(cabecera.getUserId());
        if (!isUserIdValid.isValido()) {
            return new ValidacionesBb(false, ValidacionBb.VALIDACION_USER_ID_FALLO.getMensaje() + ": " + isUserIdValid.getMensaje());
        }
        return new ValidacionesBb(true, ValidacionBb.CABECERA_VALIDA.getMensaje());
    }

    public ValidacionesBb isUserIdvalid(UserId userId) {
        if(userId == null) {
            return new ValidacionesBb(false, ValidacionBb.USER_ID_NULO.getMensaje());
        }
        if(userId.getUserName() == null || userId.getUserName().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.USER_NAME_USER_ID_INVALIDO.getMensaje());
        }
        return new ValidacionesBb(true, ValidacionBb.USER_ID_VALIDO.getMensaje());
    }

    public Cabecera cabeceraNullOrIncomplete(Cabecera cabecera) {
        if (cabecera == null) {
            return createEmptyCabecera();
        }

        if (cabecera.getSystemId() == null || cabecera.getSystemId().isEmpty()) {
            cabecera.setSystemId(ValidacionBb.SYSTEM_ID_NULO_O_VACIO.getMensaje());
        }

        if (cabecera.getMessageId() == null || cabecera.getMessageId().isEmpty()) {
            cabecera.setMessageId(ValidacionBb.MESSAGE_ID_NULO_O_VACIO.getMensaje());
        }

        cabecera.setUserId(userIdNullOrIncomplete(cabecera.getUserId()));

        return cabecera;
    }

    private Cabecera createEmptyCabecera() {
        Cabecera cabeceraNull = new Cabecera();
        cabeceraNull.setSystemId(ValidacionBb.CABECERA_NULA.getMensaje());
        cabeceraNull.setMessageId(ValidacionBb.CABECERA_NULA_RECIBIDA.getMensaje());
        cabeceraNull.setUserId(userIdNullOrIncomplete(null));
        return cabeceraNull;
    }

    public UserId userIdNullOrIncomplete(UserId userId){
        if(userId == null || userId.getUserName() == null || userId.getUserName().isEmpty()){
            return createEmptyUserId();
        }

        return userId;
    }

    private UserId createEmptyUserId() {
        UserId userIdNull = new UserId();
        userIdNull.setUserName(ValidacionBb.USERID_NULO.getMensaje());
        return userIdNull;
    }

}
