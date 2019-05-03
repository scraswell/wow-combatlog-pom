package net.psycris.wow.combatlog;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parses combat log entries.
 */
public class EntryParser {
    private static final String ENTRY_REGEX = "^(\\d{1,2}\\/\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{3})(\\s{2})(\\w+_?+),.*$";
    private static final Pattern ENTRY_PATTERN = Pattern.compile(ENTRY_REGEX);

    /**
     * Parses a combat log entry.
     */
    public Entry parse(final String rawCombatLogEntry) {

        if (!ENTRY_PATTERN.matcher(rawCombatLogEntry).matches()) {
            throw new IllegalArgumentException("Invalid combat log entry detected.");
        }

        final String[] parts = rawCombatLogEntry
                .split(Configuration.TIMESTAMP_DELIMITER);

        final Date timestamp;
        try {
            timestamp = Configuration.getDateFormatter().parse(parts[0]);
        } catch (final ParseException e) {
            throw new EntryParseException(
                    "Failed to parse a combat log entry.",
                    e);
        }

        final List<String> data = Arrays.asList(parts[1].split(","));

        return new Entry(
                timestamp,
                data);
    }
}
