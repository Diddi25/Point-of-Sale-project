package se.kth.iv1350.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "point-of-sale-log.txt";
    private final PrintWriter logFile;

    public LogHandler() throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true), true);
    }

    /**
     * Writes a log entry describing a thrown exception.
     *
     * @param exception The exception that is logged.
     */
    public void logException(Exception exception) {

        String logMessage = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) +
                ", Exception was thrown: " +
                exception.getMessage();

        logFile.println(logMessage);
        exception.printStackTrace(logFile);
        logFile.println("\n");
    }
}
