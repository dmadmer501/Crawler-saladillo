package es.uca.saladillo.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Componente encargado del análisis de documentos HTML para la extracción de hipervínculos.
 *
 * Utiliza la biblioteca Jsoup para parsear la estructura del documento y filtrar de forma
 * selectiva aquellas etiquetas que contienen enlaces hacia otras páginas web.
 *
 */
public class LinkExtractor {

    /**
     * Analiza el documento HTML provisto y extrae todas las URLs
     * absolutas presentes en las etiquetas de tipo enlace.
     *
     * El metodo busca específicamente las etiquetas {@code <a>} que posean el atributo
     * {@code href}. Si el documento es nulo o no se encuentran enlaces, devuelve una
     * lista vacía.
     *
     *
     * @param doc El documento de Jsoup ({@link Document}) que representa la página web indexada.
     * @return Una lista ({@link List}) de cadenas de texto ({@link String}) que contiene las URLs absolutas encontradas.
     */
    public List<String> extractLinks(Document doc) {
        List<String> links = new ArrayList<>();

        if (doc == null) {
            return links;
        }

        // Selecciona todas las etiquetas de tipo ancla 'a' que tengan un atributo 'href'
        Elements elements = doc.select("a[href]");

        for (Element element : elements) {
            // Obtiene la URL absoluta (convierte rutas relativas a completas basándose en la base URI del documento)
            String url = element.absUrl("href");

            if (!url.isEmpty()) {
                links.add(url);
            }
        }
        return links;
    }
}