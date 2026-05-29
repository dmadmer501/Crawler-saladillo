package es.uca.saladillo.service;

import es.uca.saladillo.model.PageMetrics;
import es.uca.saladillo.util.LoggerUtil;

import java.io.FileWriter;
import java.io.IOException;

public class ResultExporter {
    private final String outputFilePath;
    private boolean isFirstRecord;

    public ResultExporter(String outputFilePath) {
        this.outputFilePath = outputFilePath;
        this.isFirstRecord = true;
    }

    public void startExport() {
        try (FileWriter writer = new FileWriter(this.outputFilePath, false)) {
            writer.write("[\n");

        } catch (IOException e) {
            LoggerUtil.logError("Failed to initialize JSON file structure", e);
        }
    }

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

    public void endExport() {
        try (FileWriter writer = new FileWriter(this.outputFilePath, true)) {
            writer.write("\n]");

        } catch (IOException e) {
            LoggerUtil.logError("Failed to close JSON file structure", e);
        }
    }
}
