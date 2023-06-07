package co.com.bancolombia.usecase.bb11;

import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.model.cabecera.Cabecera;
import co.com.bancolombia.model.datoscliente.DatosCliente;
import co.com.bancolombia.model.validacionbb.ValidacionBb;
import co.com.bancolombia.model.documentorequerido.DocumentoRequerido;
import co.com.bancolombia.model.validacionesbb.ValidacionesBb;
import co.com.bancolombia.usecase.bb12.Bb12UseCase;
import co.com.bancolombia.usecase.radicado.RadicadoUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Bb11UseCase {
    private final Bb12UseCase bb12UseCase;
    private final RadicadoUseCase radicadoUseCase;


    public Mono<Boolean> requestBb11(Bb11 bb11) {
        return isBb11Valid(bb11)
                .flatMap(valid -> {
                    if (valid.isValido()) {
                        return radicadoUseCase.saveBb11(bb11, valid.getMensaje())
                                .doOnSuccess(radicado -> bb12UseCase.getBb12timer(radicado.getBb11()))
                                .thenReturn(true);
                    } else {
                        Bb11 bb11Invalido = bb11IsNullOrIncomplete(bb11);
                        return radicadoUseCase.saveBb11Error(bb11Invalido, valid.getMensaje())
                                .thenReturn(false);
                    }
                });
    }
    private Mono<ValidacionesBb> isBb11Valid(Bb11 bb11){
        if(bb11 == null){
            return Mono.just(new ValidacionesBb(false, ValidacionBb.BB11_ES_NULO.getMensaje()));
        } else {
            if(bb11.getNumeroRadicado() == null || bb11.getNumeroRadicado().isEmpty()){
                return Mono.just(new ValidacionesBb(false, ValidacionBb.NUMERO_RADICADO_BB11_INVALIDO.getMensaje()));
            }
            if(bb11.getFechaSolicitud() == null || bb11.getFechaSolicitud().isEmpty()){
                return Mono.just(new ValidacionesBb(false, ValidacionBb.FECHA_SOLICITUD_BB11_INVALIDA.getMensaje()));
            }
            ValidacionesBb cabeceraValida = radicadoUseCase.isCabeceraValid(bb11.getCabecera());
            if(!cabeceraValida.isValido()){
                return Mono.just(new ValidacionesBb(false, ValidacionBb.CABECERA_BB11_INVALIDA.getMensaje() + ": " + cabeceraValida.getMensaje()));
            }
            return isDatosClientesValid(bb11.getDatosCliente())
                    .flatMap(valid -> {
                        if(!valid.isValido()){
                            return Mono.just(new ValidacionesBb(false, ValidacionBb.VALIDACION_DATOS_CLIENTES_BB11_FALLO.getMensaje() + ": " + valid.getMensaje()));
                        }
                        return Mono.just(new ValidacionesBb(true, ValidacionBb.BB11_VALIDO.getMensaje()));
                    });
        }
    }

    private Mono<ValidacionesBb> isDatosClientesValid(List<DatosCliente> datosClientes){
        if(datosClientes == null || datosClientes.isEmpty()){
            return Mono.just(new ValidacionesBb(false, ValidacionBb.LISTA_DATOS_CLIENTES_VACIA_O_NULA.getMensaje()));
        } else {
            return datosClientes.stream()
                    .map(this::validateDatosCliente)
                    .filter(validationResult -> !validationResult.isValido())
                    .findFirst()
                    .map(Mono::just)
                    .orElseGet(() -> Mono.just(new ValidacionesBb(true, ValidacionBb.TODOS_DATOS_CLIENTES_VALIDOS.getMensaje())));
        }
    }

    private ValidacionesBb validateDatosCliente(DatosCliente cliente) {
        ValidacionesBb identificacionValida = radicadoUseCase.isIdentificacionClienteValid(cliente.getIdentificacionCliente());

        if (!identificacionValida.isValido()) {
            return new ValidacionesBb(false, ValidacionBb.IDENTIFICACION_CLIENTE_INVALIDA.getMensaje() + ": " + identificacionValida.getMensaje());
        } else if (cliente.getCantidadDocumentosRecibidos() <= 0) {
            return new ValidacionesBb(false, ValidacionBb.CANTIDAD_DOCUMENTOS_INVALIDA.getMensaje());
        } else if (cliente.getNombreCliente() == null || cliente.getNombreCliente().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.NOMBRE_CLIENTE_INVALIDO.getMensaje());
        } else if (cliente.getTipoCliente() == null || cliente.getTipoCliente().isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.TIPO_CLIENTE_INVALIDO.getMensaje());
        } else {
            ValidacionesBb documentoValido = isDocumentoRequeridoValid(cliente.getDocumentoRequerido());
            if (!documentoValido.isValido()) {
                return new ValidacionesBb(false, ValidacionBb.DOCUMENTO_INVALIDO.getMensaje() + ": " + documentoValido.getMensaje());
            }
        }
        return new ValidacionesBb(true, "");
    }
    private ValidacionesBb isDocumentoRequeridoValid(List<DocumentoRequerido> documentosRequeridos) {
        if (documentosRequeridos == null || documentosRequeridos.isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.LISTA_DOCUMENTOS_VACIA_O_NULA.getMensaje());
        }
        for (DocumentoRequerido documento : documentosRequeridos) {
            if (documento.getCodigoDocumento() == null || documento.getCodigoDocumento().isEmpty()) {
                return new ValidacionesBb(false, ValidacionBb.CODIGO_DOCUMENTO_INVALIDO.getMensaje());
            }
            if (documento.getSubTipoDocumento() == null || documento.getSubTipoDocumento().isEmpty()) {
                return new ValidacionesBb(false, ValidacionBb.SUBTIPO_DOCUMENTO_INVALIDO.getMensaje());
            }
            if (documento.getEsObligatorio() == null || documento.getEsObligatorio().isEmpty()) {
                return new ValidacionesBb(false, ValidacionBb.CAMPO_ES_OBLIGATORIO_INVALIDO.getMensaje());
            }
        }
        return new ValidacionesBb(true, ValidacionBb.TODOS_DOCUMENTOS_VALIDOS.getMensaje());
    }


    private Bb11 bb11IsNullOrIncomplete(Bb11 bb11){
        if(bb11 == null){
            Bb11 bb11Null = new Bb11();
            bb11Null.setCabecera(radicadoUseCase.cabeceraNullOrIncomplete(null));
            bb11Null.setNumeroRadicado(ValidacionBb.NUMERO_RADICADO_BB11_INVALIDO.getMensaje());
            bb11Null.setVersion(1);
            bb11Null.setDatosCliente(new ArrayList<>());
            return bb11Null;
        }

        if(bb11.getCabecera() == null){
            bb11.setCabecera(new Cabecera());
        }

        if (bb11.getNumeroRadicado() == null || bb11.getNumeroRadicado().isEmpty()) {
            bb11.setNumeroRadicado(ValidacionBb.NUMERO_RADICADO_BB11_INVALIDO.getMensaje());
        }

        if(bb11.getNumeroRadicado() != null && !bb11.getNumeroRadicado().isEmpty() &&
                (bb11.getVersion() == null || bb11.getVersion() <= 0)){
            bb11.setVersion(1);
        }

        if(bb11.getDatosCliente() == null){
            bb11.setDatosCliente(new ArrayList<>());
        }

        return bb11;
    }














}
