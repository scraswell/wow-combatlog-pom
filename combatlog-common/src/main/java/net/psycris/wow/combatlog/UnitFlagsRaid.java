package net.psycris.wow.combatlog;

public enum UnitFlagsRaid {
    COMBATLOG_OBJECT_RAIDTARGET8(0x00000080),
    COMBATLOG_OBJECT_RAIDTARGET7(0x00000040),
    COMBATLOG_OBJECT_RAIDTARGET6(0x00000020),
    COMBATLOG_OBJECT_RAIDTARGET5(0x00000010),
    COMBATLOG_OBJECT_RAIDTARGET4(0x00000008),
    COMBATLOG_OBJECT_RAIDTARGET3(0x00000004),
    COMBATLOG_OBJECT_RAIDTARGET2(0x00000002),
    COMBATLOG_OBJECT_RAIDTARGET1(0x00000001);

    private final long value;

    UnitFlagsRaid(final long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
