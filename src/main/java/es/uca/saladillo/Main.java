package es.uca.saladillo;

import es.uca.saladillo.config.ConfigManager;
import es.uca.saladillo.service.Crawler;

/**
 * Clase principal que actúa como el punto de entrada (Entry Point) de la aplicación.
 * Se encarga de coordinar la fase inicial del ciclo de vida del programa: levanta
 * el gestor de configuración cargando el archivo de propiedades y arranca el
 * motor de rastreo web ({@link Crawler}).
 */
public class Main {

    /**
     * Metodo principal de ejecución (Main Loop) que invoca la máquina virtual de Java (JVM).
     * Inicializa secuencialmente las dependencias de la aplicación buscando por defecto
     * un archivo llamado {@code config.properties} en la raíz del proyecto para posteriormente
     * delegar el flujo de control al crawler.
     *
     */
    public static void main(String[] args) {
        // Inicializa el gestor de configuración leyendo el archivo de propiedades por defecto
        ConfigManager config = new ConfigManager("config.properties");

        // Instancia el servicio de rastreo inyectándole la configuración procesada
        Crawler crawler = new Crawler(config);

        // Desencadena el bucle de exploración de páginas web
        crawler.start();
    }
}