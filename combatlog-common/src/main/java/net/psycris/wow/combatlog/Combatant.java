package net.psycris.wow.combatlog;

import java.util.Date;
import java.util.List;

public class Combatant
        extends Entry {
    Combatant(
            final Date timestamp,
            final List<String> data) {
        super(timestamp, data);
    }
}
