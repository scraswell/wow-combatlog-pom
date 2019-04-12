package net.psycris.wow.combatlog;

import java.util.Date;
import java.util.List;

/**
 * A standard combatlog event.
 */
public class CombatEvent
        extends EventBase {
    /**
     * The event source.
     */
    private Subject source;

    /**
     * The source flags.
     */
    private byte[] sourceFlags;

    /**
     * The source raid flags.
     */
    private byte[] sourceRaidFlags;

    /**
     * The event target.
     */
    private Subject target;

    /**
     * The target flags.
     */
    private byte[] targetFlags;

    /**
     * The target raid flags.
     */
    private byte[] targetRaidFlags;

    /**
     * Initializes a new instance of the CombatEvent class.
     * @param timestamp The event timestamp.
     * @param eventData The event data.
     */
    public CombatEvent(
            final Date timestamp,
            final List<String> eventData) {
        super(timestamp, eventData);
    }

    public Subject getSource() {
        return this.source;
    }

    public byte[] getSourceFlags() {
        return this.sourceFlags;
    }

    public byte[] getSourceRaidFlags() {
        return this.sourceRaidFlags;
    }

    public Subject getTarget() {
        return this.target;
    }

    public byte[] getTargetFlags() {
        return this.targetFlags;
    }

    public byte[] getTargetRaidFlags() {
        return this.targetRaidFlags;
    }
}
