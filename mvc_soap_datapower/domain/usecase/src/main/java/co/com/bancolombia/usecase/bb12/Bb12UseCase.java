package co.com.bancolombia.usecase.bb12;

import co.com.bancolombia.model.bb11.Bb11;
import co.com.bancolombia.model.bb12.Bb12;
import co.com.bancolombia.model.bb12.gateways.Bb12Repository;
import co.com.bancolombia.model.bb12validado.Bb12Validado;
import co.com.bancolombia.model.cliente.Cliente;
import co.com.bancolombia.model.documento.Documento;
import co.com.bancolombia.model.evento.Evento;
import co.com.bancolombia.model.validacionbb.ValidacionBb;
import co.com.bancolombia.model.validacionesbb.ValidacionesBb;
import co.com.bancolombia.usecase.radicado.RadicadoUseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Bb12UseCase {
    private final Bb12Repository bb12Repository;
    private final RadicadoUseCase radicadoUseCase;

    public void getBb12timer(Bb11 bb11) {
        Mono.fromRunnable(() -> this.getBb12(bb11))
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe();
    }

    private void getBb12(Bb11 bb11) {
        bb12Repository.getBb12ByBb11(bb11)
                .flatMap(bb12 -> {
                    ValidacionesBb validation = isBb12Valid(bb12);
                    if(validation.isValido()) {
                        return Mono.just(new Bb12Validado(bb12, validation.getMensaje(), true));
                    } else {
                        return Mono.just(new Bb12Validado(isBb12NullOrIncomplete(bb12), validation.getMensaje(), false));
                    }
                })
                .flatMap(bb12Validado -> radicadoUseCase.saveBb12(bb12Validado.getBb12(), bb12Validado.getObservaciones()))
                .subscribe();
    }
    private ValidacionesBb isBb12Valid(Bb12 bb12){
        if(bb12 == null){
            return new ValidacionesBb(false, ValidacionBb.OBJETO_BB12_NULO.getMensaje());
        } else {
            ValidacionesBb cabeceraValida = radicadoUseCase.isCabeceraValid(bb12.getCabecera());
            if (!cabeceraValida.isValido()) {
                return new ValidacionesBb(false, ValidacionBb.CABECERA_BB12_INVALIDA + cabeceraValida.getMensaje());
            }
            if(bb12.getNumeroRadicado() == null || bb12.getNumeroRadicado().isEmpty()) {
                return new ValidacionesBb(false, ValidacionBb.NUMERO_RADICADO_INVALIDO.getMensaje());
            }
            if(bb12.getVersion() == null || bb12.getVersion().isEmpty()) {
                return new ValidacionesBb(false, ValidacionBb.VERSION_INVALIDA.getMensaje());
            }
            ValidacionesBb clientesValidados = isClientesValid(bb12.getClientes());
            if (!clientesValidados.isValido()) {
                return new ValidacionesBb(false, ValidacionBb.CLIENTES_INVALIDOS.getMensaje() + clientesValidados.getMensaje());
            }
            return new ValidacionesBb(true, ValidacionBb.BB12_VALIDO.getMensaje());
        }
    }

    private ValidacionesBb isClientesValid(List<Cliente> clientes){
        if(clientes == null || clientes.isEmpty()){
            return new ValidacionesBb(false, ValidacionBb.LISTA_CLIENTES_NULA.getMensaje());
        }
        for (Cliente cliente : clientes) {
            ValidacionesBb identificacionValida = radicadoUseCase.isIdentificacionClienteValid(cliente.getIdentificacionCliente());
            if (!identificacionValida.isValido()) {
                return new ValidacionesBb(false, ValidacionBb.IDENTIFICACION_CLIENTE_INVALIDA.getMensaje() + identificacionValida.getMensaje());
            }
            ValidacionesBb documentosValidos = isDocumentosValid(cliente.getDocumentos());
            if (!documentosValidos.isValido()) {
                return new ValidacionesBb(false, ValidacionBb.DOCUMENTOS_INVALIDOS.getMensaje() + documentosValidos.getMensaje());
            }
        }
        return new ValidacionesBb(true, ValidacionBb.TODOS_CLIENTES_VALIDOS.getMensaje());
    }

    private ValidacionesBb isDocumentosValid(List<Documento> documentos) {
        if (documentos == null || documentos.isEmpty()) {
            return new ValidacionesBb(false, ValidacionBb.LISTA_DOCUMENTOS_NULA.getMensaje());
        }

        for (Documento documento : documentos) {
            ValidacionesBb validationResult;

            validationResult = isEventosValid(documento.getEventos());
            if (!validationResult.isValido()) {
                return validationResult;
            }

            validationResult = isCodigoDocumentoValid(documento.getCodigoDocumento());
            if (!validationResult.isValido()) {
                return validationResult;
            }

            validationResult = isSubTipoDocumentoValid(documento.getSubTipoDocumento());
            if (!validationResult.isValido()) {
                return validationResult;
            }

            validationResult = isNombreArchivoValid(documento.getNombreArchivo());
            if (!validationResult.isValido()) {
                return validationResult;
            }
        }

        return new ValidacionesBb(true, ValidacionBb.TODOS_DOCUMENTOS_VALIDOS.getMensaje());
    }

    private ValidacionesBb isCodigoDocumentoValid(String codigoDocumento) {
        return codigoDocumento != null && !codigoDocumento.isEmpty()
                ? new ValidacionesBb(true, "")
                : new ValidacionesBb(false, ValidacionBb.CODIGO_DOCUMENTO_INVALIDO.getMensaje());
    }

    private ValidacionesBb isSubTipoDocumentoValid(String subTipoDocumento) {
        return subTipoDocumento != null && !subTipoDocumento.isEmpty()
                ? new ValidacionesBb(true, "")
                : new ValidacionesBb(false, ValidacionBb.SUBTIPO_DOCUMENTO_INVALIDO.getMensaje());
    }

    private ValidacionesBb isNombreArchivoValid(String nombreArchivo) {
        return nombreArchivo != null && !nombreArchivo.isEmpty()
                ? new ValidacionesBb(true, "")
                : new ValidacionesBb(false, ValidacionBb.NOMBRE_ARCHIVO_INVALIDO.getMensaje());
    }

    private ValidacionesBb isEventosValid(List<Evento> eventos){
        if(eventos == null || eventos.isEmpty()){
            return new ValidacionesBb(false, ValidacionBb.LISTA_EVENTOS_NULA.getMensaje());
        }
        for (Evento evento : eventos) {
            ValidacionesBb result = validateEvento(evento);
            if (!result.isValido()) {
                return result;
            }
        }
        return new ValidacionesBb(true, ValidacionBb.TODOS_EVENTOS_VALIDOS.getMensaje());
    }

    private ValidacionesBb validateEvento(Evento evento) {
        ValidacionesBb result = isDescripcionEstadoEventoValid(evento.getDescripcionEstadoEvento());
        if (!result.isValido()) {
            return new ValidacionesBb(false, ValidacionBb.DESCRIPCION_ESTADO_EVENTO_INVALIDA.getMensaje() + result.getMensaje());
        }
        result = isEstadoEventoValid(evento.getEstadoEvento());
        if (!result.isValido()) {
            return new ValidacionesBb(false, ValidacionBb.ESTADO_EVENTO_INVALIDO.getMensaje());
        }
        result = isFechaEventoValid(evento.getFechaEvento());
        if (!result.isValido()) {
            return new ValidacionesBb(false, ValidacionBb.FECHA_EVENTO_INVALIDA.getMensaje());
        }
        result = isIndicadorEventoValid(evento.getIndicadorEvento());
        if (!result.isValido()) {
            return new ValidacionesBb(false, ValidacionBb.INDICADOR_EVENTO_INVALIDO.getMensaje());
        }
        return new ValidacionesBb(true, "");
    }

    private ValidacionesBb isEstadoEventoValid(String estadoEvento) {
        boolean isValid = estadoEvento != null && !estadoEvento.isEmpty();
        return isValid
                ? new ValidacionesBb(true, "")
                : new ValidacionesBb(false, "");
    }

    private ValidacionesBb isFechaEventoValid(String fechaEvento) {
        boolean isValid = fechaEvento != null && !fechaEvento.isEmpty();
        return isValid
                ? new ValidacionesBb(true, "")
                : new ValidacionesBb(false, "");
    }

    private ValidacionesBb isIndicadorEventoValid(String indicadorEvento) {
        boolean isValid = indicadorEvento != null && !indicadorEvento.isEmpty();
        return isValid
                ? new ValidacionesBb(true, "")
                : new ValidacionesBb(false, "");
    }

    private ValidacionesBb isDescripcionEstadoEventoValid(List<String> descripcion){
        boolean isValid = descripcion != null && !descripcion.isEmpty();
        return isValid
                ? new ValidacionesBb(true, ValidacionBb.DESCRIPCION_ESTADO_EVENTO_VALIDA.getMensaje())
                : new ValidacionesBb(false, ValidacionBb.DESCRIPCION_ESTADO_EVENTO_NULA.getMensaje());
    }

    private Bb12 isBb12NullOrIncomplete(Bb12 bb12) {
        if (bb12 == null) {
            Bb12 bb12Null = new Bb12();
            bb12Null.setCabecera(radicadoUseCase.cabeceraNullOrIncomplete(null));
            bb12Null.setNumeroRadicado(ValidacionBb.BB12_NULL.getMensaje());
            bb12Null.setVersion("1");
            bb12Null.setClientes(new ArrayList<>());
            return bb12Null;
        }
        if (bb12.getNumeroRadicado() == null || bb12.getNumeroRadicado().isEmpty()) {
            bb12.setNumeroRadicado(ValidacionBb.BB12_SIN_RADICADO.getMensaje());
        }
        if (bb12.getVersion() == null || bb12.getVersion().isEmpty()) {
            bb12.setVersion(ValidacionBb.BB12_SIN_VERSION.getMensaje());
        }
        if (bb12.getClientes() == null) {
            bb12.setClientes(new ArrayList<>());
        }
        return bb12;
    }




}
