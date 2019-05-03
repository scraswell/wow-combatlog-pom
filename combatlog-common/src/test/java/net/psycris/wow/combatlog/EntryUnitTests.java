package net.psycris.wow.combatlog;

import org.junit.Assert;
import org.junit.Test;

public class EntryUnitTests {
    final EntryParser entryParser = new EntryParser();
    final EventFactory eventFactory = new EventFactory(this.entryParser);

    @Test
    public void canParseKnownEntries() {
        SampleData.getSampleEntries()
                .forEach(entry -> this.entryParser.parse(entry));
    }

    @Test
    public void canGetEntryData() {
        final String s = SampleData.getSampleEntries().get(0);

        final Entry sampleEntry = this.entryParser
                .parse(s);

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
