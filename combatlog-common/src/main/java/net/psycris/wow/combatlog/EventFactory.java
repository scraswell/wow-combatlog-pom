package net.psycris.wow.combatlog;

public class EventFactory {
    private final EntryParser entryParser;

    public EventFactory(
            final EntryParser entryParser) {
        if (entryParser == null) {
            throw new IllegalArgumentException("The entry parser was null.");
        }

        this.entryParser = entryParser;
    }

    public Event createEvent(final String rawCombatLogEntry) {
        final Entry parsedEntry = this
                .getEntryParser()
                .parse(rawCombatLogEntry);

        final String eventType = parsedEntry
                .getData()
                .get(EventArgs.EVENT.getIndex());

        if (EventTypes.isCombatEvent(eventType)) {
            return new CombatEvent(
                    parsedEntry.getTimestamp(),
                    parsedEntry.getData());
        } else {
            return new Event(
                    parsedEntry.getTimestamp(),
                    parsedEntry.getData());
        }
    }

    protected EntryParser getEntryParser() {
        return entryParser;
    }
}
