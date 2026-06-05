# Crawler Saladillo 

Un rastreador web (Web Crawler) modular y configurable desarrollado en **Java 21** utilizando la librería **Jsoup** para el procesamiento de documentos HTML. Este proyecto recopila de forma automatizada métricas clave de un sitio web, tales como el título de las páginas, el número de enlaces salientes y los tiempos de respuesta, exportando todos los datos estructurados directamente a un archivo en formato JSON.

---

## Características

* **Rastreo Automatizado:** Explora de forma iterativa las páginas web a partir de una URL semilla empleando un algoritmo de búsqueda en anchura (BFS) gestionado por una cola.
* **Métricas en Tiempo Real:** Calcula el tiempo de ejecución de la petición HTTP (`executionTime`) y contabiliza los enlaces válidos encontrados (`linkCount`).
* **Exportación Eficiente:** Diseñado con un flujo de escritura progresivo (`FileWriter`) que abre, añade registros estructurados en formato JSON y cierra adecuadamente la estructura al finalizar el proceso.
* **Configuración Dinámica:** Controla el comportamiento del rastreador (límites de páginas, profundidad y rutas) mediante un archivo `config.properties` externo sin necesidad de modificar el código fuente.

---

## Tecnologías Utilizadas

* **Lenguaje:** Java 21.
* **Gestor de Dependencias:** Maven (Project Object Model basado en `pom.xml`).
* **Librería Principal:** [Jsoup (v1.22.2)](https://jsoup.org/) para la conexión y extracción de elementos del árbol DOM.

---

## Estructura del Proyecto

El código fuente sigue los estándares del repositorio y está organizado de la siguiente manera:

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
```

---

## Configuración

El comportamiento del Crawler se gestiona desde el archivo config.properties ubicado en la raíz del proyecto. Puedes modificar los siguientes parámetros antes de la ejecución:

```
Properties

# URL semilla desde donde comenzará el rastreo
seed.url=[https://www.uca.es/](https://www.uca.es/)

# Profundidad máxima de navegación
max.depth=3

# Número máximo de páginas únicas a visitar
max.pages=50

# Archivo de salida donde se guardarán los resultados obtenidos
output.file=results.json
```

---

## Instalación y Ejecución

Sigue estos pasos para clonar, compilar y ejecutar el proyecto localmente:
1. Clonar el repositorio
```
Bash

git clone [https://github.com/tu-usuario/crawler-saladillo.git](https://github.com/tu-usuario/crawler-saladillo.git)
cd crawler-saladillo
```

2. Compilar con Maven

Asegúrate de contar con el JDK 21 instalado de forma correcta en tu sistema:
Bash
```
mvn clean compile
```

3. Ejecutar la Aplicación

Lanza la clase principal del proyecto mediante el plugin ejecutor de Maven:
Bash
```
mvn exec:java -Dexec.mainClass="es.uca.saladillo.Main"
```

---

## Ejemplo de Resultados

Al finalizar el rastreo, se generará o actualizará el archivo configurado (por ejemplo, results.json), el cual almacena un arreglo con la información recopilada:
```
JSON

[
  {
     "url": "[https://www.uca.es/](https://www.uca.es/)",
     "title": "Portal UCA – Portal principal de la Universidad de Cádiz", 
     "linkCount": 225,
     "executionTime": 590
  },
  {
     "url": "[https://campusvirtual.uca.es/](https://campusvirtual.uca.es/)",
     "title": "Campus Virtual | Universidad de Cádiz UCA", 
     "linkCount": 54,
     "executionTime": 197
  }
]
```
---

## Licencia

Este proyecto se distribuye con fines puramente académicos y educativos asociados a la Universidad de Cádiz (UCA). Siéntete libre de clonarlo, proponer mejoras o adaptar el algoritmo de extracción según tus necesidades de aprendizaje.
