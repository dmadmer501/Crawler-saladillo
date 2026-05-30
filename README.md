Crawler Saladillo 🕷️

Un rastreador web (Web Crawler) modular y configurable desarrollado en Java 21 utilizando la librería Jsoup para el procesamiento de documentos HTML. Este proyecto recopila de forma automatizada métricas clave de un sitio web, tales como el título de las páginas, el número de enlaces salientes y los tiempos de respuesta, exportando todos los datos estructurados directamente a un archivo en formato JSON.
🚀 Características

    Rastreo Automatizado: Explora de forma iterativa las páginas web a partir de una URL semilla empleando un algoritmo de búsqueda en anchura (BFS).

    Métricas en Tiempo Real: Calcula el tiempo de ejecución de la petición HTTP (executionTime) y contabiliza los enlaces válidos encontrados (linkCount).

    Exportación por Streaming: Diseñado para escribir de manera progresiva en el fichero de salida, minimizando el uso de memoria RAM.

    Configuración Dinámica: Controla el comportamiento del rastreador (límites de páginas, profundidad y rutas) mediante un archivo .properties externo sin necesidad de recompilar el código.

🛠️ Tecnologías Utilizadas

    Lenguaje: Java 21 (compatible con características modernas de programación).

    Gestor de Dependencias: Maven 4.0.0.

    Librería Principal: Jsoup (v1.22.2) para conectar, descargar y parsear el árbol DOM de las páginas web.

📁 Estructura del Proyecto

El código fuente sigue los estándares de arquitectura limpia estructurado por paquetes:
Plaintext

src/main/java/es/uca/saladillo/
├── config/
│   └── ConfigManager.java     # Carga y gestión de propiedades del archivo externo.
├── model/
│   └── PageMetrics.java       # POJO que representa los datos y métricas extraídas de cada URL.
├── service/
│   ├── Crawler.java           # Orquestador del ciclo de vida y bucle de rastreo.
│   ├── LinkExtractor.java     # Extractor y filtrador de enlaces hipertexto (a[href]).
│   ├── PageFetcher.java       # Manejador de las conexiones HTTP mediante Jsoup.
│   └── ResultExporter.java    # Gestor de la persistencia y formateo JSON en disco.
├── util/
│   └── LoggerUtil.java        # Formateador simple para logs informativos y de error.
└── Main.java                  # Punto de entrada de la aplicación.

⚙️ Configuración

Antes de ejecutar la aplicación, puedes ajustar los parámetros de comportamiento modificando los valores del archivo config.properties en la raíz del proyecto:
Properties

# URL de inicio para el rastreo
seed.url=https://www.uca.es/

# Nivel de profundidad máximo a explorar
max.depth=3

# Límite total de páginas únicas a visitar
max.pages=50

# Nombre o ruta del archivo con los resultados guardados
output.file=results.json

💻 Instalación y Ejecución

Sigue estos pasos para clonar el repositorio, compilar el entorno y lanzar el Crawler:
1. Clonar el repositorio
Bash

git clone https://github.com/tu-usuario/crawler-saladillo.git
cd crawler-saladillo

2. Compilar con Maven

Asegúrate de tener instalado el JDK 21 y Maven configurado en tus variables de entorno:
Bash

mvn clean compile

3. Ejecutar la Aplicación

Puedes ejecutar la clase principal directamente utilizando el plugin de Maven:
Bash

mvn exec:java -Dexec.mainClass="es.uca.saladillo.Main"

📊 Ejemplo de Salida

Al finalizar con éxito, el programa generará un reporte con la estructura del archivo configurado (results.json). Cada elemento lucirá de la siguiente manera:
JSON

[
  {
     "url": "https://www.uca.es/",
     "title": "Portal UCA – Portal principal de la Universidad de Cádiz", 
     "linkCount": 225,
     "executionTime": 590
  },
  {
     "url": "https://campusvirtual.uca.es/",
     "title": "Campus Virtual | Universidad de Cádiz UCA", 
     "linkCount": 54,
     "executionTime": 197
  }
]

🛡️ Licencia

Este proyecto se distribuye con fines educativos para la Escuela Oficial o entorno académico asociado. Puedes libremente clonarlo, modificarlo y adaptarlo a tus necesidades de aprendizaje.
