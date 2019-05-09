package net.psycris.wow.combatlog;

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
    private Integer sourceRaidFlags;

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
    private Integer targetRaidFlags;

    /**
     * Initializes a new instance of the CombatEvent class.
     *
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

    public Integer getSourceRaidFlags() {
        if (this.sourceRaidFlags == null) {
            this.sourceRaidFlags = Integer.parseInt(
                    this.getEventData().get(EventArgs.SOURCE_RAID_FLAGS.getIndex()).replace("0x", ""),
                    16);
        }

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

    public Integer getTargetRaidFlags() {
        if (this.targetRaidFlags == null) {
            this.targetRaidFlags = Integer.parseInt(
                    this.getEventData().get(EventArgs.TARGET_RAID_FLAGS.getIndex()).replace("0x", ""),
                    16);
        }

        return this.targetRaidFlags;
    }

    public boolean sourceIsPlayer() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_PLAYER,
                this.getSourceFlags());
    }

    public boolean sourceIsPet() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_PET,
                this.getSourceFlags());
    }

    public boolean sourceIsNpc() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_NPC,
                this.getSourceFlags());
    }

    public boolean sourceIsPlayerControlled() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_CONTROL_PLAYER,
                this.getSourceFlags());
    }

    public boolean sourceIsNpcControlled() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_CONTROL_NPC,
                this.getSourceFlags());
    }

    public boolean sourceIsFriendly() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_REACTION_FRIENDLY,
                this.getSourceFlags());
    }

    public boolean sourceIsHostile() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_REACTION_HOSTILE,
                this.getSourceFlags());
    }

    public boolean sourceIsTargeted() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TARGET,
                this.getSourceFlags());
    }

    public boolean targetIsPlayer() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_PLAYER,
                this.getTargetFlags());
    }

    public boolean targetIsPet() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_PET,
                this.getTargetFlags());
    }

    public boolean targetIsNpc() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_NPC,
                this.getTargetFlags());
    }

    public boolean targetIsPlayerControlled() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_CONTROL_PLAYER,
                this.getTargetFlags());
    }

    public boolean targetIsNpcControlled() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TYPE_NPC,
                this.getTargetFlags());
    }

    public boolean targetIsFriendly() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_REACTION_FRIENDLY,
                this.getTargetFlags());
    }

    public boolean targetIsHostile() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_REACTION_HOSTILE,
                this.getTargetFlags());
    }

    public boolean targetIsTargeted() {
        return this.checkFlag(
                UnitFlags.COMBATLOG_OBJECT_TARGET,
                this.getTargetFlags());
    }

    /**
     * Checks a flag.
     *
     * @param flag      The flag to be checked.
     * @param unitFlags The actual flags set.
     * @return
     */
    private boolean checkFlag(
            final UnitFlags flag,
            final Integer unitFlags) {
        return flag.getValue() ==
                (unitFlags & flag.getValue());
    }
}
