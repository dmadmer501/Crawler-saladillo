package es.uca.saladillo.config;

import es.uca.saladillo.util.LoggerUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private String seedUrl;
    private int maxDepth;
    private int maxPages;
    private String outputFile;

    public ConfigManager(String filePath) {
        loadConfiguration(filePath);
    }

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

    public String getSeedUrl() {
        return seedUrl;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public int getMaxPages() {
        return maxPages;
    }

    public String getOutputFile() {
        return outputFile;
    }
}
