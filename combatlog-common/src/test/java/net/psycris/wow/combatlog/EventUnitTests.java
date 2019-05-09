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
        Assert.assertTrue(((CombatEvent)event).sourceIsPlayer());
        Assert.assertFalse(((CombatEvent)event).targetIsPlayer());
        Assert.assertFalse(((CombatEvent)event).sourceIsPet());
        Assert.assertFalse(((CombatEvent)event).targetIsPet());
        Assert.assertFalse(((CombatEvent)event).sourceIsNpc());
        Assert.assertFalse(((CombatEvent)event).sourceIsNpcControlled());
        Assert.assertTrue(((CombatEvent)event).sourceIsPlayerControlled());
        Assert.assertTrue(((CombatEvent)event).sourceIsFriendly());
        Assert.assertFalse(((CombatEvent)event).sourceIsHostile());
        Assert.assertFalse(((CombatEvent)event).sourceIsTargeted());
        Assert.assertTrue(((CombatEvent)event).targetIsNpc());
        Assert.assertTrue(((CombatEvent)event).targetIsNpcControlled());
        Assert.assertFalse(((CombatEvent)event).targetIsPlayerControlled());
        Assert.assertFalse(((CombatEvent)event).targetIsFriendly());
        Assert.assertTrue(((CombatEvent)event).targetIsHostile());
        Assert.assertTrue(((CombatEvent)event).targetIsTargeted());
    }
}
