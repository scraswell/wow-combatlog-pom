package net.psycris.wow.combatlog;

/**
 * Convenience ENUM including the indices of well-known combat log entry parameters.
 */
public enum EventArgs {
    /**
     * The index of the event name.
     */
    EVENT(0),

    /**
     * The source GUID index.
     */
    SOURCE_GUID(1),

    /**
     * The source name index.
     */
    SOURCE_NAME(2),

    /**
     * The source flags index.
     */
    SOURCE_FLAGS(3),

    /**
     * The source raid flags index.
     */
    SOURCE_RAID_FLAGS(4),

    /**
     * The target GUID index.
     */
    TARGET_GUID(5),

    /**
     * The target name index.
     */
    TARGET_NAME(6),

    /**
     * The target flags index.
     */
    TARGET_FLAGS(7),

    /**
     * The target raid flags index.
     */
    TARGET_RAID_FLAGS(8);

    private final int index;

    EventArgs(final int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
