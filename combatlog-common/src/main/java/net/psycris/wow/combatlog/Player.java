package net.psycris.wow.combatlog;

public class Player
        extends Subject {

    /**
     * Initializes a new instance of the Subject class.
     *
     * @param id   The subject name.
     * @param name The subject identifier.
     */
    public Player(
            final String id,
            final String name) {
        super(id, name);
    }
}
