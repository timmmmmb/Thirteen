package main.java.ch.bfh.thirteen.logger;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A file logger which logs important events and errors in a log file
 */
public class FileLogger {

    private Logger logger;

    /**
     * Constructor for the file logger
     * Sets the console logger to false
     * Log level is set to ALL
     */
    public FileLogger() {
        this.logger = Logger.getLogger(Logger.class.getName());
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);
    }

    /***
     * Logs the log message and log level in the log file
     * Also sets the FileHandler and the Formatter
     * @param logMessage
     * Sets a personalized message for the log file
     * @param level
     * The log level which will be shown in the logfile
     */
    public void log(String logMessage, Level level) {
        try {
            FileHandler logfile = new FileHandler("logfile.txt", true);
            logfile.setFormatter(new LogFormatter());
            logger.addHandler(logfile);
            logger.info(level.toString() + ":" + " " + logMessage);
            logfile.close();
        } catch (IOException e) {
            System.out.println("log file not found");
        } catch (SecurityException e) {
            System.out.println("log failed");
        }
    }
}
