package net.psycris.wow.combatlog;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Models a raw combat log entry.
 */
public class Entry {
    private final Date timestamp;
    private final List<String> data;

    private Entry(
            final Date timestamp,
            final List<String> data) {
        this.timestamp = timestamp;
        this.data = data;
    }

    /**
     * Gets the timestamp associated with the entry.
     * @return The timestamp associated with the entry.
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the entry data.
     * @return The entry data.
     */
    public List<String> getData() {
        return data;
    }

    /**
     * Models a builder to create entries from a string.
     */
    public static class Builder {
        private static final String ENTRY_REGEX = "^(\\d{1,2}\\/\\d{1,2}\\s\\d{2}:\\d{2}:\\d{2}\\.\\d{3})(\\s{2})(\\w+_?+),.*$";
        private static final Pattern ENTRY_PATTERN = Pattern.compile(ENTRY_REGEX);

        private String rawCombatLogEntry;

        public Builder setRawCombatLogEntry(final String rawCombatLogEntry) {
            if (! ENTRY_PATTERN.matcher(rawCombatLogEntry).matches()) {
                throw new IllegalArgumentException("Invalid combat log entry detected.");
            }

            this.rawCombatLogEntry = rawCombatLogEntry;

            return this;
        }

        public Entry build() {
            final String[] parts = this.rawCombatLogEntry.split(Configuration.TIMESTAMP_DELIMITER);

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
}
