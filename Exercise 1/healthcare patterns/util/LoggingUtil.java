package util;

import java.io.IOException;
import java.util.logging.*;

/**
 * Small logging utility using java.util.logging
 * Creates a file handler logs/app.log and console handler if not present.
 */
public final class LoggingUtil {
    private LoggingUtil() {}

    public static Logger getLogger(Class<?> cls) {
        Logger logger = Logger.getLogger(cls.getName());
        // Avoid adding handlers multiple times
        if (logger.getHandlers().length == 0) {
            logger.setUseParentHandlers(false); // we'll set our own handlers

            // Console handler
            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.INFO);
            ch.setFormatter(new SimpleFormatter());
            logger.addHandler(ch);

            // File handler
            try {
                java.nio.file.Path logDir = java.nio.file.Paths.get("logs");
                java.nio.file.Files.createDirectories(logDir);
                Handler fh = new FileHandler("logs/app.log", 10 * 1024 * 1024, 5, true);
                fh.setLevel(Level.ALL);
                fh.setFormatter(new SimpleFormatter());
                logger.addHandler(fh);
            } catch (IOException e) {
                logger.log(Level.WARNING, "Unable to create file handler for logging", e);
            }

            logger.setLevel(Level.INFO);
        }
        return logger;
    }
}
