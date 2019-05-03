package net.psycris.wow.combatlog;

public class UnknownEventTypeException
        extends RuntimeException {
    public UnknownEventTypeException(
            final String message,
            final Exception innerException) {
        super(message, innerException);
    }
}
