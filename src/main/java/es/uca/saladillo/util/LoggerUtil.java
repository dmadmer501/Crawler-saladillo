package es.uca.saladillo.util;

public class LoggerUtil {

    public static void logInfo(String message) {
        System.out.println("[INFO] - " + message);
    }

    public static void logError(String message, Exception e) {
        System.err.println("[ERROR] - " + message);
        if (e != null) {
            System.err.println("[CAUSE] - " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
