package es.uca.saladillo.service;

import es.uca.saladillo.config.ConfigManager;
import es.uca.saladillo.model.PageMetrics;
import es.uca.saladillo.util.LoggerUtil;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Queue;

/**
 * Componente principal encargado de ejecutar el rastreo web (Web Crawling).
 *
 * Esta clase coordina el ciclo de vida del rastreo utilizando una estrategia de búsqueda
 * en anchura (BFS) mediante una cola de URLs. Se apoya en componentes especializados
 * para descargar el contenido, extraer enlaces y exportar las métricas de rendimiento resultantes.
 *
 */
public class Crawler {

    /** Gestor de configuración que contiene las restricciones y parámetros del rastreo. */
    private final ConfigManager config;

    /** Componente encargado de descargar el contenido HTML de las páginas web. */
    private final PageFetcher fetcher;

    /** Componente encargado de extraer los enlaces (URLs) embebidos en el código HTML. */
    private final LinkExtractor extractor;

    /** Componente encargado de volcar las métricas recolectadas en el archivo de salida. */
    private final ResultExporter exporter;

    /** Conjunto de URLs que ya han sido procesadas para evitar redundancias y bucles infinitos. */
    private final Set<String> visitedUrls;

    /** Cola de URLs pendientes de ser rastreadas (mecanismo FIFO para la estrategia BFS). */
    private final Queue<String> urlQueue;

    /**
     * Construye e inicializa el rastreador web con la configuración provista.
     *
     * Instancia internamente los servicios de descarga, extracción y exportación,
     * así como las estructuras de datos necesarias para el seguimiento de los enlaces.
     *
     * @param config El gestor de configuración con los parámetros iniciales de la aplicación.
     */
    public Crawler(ConfigManager config) {
        this.config = config;
        this.fetcher = new PageFetcher();
        this.extractor = new LinkExtractor();
        this.exporter = new ResultExporter(config.getOutputFile());

        this.visitedUrls = new HashSet<>();
        this.urlQueue = new LinkedList<>();
    }

    /**
     * Inicia el proceso de rastreo web a partir de la URL semilla (Seed URL).
     *
     * El algoritmo se ejecuta en bucle descargando páginas, midiendo tiempos de respuesta,
     * descubriendo nuevos enlaces y guardando los resultados. El proceso se detiene
     * cuando no quedan URLs en la cola o cuando se alcanza el límite máximo de páginas
     * definido en la configuración.
     */
    public void start() {
        exporter.startExport();

        String seedUrl = config.getSeedUrl();
        urlQueue.add(seedUrl);

        while(!urlQueue.isEmpty() && visitedUrls.size() < config.getMaxPages()) {
            String currentUrl = urlQueue.poll();

            if (visitedUrls.contains(currentUrl)) {
                continue;
            }

            LoggerUtil.logInfo("Analyzing page " + (visitedUrls.size() + 1) + ": " + currentUrl);

            visitedUrls.add(currentUrl);

            long startTime = System.currentTimeMillis();
            org.jsoup.nodes.Document doc = fetcher.fetchPage(currentUrl);
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            if (doc == null) {
                continue;
            }

            java.util.List<String> discoveredLinks = extractor.extractLinks(doc);
            String title = doc.title();
            int linkCount = discoveredLinks.size();

            PageMetrics metrics = new PageMetrics(currentUrl, title, linkCount, executionTime);
            exporter.exportResult(metrics);

            for (String link : discoveredLinks) {
                if (!visitedUrls.contains(link)) {
                    urlQueue.add(link);
                }
            }
        }

        exporter.endExport();
        LoggerUtil.logInfo("Crawling finished successfully. Total pages visited: " + visitedUrls.size());
    }
}