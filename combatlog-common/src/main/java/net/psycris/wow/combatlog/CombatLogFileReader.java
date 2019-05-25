package net.psycris.wow.combatlog;

import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class CombatLogFileReader
        implements Closeable {
    private final EntryParser entryParser = new EntryParser();
    private final String combatLogFilePath;

    private long linesProcessed = 0;
    private FileSystem jarFile;
    private Stream<String> lines;
    private Iterator<String> lineIterator;

    public CombatLogFileReader(
            final String combatLogFilePath) {
        if (combatLogFilePath == null
                || combatLogFilePath.isEmpty()) {
            throw new IllegalArgumentException("The file path was null or empty.");
        }

        this.combatLogFilePath = combatLogFilePath;
    }

    public Encounter getNextEncounter()
            throws IOException {
        final List<Event> encounterEvents = new ArrayList<>();

        Encounter encounter = null;
        Event encounterStartEvent = null;

        while (this.getLineIterator().hasNext()) {
            final String line = this.getLineIterator().next();
            final Entry entry = this.getEntryParser().parse(line);

            final Event event = new Event(
                    entry.getTimestamp(),
                    entry.getData());

            if (event.getEvent().equals("ENCOUNTER_START")) {
                encounterStartEvent = event;
            }

            if (encounterStartEvent != null) {
                encounterEvents.add(event);
            }

            if (encounterStartEvent != null
                    && event.getEvent().equals("ENCOUNTER_END")) {

                encounter = new Encounter.Builder()
                        .setEvents(encounterEvents)
                        .build();
                break;
            }
        }

        return encounter;
    }

    public EntryParser getEntryParser() {
        return this.entryParser;
    }

    protected String getCombatLogFilePath() {
        return this.combatLogFilePath;
    }

    protected Iterator<String> getLineIterator()
            throws IOException {
        if (this.lineIterator == null) {
            this.lineIterator = this.getLines().iterator();
        }

        return this.lineIterator;
    }

    protected boolean isJar() {
        return this.getCombatLogFilePath()
                .contains("!");
    }

    protected FileSystem getJarFile()
            throws IOException {

        if (this.isJar()
                && this.jarFile == null) {
            final Map<String, String> environment = new HashMap<>();
            final String[] uriParts = this.getCombatLogFilePath()
                    .split("!");

            this.jarFile = FileSystems.newFileSystem(
                    URI.create(uriParts[0]),
                    environment);
        }

        return this.jarFile;
    }

    protected Stream<String> getLines()
            throws IOException {
        if (this.isJar()) {

            final String[] uriParts = this.getCombatLogFilePath()
                    .split("!");

            this.lines = Files.lines(
                    this.getJarFile().getPath(uriParts[1]));
        } else {

            this.lines = Files.lines(
                    Paths.get(this.getCombatLogFilePath()));
        }

        return this.lines;
    }

    @Override
    public void close()
            throws IOException {
        if (this.getLines() != null) {
            this.getLines()
                    .close();
        }

        if (this.getJarFile() != null) {
            this.getJarFile()
                    .close();
        }
    }

    public class CombatLogLineConsumer
            implements Consumer<String> {
        private final List<Event> eventList = new ArrayList<>();
        private final EntryParser entryParser = new EntryParser();

        public List<Event> getEventList() {
            return this.eventList;
        }

        protected EntryParser getEntryParser() {
            return this.entryParser;
        }

        @Override
        public void accept(final String line) {
            final Entry entry = this.getEntryParser()
                    .parse(line);

            final Event event = new Event(
                    entry.getTimestamp(),
                    entry.getData());

            this.getEventList()
                    .add(event);
        }

        @Override
        public Consumer<String> andThen(final Consumer<? super String> after) {
            return null;
        }
    }
}
