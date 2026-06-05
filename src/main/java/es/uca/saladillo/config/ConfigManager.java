package es.uca.saladillo.config;

import es.uca.saladillo.util.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Gestor de configuración encargado de cargar y almacenar los parámetros
 * necesarios para la ejecución de la aplicación (generalmente un web crawler).

 * Lee las propiedades desde un archivo plano y, en caso de error en la lectura
 * o en el formateo de los datos, establece valores por defecto de forma segura.
 */
public class ConfigManager {

    /** URL inicial desde la que comenzará el proceso de rastreo. */
    private String seedUrl;

    /** Nivel máximo de profundidad para el rastreo de enlaces. */
    private int maxDepth;

    /** Número máximo de páginas web que se van a procesar. */
    private int maxPages;

    /** Ruta del archivo donde se guardarán los resultados obtenidos. */
    private String outputFile;

    /**
     * Construye un nuevo configurador y desencadena la carga de datos
     * a partir del archivo especificado.
     *
     * @param filePath Ruta absoluta o relativa del archivo de propiedades (.properties).
     */
    public ConfigManager(String filePath) {
        loadConfiguration(filePath);
    }

    /**
     * Carga las propiedades desde el archivo proporcionado.
     * Si ocurre una excepción de E/S o un error de formato numérico,
     * se registran los errores en el log y se asignan valores predeterminados.
     *
     * @param filePath Ruta del archivo de configuración.
     */
    private void loadConfiguration(String filePath) {
        Properties properties = new Properties();

        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);

            this.seedUrl = properties.getProperty("seed.url", "https://example.com");
            this.outputFile = properties.getProperty("output.file", "results.csv");

            this.maxDepth = Integer.parseInt(properties.getProperty("max.depth", "3"));
            this.maxPages = Integer.parseInt(properties.getProperty("max.pages", "50"));

        } catch (IOException e) {
            LoggerUtil.logError("Failed to load configuration file: " + filePath, e);
            this.seedUrl = "https://example.com";
            this.outputFile = "results.csv";
            this.maxDepth = 3;
            this.maxPages = 50;

        } catch (NumberFormatException e) {
            LoggerUtil.logError("Error parsing numeric properties in configuration", e);
            this.maxDepth = 3;
            this.maxPages = 50;
        }
    }

    /**
     * Obtiene la URL semilla configurada para iniciar el rastreo.
     *
     * @return La URL inicial como {@code String}.
     */
    public String getSeedUrl() {
        return seedUrl;
    }

    /**
     * Obtiene el nivel máximo de profundidad permitido en el rastreo.
     *
     * @return El número máximo de niveles de profundidad.
     */
    public int getMaxDepth() {
        return maxDepth;
    }

    /**
     * Obtiene el límite máximo de páginas que la aplicación procesará.
     *
     * @return El número máximo de páginas a rastrear.
     */
    public int getMaxPages() {
        return maxPages;
    }

    /**
     * Obtiene la ruta del archivo de salida donde se exportarán los resultados.
     *
     * @return El nombre o ruta del archivo de salida (ej. "results.csv").
     */
    public String getOutputFile() {
        return outputFile;
    }
}