package net.psycris.wow.combatlog;

/**
 * Models a subject from a combat log event.
 */
public class Subject {
    /**
     * The subject identifier.
     */
    private final String id;

    /**
     * The subject name.
     */
    private final String name;

    /**
     * Initializes a new instance of the Subject class.
     * @param id The subject name.
     * @param name The subject identifier.
     */
    public Subject(
            final String id,
            final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the subject identifier.
     *
     * @return The subject identifier.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Gets the subject name.
     *
     * @return The subject name.
     */
    public String getName() {
        return this.name;
    }

    public static class Builder {
        /**
         * The subject identifier.
         */
        private String id;

        /**
         * The subject name.
         */
        private String name;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Subject build() {
            return new Subject(
                    this.id,
                    this.name);
        }
    }
}
