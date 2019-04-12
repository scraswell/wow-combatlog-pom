package net.psycris.wow.combatlog;

public class EntryParseException
        extends RuntimeException {
    public EntryParseException(
            final String message,
            final Exception innerException) {
        super(message, innerException);
    }
}
