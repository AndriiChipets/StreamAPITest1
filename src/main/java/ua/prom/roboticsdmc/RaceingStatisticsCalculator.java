package ua.prom.roboticsdmc;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;

import ua.prom.roboticsdmc.domain.Racer;
import ua.prom.roboticsdmc.parser.RaceParser;
import ua.prom.roboticsdmc.provider.RaceViewProvider;
import ua.prom.roboticsdmc.reader.FileReader;
import ua.prom.roboticsdmc.validator.FileValidator;

public class RaceingStatisticsCalculator {

    private final FileValidator fileValidator;
    private final FileReader reader;
    private final RaceParser raceParser;
    private final RaceViewProvider raceViewProvider;

    public RaceingStatisticsCalculator(FileValidator fileValidator, FileReader reader, RaceParser raceParser,
            RaceViewProvider raceViewProvider) {
        this.fileValidator = fileValidator;
        this.reader = reader;
        this.raceParser = raceParser;
        this.raceViewProvider = raceViewProvider;
    }

    public String provideStatics(String abbreviationFilePath, String startsFilePath, String endFilePath) {

        fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath);
        List<String> abbreviations = reader.read(abbreviationFilePath);
        List<String> starts = reader.read(startsFilePath);
        List<String> ends = reader.read(endFilePath);

        List<Racer> racers = raceParser.parse(ends, starts, abbreviations);

        racers.sort(Comparator.comparingLong(a -> ChronoUnit.MILLIS.between(a.getStart(), a.getEnd())));

        return raceViewProvider.provideView(racers);
    }
}
