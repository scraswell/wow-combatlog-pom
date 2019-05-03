package net.psycris.wow.combatlog;

import java.util.Arrays;
import java.util.List;

import static net.psycris.wow.combatlog.EventType.*;

public final class EventTypes {
    private static final List<EventType> COMBAT_EVENT_TYPES = Arrays.asList(
            ENVIRONMENTAL_DAMAGE,
            PARTY_KILL,
            RANGE_DAMAGE,
            RANGE_MISSED,
            SPELL_ABSORBED,
            SPELL_AURA_APPLIED,
            SPELL_AURA_APPLIED_DOSE,
            SPELL_AURA_BROKEN,
            SPELL_AURA_BROKEN_SPELL,
            SPELL_AURA_REFRESH,
            SPELL_AURA_REMOVED,
            SPELL_AURA_REMOVED_DOSE,
            SPELL_CAST_FAILED,
            SPELL_CAST_START,
            SPELL_CAST_SUCCESS,
            SPELL_CREATE,
            SPELL_DAMAGE,
            SPELL_DISPEL,
            SPELL_ENERGIZE,
            SPELL_EXTRA_ATTACKS,
            SPELL_HEAL,
            SPELL_HEAL_ABSORBED,
            SPELL_INSTAKILL,
            SPELL_INTERRUPT,
            SPELL_MISSED,
            SPELL_PERIODIC_ENERGIZE,
            SPELL_PERIODIC_DAMAGE,
            SPELL_PERIODIC_HEAL,
            SPELL_PERIODIC_MISSED,
            SPELL_RESURRECT,
            SPELL_STOLEN,
            SPELL_SUMMON,
            SWING_DAMAGE,
            SWING_DAMAGE_LANDED,
            SWING_MISSED,
            UNIT_DIED);

    public static boolean isCombatEvent(final String eventType) {
        return COMBAT_EVENT_TYPES
                .contains(EventType.getByName(eventType));
    }

    private EventTypes() {
    }
}
