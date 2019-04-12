package net.psycris.wow.combatlog;

import java.util.Date;
import java.util.List;

/**
 * A base combat log event from which all events would be derived.
 */
public abstract class EventBase {
    /**
     * The event timestamp.
     */
    private final Date timestamp;

    /**
     * The event name.
     */
    private String event;

    /**
     * The event data.
     */
    private final List<String> eventData;

    protected EventBase(
            final Date timestamp,
            final List<String> eventData) {
        if (timestamp == null) {
            throw new IllegalArgumentException("The timestamp was null.");
        }

        if (eventData == null || eventData.isEmpty()) {
            throw new IllegalArgumentException("The event data was null or empty.");
        }

        this.timestamp = timestamp;
        this.eventData = eventData;
    }

    /**
     * Gets the event timestamp.
     *
     * @return The event timestamp.
     */
    public Date getTimestamp() {
        return this.timestamp;
    }

    /**
     * Gets the event name.
     *
     * @return The event name.
     */
    public String getEvent() {
        if (this.event == null) {
            this.event = this.getEventData()
                    .get(EventArgs.EVENT.getIndex());
        }

        return this.event;
    }

    /**
     * Gets the event data.
     *
     * @return The event data.
     */
    public List<String> getEventData() {
        return this.eventData;
    }
}
