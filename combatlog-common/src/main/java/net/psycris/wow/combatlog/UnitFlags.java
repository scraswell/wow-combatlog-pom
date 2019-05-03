package net.psycris.wow.combatlog;

/**
 * Comparisons:
 * int value = VALUE_TO_CHECK | OTHER_VALUE_TO_CHECK;
 *
 * if ((value & VALUE_TO_CHECK) == VALUE_TO_CHECK)
 * {
 *     // do something--it was set
 * }
 *
 * if ((value & OTHER_VALUE_TO_CHECK) == OTHER_VALUE_TO_CHECK)
 * {
 *     // also set (if it gets in here, then it was defined in
 *     //   value, but it does not guarantee that it was set with
 *     //   OR without other values. To guarantee it's only this
 *     //   value just use == without bitwise logic)
 * }
 */
public enum UnitFlags {
    COMBATLOG_OBJECT_TYPE_OBJECT(0x00004000),
    COMBATLOG_OBJECT_TYPE_GUARDIAN(0x00002000),
    COMBATLOG_OBJECT_TYPE_PET(0x00001000),
    COMBATLOG_OBJECT_TYPE_NPC(0x00000800),
    COMBATLOG_OBJECT_TYPE_PLAYER(0x00000400),
    COMBATLOG_OBJECT_CONTROL_NPC(0x00000200),
    COMBATLOG_OBJECT_CONTROL_PLAYER(0x00000100),
    COMBATLOG_OBJECT_REACTION_HOSTILE(0x00000040),
    COMBATLOG_OBJECT_REACTION_NEUTRAL(0x00000020),
    COMBATLOG_OBJECT_REACTION_FRIENDLY(0x00000010),
    COMBATLOG_OBJECT_AFFILIATION_OUTSIDER(0x00000008),
    COMBATLOG_OBJECT_AFFILIATION_RAID(0x00000004),
    COMBATLOG_OBJECT_AFFILIATION_PARTY(0x00000002),
    COMBATLOG_OBJECT_AFFILIATION_MINE(0x00000001),
    COMBATLOG_OBJECT_NONE(0x80000000),
    COMBATLOG_OBJECT_MAINASSIST(0x00080000),
    COMBATLOG_OBJECT_MAINTANK(0x00040000),
    COMBATLOG_OBJECT_FOCUS(0x00020000),
    COMBATLOG_OBJECT_TARGET(0x00010000);

    private final long value;

    UnitFlags(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
