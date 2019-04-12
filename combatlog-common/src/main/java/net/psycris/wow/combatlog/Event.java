package net.psycris.wow.combatlog;

import java.util.Date;
import java.util.List;

public class Event
        extends EventBase {

    public Event(
            final Date timestamp,
            final List<String> eventData) {
        super(timestamp, eventData);
    }
}
