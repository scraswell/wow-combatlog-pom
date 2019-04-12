package net.psycris.wow.combatlog;

import org.junit.Assert;
import org.junit.Test;

public class EventUnitTests {
    @Test
    public void canCreateEventFromEntry() {
        final String s = SampleData.getSampleEntries().get(0);

        final Entry sampleEntry = new Entry.Builder()
                .setRawCombatLogEntry(s)
                .build();

        final Event event = new Event(
                sampleEntry.getTimestamp(),
                sampleEntry.getData());

        Assert.assertNotNull(event);
        Assert.assertEquals(sampleEntry.getTimestamp(), event.getTimestamp());
        Assert.assertEquals(sampleEntry.getData(), event.getEventData());
        Assert.assertEquals("COMBAT_LOG_VERSION", event.getEvent());
    }
}
