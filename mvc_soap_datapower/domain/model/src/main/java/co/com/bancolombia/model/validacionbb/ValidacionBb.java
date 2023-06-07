package co.com.bancolombia.model.validacionbb;

public enum ValidacionBb {
    BB11_VALIDO("Bb11 validado correctamente"),
    BB12_VALIDO("Bb12 validado correctamente"),
    BB11_ES_NULO("El objeto Bb11 es nulo"),
    LISTA_DOCUMENTOS_VACIA_O_NULA("La lista de documentos requeridos está vacía o es nula"),
    CODIGO_DOCUMENTO_INVALIDO("El código de documento está vacío o es nulo para uno o más documentos"),
    SUBTIPO_DOCUMENTO_INVALIDO("El subtipo de documento está vacío o es nulo para uno o más documentos"),
    CAMPO_ES_OBLIGATORIO_INVALIDO("El campo 'es obligatorio' está vacío o es nulo para uno o más documentos"),
    TODOS_DOCUMENTOS_VALIDOS("Todos los documentos son válidos"),
    LISTA_DATOS_CLIENTES_VACIA_O_NULA("La lista de datos del cliente está vacía o es nula"),
    IDENTIFICACION_CLIENTE_INVALIDA("La identificación del cliente no es válida"),
    CANTIDAD_DOCUMENTOS_INVALIDA("La cantidad de documentos recibidos es 0 o menor para uno o más clientes"),
    NOMBRE_CLIENTE_INVALIDO("El nombre del cliente está vacío o es nulo para uno o más clientes"),
    TIPO_CLIENTE_INVALIDO("El tipo de cliente está vacío o es nulo para uno o más clientes"),
    DOCUMENTO_INVALIDO("La validación del documento falló"),
    NUMERO_RADICADO_BB11_INVALIDO("El número de radicado en Bb11 es nulo o está vacío"),
    FECHA_SOLICITUD_BB11_INVALIDA("La fecha de solicitud en Bb11 es nula o está vacía"),
    CABECERA_BB11_INVALIDA("La cabecera en Bb11 no es válida"),
    CABECERA_BB12_INVALIDA("La cabecera en BB12 no es válida:"),
    OBJETO_BB12_NULO("El objeto Bb12 es nulo"),
    NUMERO_RADICADO_INVALIDO("El número de radicado está vacío o es nulo"),
    VERSION_INVALIDA("La versión está vacía o es nula"),
    CLIENTES_INVALIDOS("Los clientes no son válidos: "),
    LISTA_CLIENTES_NULA("La lista de clientes es nula o está vacía"),
    DOCUMENTOS_INVALIDOS("Los documentos no son válidos: "),
    TODOS_CLIENTES_VALIDOS("Todos los clientes son válidos"),
    LISTA_DOCUMENTOS_NULA("La lista de documentos es nula o está vacía"),

    NOMBRE_ARCHIVO_INVALIDO("El nombre del archivo está vacío o es nulo para uno o más documentos"),
    VALIDACION_DATOS_CLIENTES_BB11_FALLO("La validación de datos de los clientes en Bb11 falló"),
    IDENTIFICACION_CLIENTE_VALIDA("La identificación del cliente es válida"),
    IDENTIFICACION_CLIENTE_NULA("La identificación del cliente es nula"),
    NUMERO_DOCUMENTO_CLIENTE_INVALIDO("El número de documento del cliente es nulo o está vacío"),
    TIPO_DOCUMENTO_CLIENTE_INVALIDO("El tipo de documento del cliente es nulo o está vacío"),
    CABECERA_VALIDA("La cabecera es válida"),
    CABECERA_NULA("La cabecera es nula"),
    SYSTEM_ID_CABECERA_INVALIDO("El System ID de la cabecera es nulo o está vacío"),
    MESSAGE_ID_CABECERA_INVALIDO("El Message ID de la cabecera es nulo o está vacío"),
    VALIDACION_USER_ID_FALLO("La validación del UserID falló"),
    USER_ID_VALIDO("El UserID es válido"),
    USER_ID_NULO("El UserID es nulo"),
    USER_NAME_USER_ID_INVALIDO("El UserName del UserID es nulo o está vacío"),
    LISTA_EVENTOS_NULA("La lista de eventos es nula o está vacía"),
    DESCRIPCION_ESTADO_EVENTO_INVALIDA("La descripción del estado del evento no es válida: "),
    ESTADO_EVENTO_INVALIDO("El estado del evento está vacío o es nulo para uno o más eventos"),
    FECHA_EVENTO_INVALIDA("La fecha del evento está vacía o es nula para uno o más eventos"),
    INDICADOR_EVENTO_INVALIDO("El indicador del evento está vacío o es nulo para uno o más eventos"),
    TODOS_EVENTOS_VALIDOS("Todos los eventos son válidos"),
    DESCRIPCION_ESTADO_EVENTO_NULA("La descripción del estado del evento es nula o está vacía"),
    DESCRIPCION_ESTADO_EVENTO_VALIDA("La descripción del estado del evento es válida"),
    CABECERA_NULA_RECIBIDA("cabecera_nula_recibida"),
    SYSTEM_ID_NULO_O_VACIO("SystemId_nulo_o_vacio"),
    MESSAGE_ID_NULO_O_VACIO("MessageId_nulo_o_vacio"),
    USERID_NULO("UserID_Nulo"),
    BB12_NULL("BB12_Nulo"),
    BB12_SIN_RADICADO("Bb12_sin_Radicado"),
    BB12_SIN_VERSION("Bb12_sin_version"),
    TODOS_DATOS_CLIENTES_VALIDOS("Todos los datos de los clientes son válidos");


    private final String mensaje;

    ValidacionBb(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
