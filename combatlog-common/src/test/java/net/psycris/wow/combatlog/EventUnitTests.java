package net.psycris.wow.combatlog;

import org.junit.Assert;
import org.junit.Test;

public class EventUnitTests {
    final EntryParser entryParser = new EntryParser();
    final EventFactory eventFactory = new EventFactory(this.entryParser);

    @Test
    public void canCreateEventFromEntry() {
        final String s = SampleData.getSampleEntries().get(0);

        final Entry sampleEntry = this.entryParser
                .parse(s);

        final Event event = this.eventFactory
                .createEvent(s);

        Assert.assertNotNull(event);
        Assert.assertEquals(sampleEntry.getTimestamp(), event.getTimestamp());
        Assert.assertEquals(sampleEntry.getData(), event.getEventData());
        Assert.assertEquals("COMBAT_LOG_VERSION", event.getEvent());
    }

    @Test
    public void canCreateSpellDamageEvent() {
        final String s = SampleData.getSampleEntries().get(21);

        final Entry sampleEntry = this.entryParser
                .parse(s);

        final Event event = this.eventFactory
                .createEvent(s);

        Assert.assertNotNull(event);
        Assert.assertEquals(event.getClass(), CombatEvent.class);
        Assert.assertNotNull(((CombatEvent)event).getSource());
        Assert.assertNotNull(((CombatEvent)event).getSourceFlags());
        Assert.assertNotNull(((CombatEvent)event).getTarget());
        Assert.assertNotNull(((CombatEvent)event).getTargetFlags());
        Assert.assertEquals(sampleEntry.getTimestamp(), event.getTimestamp());
        Assert.assertEquals(sampleEntry.getData(), event.getEventData());
        Assert.assertEquals("SPELL_DAMAGE", event.getEvent());
    }
}
