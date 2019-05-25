package net.psycris.wow.combatlog;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Encounter {
    private Long id;
    private String name;
    private List<Combatant> combatants;
    private final List<Event> events;

    private Encounter(
            final List<Event> events) {
        this.events = events;
    }

    public Long getId() {
        if (this.id == null) {
            this.parseIdentifyingInformation();
        }
        return this.id;
    }

    public String getName() {
        if (this.name == null) {
            this.parseIdentifyingInformation();
        }

        return this.name;
    }

    public List<Combatant> getCombatants() {
        if (this.combatants == null) {
            this.combatants = new ArrayList<>();

            this.combatants = this.getEvents()
                    .stream()
                    .filter(x -> x.getEvent().equals("COMBATANT_INFO"))
                    .map(x -> new Combatant(x.getTimestamp(), x.getEventData()))
                    .collect(Collectors.toList());
        }

        return this.combatants;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    private void parseIdentifyingInformation() {
        final Event event = this.getEvents().get(0);

        this.name = event.getEventData()
                .get(2);

        this.id = Long.parseLong(event.getEventData().get(1));
    }

    public static class Builder {
        private List<Event> events;

        public Encounter build() {
            return new Encounter(
                    this.getEvents());
        }

        public List<Event> getEvents() {
            return events;
        }

        public Builder setEvents(List<Event> events) {
            this.events = events;
            return this;
        }
    }
}
