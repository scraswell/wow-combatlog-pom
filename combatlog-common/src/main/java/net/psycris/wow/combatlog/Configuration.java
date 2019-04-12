package net.psycris.wow.combatlog;

import java.text.SimpleDateFormat;

/**
 * Combat log configuration information.
 */
final class Configuration {
    /**
     * The timestamp format used by the log file.
     */
    private static final String TIMESTAMP_FORMAT = "M/d HH:mm:ss.SSS";

    /**
     * The delimiter used between the timestamp and the event data.
     */
    static final String TIMESTAMP_DELIMITER = "  ";

    private Configuration() {
    }

    /**
     * Gets a date formatter for use in parsing data.
     * @return The date formatter.
     */
    static SimpleDateFormat getDateFormatter() {
        return new SimpleDateFormat(TIMESTAMP_FORMAT);
    }
}
