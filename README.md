# Crawler Saladillo 🕷️

Un rastreador web (Web Crawler) modular y configurable desarrollado en **Java 21** utilizando la librería **Jsoup** para el procesamiento de documentos HTML. Este proyecto recopila de forma automatizada métricas clave de un sitio web, tales como el título de las páginas, el número de enlaces salientes y los tiempos de respuesta, exportando todos los datos estructurados directamente a un archivo en formato JSON.

---

## 🚀 Características

* **Rastreo Automatizado:** Explora de forma iterativa las páginas web a partir de una URL semilla empleando un algoritmo de búsqueda en anchura (BFS) gestionado por una cola.
* **Métricas en Tiempo Real:** Calcula el tiempo de ejecución de la petición HTTP (`executionTime`) y contabiliza los enlaces válidos encontrados (`linkCount`).
* **Exportación Eficiente:** Diseñado con un flujo de escritura progresivo (`FileWriter`) que abre, añade registros estructurados en formato JSON y cierra adecuadamente la estructura al finalizar el proceso.
* **Configuración Dinámica:** Controla el comportamiento del rastreador (límites de páginas, profundidad y rutas) mediante un archivo `config.properties` externo sin necesidad de modificar el código fuente[cite: 1].

---

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java 21[cite: 1].
* **Gestor de Dependencias:** Maven (Project Object Model basado en `pom.xml`)[cite: 1].
* **Librería Principal:** [Jsoup (v1.22.2)](https://jsoup.org/) para la conexión y extracción de elementos del árbol DOM[cite: 1].

---

## 📁 Estructura del Proyecto

El código fuente sigue los estándares del repositorio y está organizado de la siguiente manera[cite: 1]:

```text
src/
  main/
    java/
      es/
        uca/
          saladillo/
            config/
              ConfigManager.java    # Gestión y lectura del archivo de propiedades.
            model/
              PageMetrics.java      # Modelo de datos para almacenar las métricas de la página.
            service/
              Crawler.java          # Orquestador del ciclo de rastreo.
              LinkExtractor.java    # Extractor de enlaces hipertexto de elementos HTML.
              PageFetcher.java      # Componente de conexión y descarga de páginas con Jsoup.
              ResultExporter.java   # Manejador de la exportación a formato JSON.
            util/
              LoggerUtil.java       # Utilidad básica para la salida de logs por consola.
            Main.java               # Punto de entrada de la aplicación.
