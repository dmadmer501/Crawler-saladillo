package es.uca.saladillo.util;

/**
 * Clase utilitaria para centralizar y estandarizar el registro de eventos (logging) de la aplicación.
 * Proporciona métodos estáticos simples para imprimir mensajes informativos y de error por consola,
 * ayudando a trazar el comportamiento del sistema sin necesidad de instanciar objetos.
 *
 */
public class LoggerUtil {

    /**
     * Registra un mensaje informativo en la salida estándar del sistema.
     * Antepone la etiqueta {@code [INFO]} al mensaje proporcionado para facilitar
     * su filtrado y lectura en la consola convencional ({@link System#out})
     *
     * @param message El texto descriptivo del evento informativo que se desea registrar.
     */
    public static void logInfo(String message) {
        System.out.println("[INFO] - " + message);
    }

    /**
     * Registra un mensaje de error y su causa en la salida de errores estándar del sistema.
     * Envía los datos a través de {@link System#err} (que habitualmente se resalta en rojo
     * en los IDEs). Si se adjunta una excepción no nula, extrae dinámicamente el nombre
     * simple de la clase del error y su mensaje descriptivo para imprimirlos bajo la etiqueta {@code [CAUSE]}.
     *
     * @param message El texto explicativo del error o del contexto donde ocurrió.
     * @param e       La excepción ({@link Exception}) que originó el problema, o {@code null}
     * if solo se desea reportar el mensaje de texto sin un objeto de excepción asociado.
     */
    public static void logError(String message, Exception e) {
        System.err.println("[ERROR] - " + message);
        if (e != null) {
            // Extrae el nombre de la clase (ej. IOException) y el detalle del error
            System.err.println("[CAUSE] - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}