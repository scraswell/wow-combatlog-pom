package net.psycris.wow.combatlog;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class CombatLogFileReaderTests {
    @Test
    public void test1()
            throws IOException,
            URISyntaxException {

        final URL resource = Thread.currentThread()
                .getContextClassLoader()
                .getResource("logs/log1.txt");

        if (resource != null) {
            try (final CombatLogFileReader combatLogFileReader = new CombatLogFileReader(resource.toURI().toString())) {

                Encounter nextEncounter = combatLogFileReader
                        .getNextEncounter();

                Assert.assertNotNull(nextEncounter);

                Assert.assertEquals(
                        "\"Heartsbane Triad\"",
                        nextEncounter.getName());

                Assert.assertEquals(
                        2113L,
                        nextEncounter.getId().longValue());

                Assert.assertEquals(
                        5,
                        nextEncounter.getCombatants().size());

                nextEncounter = combatLogFileReader
                        .getNextEncounter();

                Assert.assertNotNull(nextEncounter);

                Assert.assertEquals(
                        "\"Heartsbane Triad\"",
                        nextEncounter.getName());

                Assert.assertEquals(
                        2113L,
                        nextEncounter.getId().longValue());

                Assert.assertEquals(
                        5,
                        nextEncounter.getCombatants().size());

                nextEncounter = combatLogFileReader
                        .getNextEncounter();

                Assert.assertNotNull(nextEncounter);

                Assert.assertEquals(
                        "\"Soulbound Goliath\"",
                        nextEncounter.getName());

                Assert.assertEquals(
                        2114L,
                        nextEncounter.getId().longValue());

                Assert.assertEquals(
                        5,
                        nextEncounter.getCombatants().size());
            }
        }
    }

    @Test
    public void test2()
            throws IOException,
            URISyntaxException {

        final URL resource = Thread.currentThread()
                .getContextClassLoader()
                .getResource("logs/log3.txt");

        if (resource != null) {
            try (final CombatLogFileReader combatLogFileReader = new CombatLogFileReader(resource.toURI().toString())) {

                while (true) {
                    long startMillis = System.currentTimeMillis();
                    final Encounter nextEncounter = combatLogFileReader.getNextEncounter();

                    if (nextEncounter == null) {
                        break;
                    }

                    String parsingResult = String.format(
                            "Parsed [%s::%s] containing [%s] events / [%s] combatants in [%s]ms.",
                            nextEncounter.getName(),
                            nextEncounter.getId(),
                            nextEncounter.getEvents().size(),
                            nextEncounter.getCombatants().size(),
                            System.currentTimeMillis() - startMillis);

                    System.out.println(parsingResult);
                }
            }
        }
    }

    @Test
    public void test3()
            throws IOException,
            URISyntaxException {

        for (int i = 1; i < 13; i++) {

            final URL resource = Thread.currentThread()
                    .getContextClassLoader()
                    .getResource(String.format("logs/log%s.txt", i));

            if (resource != null) {
                try (final CombatLogFileReader combatLogFileReader = new CombatLogFileReader(resource.toURI().toString())) {

                    while (true) {
                        long startMillis = System.currentTimeMillis();
                        final Encounter nextEncounter = combatLogFileReader.getNextEncounter();

                        if (nextEncounter == null) {
                            break;
                        }

                        String parsingResult = String.format(
                                "Parsed [%s::%s] containing [%s] events / [%s] combatants in [%s]ms.",
                                nextEncounter.getName(),
                                nextEncounter.getId(),
                                nextEncounter.getEvents().size(),
                                nextEncounter.getCombatants().size(),
                                System.currentTimeMillis() - startMillis);

                        System.out.println(parsingResult);
                    }
                }
            }
        }
    }
}
