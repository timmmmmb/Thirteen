package main.java.ch.bfh.thirteen.logger;

import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * The formatter for the FileLogger class
 */
public class LogFormatter extends Formatter {

    /**
     * Makes the format which will be shown in the log file
     * @param record the record to format
     * @return the String for the log file
     */
    @Override
    public String format(LogRecord record) {
        return ">> " + new Date(record.getMillis()) + " >> "
                + record.getMessage() + "\n";
    }

}
