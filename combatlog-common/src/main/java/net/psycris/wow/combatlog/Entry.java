package net.psycris.wow.combatlog;

import java.util.Date;
import java.util.List;

/**
 * Models a raw combat log entry.
 */
public class Entry {
    private final Date timestamp;
    private final List<String> data;

    Entry(
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
}
