package es.uca.saladillo.service;

import es.uca.saladillo.util.LoggerUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Componente encargado de realizar las peticiones HTTP para descargar el contenido de las páginas web.
 *
 * Utiliza la biblioteca Jsoup como cliente HTTP para conectarse a las URLs y parsear el código
 * HTML recuperado en un objeto manejable por la aplicación.
 *
 */
public class PageFetcher {

    /**
     * Realiza una petición HTTP GET a la URL especificada y devuelve su contenido estructurado.
     *
     * Si ocurre un error de red (como un timeout, un error 404, 500 o URL no válida),
     * la excepción {@link IOException} es interceptada de forma segura, se registra el fallo
     * en el sistema de logs y el método retorna {@code null}.
     *
     *
     * @param url La dirección web (URL) de la página que se desea descargar.
     * @return Un objeto {@link Document} de Jsoup que representa el árbol DOM de la página,
     * o {@code null} si la descarga falla por cualquier problema de entrada/salida.
     */
    public Document fetchPage(String url) {
        try {
            // Conecta a la URL mediante HTTP GET y parsea el resultado
            return Jsoup.connect(url).get();

        } catch (IOException e) {
            LoggerUtil.logError("Failed to load the page: ", e);
            return null;
        }
    }
}