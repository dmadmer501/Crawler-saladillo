package es.uca.saladillo.model;

/**
 * Representa las métricas de rendimiento y contenido recopiladas de una página web específica.
 *
 * Esta clase es un contenedor de datos inmutable (un objeto de tipo valor) que almacena
 * información clave como la URL, el título, la cantidad de enlaces detectados y el tiempo
 * empleado en procesar la página.
 */
public class PageMetrics {

    /** La URL de la página web analizada. */
    private final String url;

    /** El título de la página extraído de su cabecera HTML. */
    private final String title;

    /** El número total de enlaces (links) encontrados dentro de la página. */
    private final int linkCount;

    /** El tiempo de ejecución empleado en procesar o descargar la página (generalmente en milisegundos). */
    private final long executionTime;

    /**
     * Construye un nuevo objeto de métricas con toda la información recolectada de la página.
     *
     * @param url           La dirección web (URL) de la página.
     * @param title         El título oficial de la página.
     * @param linkCount     Cantidad de enlaces salientes o internos detectados.
     * @param executionTime Tiempo invertido en el procesamiento de la página.
     */
    public PageMetrics(String url, String title, int linkCount, long executionTime) {
        this.url = url;
        this.title = title;
        this.linkCount = linkCount;
        this.executionTime = executionTime;
    }

    /**
     * Obtiene la URL de la página web a la que corresponden estas métricas.
     *
     * @return Un {@code String} con la dirección URL.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Obtiene el título de la página web analizada.
     *
     * @return El título de la página.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Obtiene la cantidad de enlaces que se contabilizaron en la página.
     *
     * @return El número total de enlaces encontrados.
     */
    public int getLinkCount() {
        return linkCount;
    }

    /**
     * Obtiene el tiempo de ejecución que tomó procesar la página.
     *
     * @return El tiempo medido en milisegundos (o la unidad de tiempo configurada).
     */
    public long getExecutionTime() {
        return executionTime;
    }
}