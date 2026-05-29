# Desarrollo de un Crawler Web 🕷️

Este proyecto consiste en el desarrollo de un **Crawler Web** (araña o bot de rastreo) automatizado programado en Java. Su objetivo principal es recorrer de forma ordenada un conjunto de páginas web a partir de una URL inicial, analizar su contenido HTML mediante la biblioteca **Jsoup** y extraer información estructurada de manera eficiente.

El sistema controla la profundidad del rastreo, gestiona la exclusión de enlaces duplicados o externos, captura errores mediante logs y exporta los resultados analizados a un archivo estructurado (CSV o JSON).

---

## 🚀 Características Principales

* **Configuración Dinámica:** Control total del comportamiento del bot mediante un archivo externo de propiedades.
* **Filtrado por Palabras Clave:** Capacidad de restringir la exploración a páginas cuyo título o URL coincidan con criterios específicos (Mínimo 3 palabras clave).
* **Rastreo Inteligente:** Evita bucles infinitos controlando la profundidad del árbol de navegación y el histórico de URLs ya visitadas.
* **Robustez:** Gestión de excepciones de red, timeouts y errores de parseo con registro estructurado mediante un Logger.
* **Exportación de Datos:** Almacenamiento del progreso en formatos estándar (CSV/JSON) incluyendo métricas de rendimiento por página.

---

## 📂 Estructura del Proyecto

El código fuente sigue un diseño modular respetando el principio de responsabilidad única:

```text
src/
├── crawler/
│   ├── Crawler.java          --> Control principal del bucle y lógica del rastreo.
│   ├── PageFetcher.java      --> Conexión, descarga y parseo de HTML usando Jsoup.
│   ├── LinkExtractor.java    --> Extracción y filtrado de enlaces válidos (internos/únicos).
│   ├── ResultExporter.java   --> Exportación de los datos analizados a CSV o JSON.
│   ├── ConfigManager.java    --> Lectura y tipado del archivo config.properties.
│   └── LoggerUtil.java       --> Configuración del registro de errores y eventos del sistema.
└── Main.java                 --> Punto de entrada de la aplicación.
