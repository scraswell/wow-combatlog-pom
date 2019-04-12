package net.psycris.wow.combatlog;

import org.junit.Assert;
import org.junit.Test;

public class EntryUnitTests {
    @Test
    public void canParseKnownEntries() {
        SampleData.getSampleEntries()
                .forEach(entry -> new Entry.Builder().setRawCombatLogEntry(entry).build());
    }

    @Test
    public void canGetEntryData() {
        final String s = SampleData.getSampleEntries().get(0);

        final Entry sampleEntry = new Entry.Builder()
                .setRawCombatLogEntry(s)
                .build();

        Assert.assertEquals(
                "COMBAT_LOG_VERSION",
                sampleEntry.getData().get(EventArgs.EVENT.getIndex()));

        Assert.assertEquals(
                "10",
                sampleEntry.getData().get(1));

        Assert.assertEquals(
                "ADVANCED_LOG_ENABLED",
                sampleEntry.getData().get(2));

        Assert.assertEquals(
                "1",
                sampleEntry.getData().get(3));
    }
}
