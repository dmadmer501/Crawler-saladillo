package es.uca.saladillo.service;

import es.uca.saladillo.model.PageMetrics;
import es.uca.saladillo.util.LoggerUtil;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Componente encargado de exportar de forma secuencial las métricas de las páginas web
 * procesadas a un archivo de texto en formato JSON.
 *
 * La clase gestiona la apertura, estructuración y cierre del archivo para construir
 * un arreglo JSON ({@code [...]}) de manera incremental, optimizando el uso de memoria
 * al escribir registro por registro.
 *
 */
public class ResultExporter {

    /** Ruta o nombre del archivo donde se guardará el resultado de la exportación. */
    private final String outputFilePath;

    /** Flag de control para determinar si se está escribiendo el primer elemento,
     * lo que evita colocar una coma inicial inválida en el formato JSON. */
    private boolean isFirstRecord;

    /**
     * Construye un nuevo exportador configurando la ruta del archivo de salida.
     *
     * @param outputFilePath Ruta absoluta o relativa del archivo de destino.
     */
    public ResultExporter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.isFirstRecord = true;
    }

    /**
     * Inicializa la estructura del archivo JSON.
     *
     * Sobrescribe cualquier archivo existente en la ruta configurada (parámetro {@code append = false})
     * e introduce el corchete de apertura ({@code [}) indispensable para iniciar un array JSON válido.
     *
     */
    public void startExport() {
        try (FileWriter writer = new FileWriter(this.outputFilePath, false)) {
            writer.write("[\n");

        } catch (IOException e) {
            LoggerUtil.logError("Failed to initialize JSON file structure", e);
        }
    }

    /**
     * Convierte las métricas de una página en un objeto JSON y las añade al archivo de salida.
     *
     * El metodo controla automáticamente la inserción de comas ({@code ,}) de separación
     * entre registros, asegurándose de omitirla únicamente para el primer registro insertado.
     * Las escrituras se realizan en modo adjuntar ({@code append = true}).
     *
     *
     * @param metrics El objeto {@link PageMetrics} con los datos de rendimiento y contenido recopilados.
     */
    public void exportResult(PageMetrics metrics) {
        try (FileWriter writer = new FileWriter(this.outputFilePath, true)) {

            if (!isFirstRecord) {
                writer.write(",\n");
            } else {
                isFirstRecord = false;
            }

            String jsonBlock = "{\n" +
                    "   \"url\": \"" + metrics.getUrl() + "\",\n" +
                    "   \"title\": \"" + metrics.getTitle() + "\", \n" +
                    "   \"linkCount\": " + metrics.getLinkCount() + ",\n" +
                    "   \"executionTime\": " + metrics.getExecutionTime() + "\n" +
                    " }";

            writer.write(jsonBlock);
            writer.write("\n");

        } catch (IOException e) {
            LoggerUtil.logError("Failed to export metrics to JSON file: ", e);
        }
    }

    /**
     * Finaliza formalmente la estructura del archivo JSON.
     *
     * Añade el corchete de cierre ({@code ]}) al final del fichero en modo adjuntar para
     * garantizar que el archivo resultante sea un documento JSON sintácticamente correcto.
     *
     */
    public void endExport() {
        try (FileWriter writer = new FileWriter(this.outputFilePath, true)) {
            writer.write("\n]");

        } catch (IOException e) {
            LoggerUtil.logError("Failed to close JSON file structure", e);
        }
    }
}