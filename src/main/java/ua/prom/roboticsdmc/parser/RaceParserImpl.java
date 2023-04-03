package ua.prom.roboticsdmc.parser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ua.prom.roboticsdmc.domain.Racer;

public class RaceParserImpl implements RaceParser {

    @Override
    public List<Racer> parse(List<String> ends, List<String> starts, List<String> abbreviations) {

        List<Racer> racers = new ArrayList<>();

        for (int i = 0; i < abbreviations.size(); i++) {
            Racer racer = Racer.builder().withTeam(separateTeam(abbreviations.get(i)))
                    .withAbbreviation(separateAbbreviation(abbreviations.get(i)))
                    .withRacerName(separateRacerName(abbreviations.get(i)))
                    .withStart(separateTime(starts, separateAbbreviation(abbreviations.get(i))))
                    .withEnd(separateTime(ends, separateAbbreviation(abbreviations.get(i)))).build();
            racers.add(racer);
        }
        return racers;
    }

    private String separateAbbreviation (String line) {
        return line.substring(0, line.indexOf("_"));
    }
    
    private String separateRacerName (String line) {
        return line.substring(line.indexOf("_") + 1, line.lastIndexOf("_"));
    }
    
    private String separateTeam (String line) {
        return line.substring(line.lastIndexOf("_") + 1);
    }

    private LocalDateTime separateTime(List<String> times, String abbreviation) {
        for (String time : times) {
            if (time.startsWith(abbreviation)) {
                return LocalDateTime.parse(time.substring(3).replace("_", "T"));
            }
        }
        throw new IllegalArgumentException("There is no nessary abbreviation coincidence in \"abbreviation\" and \"time\" files");
    }
}
