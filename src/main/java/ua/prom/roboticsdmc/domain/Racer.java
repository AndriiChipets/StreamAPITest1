package ua.prom.roboticsdmc.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public class Racer {

    private final String team;
    private final String abbreviation;
    private final String racerName;
    private final LocalDateTime start;
    private final LocalDateTime end;

    private Racer(Builder builder) {
        this.team = builder.team;
        this.abbreviation = builder.abbreviation;
        this.racerName = builder.racerName;
        this.start = builder.start;
        this.end = builder.end;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getTeam() {
        return team;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getRacerName() {
        return racerName;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, abbreviation, racerName, start, end);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Racer racer = (Racer) o;
        return Objects.equals(team, racer.team) 
                && Objects.equals(abbreviation, racer.abbreviation)
                && Objects.equals(racerName, racer.racerName) 
                && Objects.equals(start, racer.start)
                && Objects.equals(end, racer.end);
    }

    @Override
    public String toString() {
        return "Racer [team=" + team + ", abbreviation=" + abbreviation + ", racerName=" + racerName + ", start="
                + start + ", end=" + end + "]";
    }

    public static class Builder {

        private String team;
        private String abbreviation;
        private String racerName;
        private LocalDateTime start;
        private LocalDateTime end;

        private Builder() {
        }

        public Builder withTeam(String team) {
            this.team = team;
            return this;
        }

        public Builder withAbbreviation(String abbreviation) {
            this.abbreviation = abbreviation;
            return this;
        }

        public Builder withRacerName(String racerName) {
            this.racerName = racerName;
            return this;
        }

        public Builder withStart(LocalDateTime start) {
            this.start = start;
            return this;
        }

        public Builder withEnd(LocalDateTime end) {
            this.end = end;
            return this;
        }

        public Racer build() {
            return new Racer(this);
        }
    }
}
