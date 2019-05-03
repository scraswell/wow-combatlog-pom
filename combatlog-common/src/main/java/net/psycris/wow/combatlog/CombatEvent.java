package net.psycris.wow.combatlog;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;
import java.util.List;

/**
 * A standard combatlog event.
 */
public class CombatEvent
        extends Event {
    /**
     * The event source.
     */
    private Subject source;

    /**
     * The source flags.
     */
    private Integer sourceFlags;

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
    private Integer targetFlags;

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
        if (this.source == null) {
            this.source = new Subject.Builder()
                    .setId(this.getEventData().get(EventArgs.SOURCE_GUID.getIndex()))
                    .setName(this.getEventData().get(EventArgs.SOURCE_NAME.getIndex()))
                    .build();
        }

        return this.source;
    }

    public Integer getSourceFlags() {
        if (this.sourceFlags == null) {
            this.sourceFlags = Integer.parseInt(
                    this.getEventData().get(EventArgs.SOURCE_FLAGS.getIndex()).replace("0x", ""),
                    16);
        }

        return this.sourceFlags;
    }

    public byte[] getSourceRaidFlags() {
        return this.sourceRaidFlags;
    }

    public Subject getTarget() {
        if (this.target == null) {
            this.target = new Subject.Builder()
                    .setId(this.getEventData().get(EventArgs.TARGET_GUID.getIndex()))
                    .setName(this.getEventData().get(EventArgs.TARGET_NAME.getIndex()))
                    .build();
        }

        return this.target;
    }

    public Integer getTargetFlags() {
        if (this.targetFlags == null) {
            this.targetFlags = Integer.parseInt(
                    this.getEventData().get(EventArgs.TARGET_FLAGS.getIndex()).replace("0x", ""),
                    16);
        }

        return this.targetFlags;
    }

    public byte[] getTargetRaidFlags() {
        return this.targetRaidFlags;
    }
}
