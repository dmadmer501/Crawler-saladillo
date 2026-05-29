package es.uca.saladillo;

import es.uca.saladillo.config.ConfigManager;
import es.uca.saladillo.service.Crawler;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ConfigManager config = new ConfigManager("config.properties");

        Crawler crawler = new Crawler(config);

        crawler.start();
    }
}