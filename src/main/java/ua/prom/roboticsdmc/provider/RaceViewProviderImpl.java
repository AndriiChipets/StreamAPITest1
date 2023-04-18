package ua.prom.roboticsdmc.provider;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import ua.prom.roboticsdmc.domain.Racer;

public class RaceViewProviderImpl implements RaceViewProvider {
    
    private SimpleDateFormat formatter = new SimpleDateFormat("m:ss.SSS");
    private int racersCounter = 0;

    @Override
    public String provideView(List<Racer> racers) {

        StringBuilder viewResult = new StringBuilder();
        String separator = "-".repeat(62);
        int topRacersNumber = 15;
        
        racers.stream()
        .limit(topRacersNumber)
        .forEach(a -> viewResult.append(
                formatLine(a.getRacerName(), a.getTeam(), calculateLapTime(a.getStart(), a.getEnd()) + "\r\n")));
        
        viewResult.append(separator + "\r\n");
        
        racers
        .stream()
        .skip(topRacersNumber).forEach(a -> viewResult.append(
                formatLine(a.getRacerName(), a.getTeam(), calculateLapTime(a.getStart(), a.getEnd()) + "\r\n")));
        
        return String.valueOf(viewResult);
    }

    private String calculateLapTime(LocalDateTime start, LocalDateTime end) {
        return formatter.format(new Date(ChronoUnit.MILLIS.between(start, end)));
    }

    private String formatLine(String racerName, String teamName, String lapTime) {
        racersCounter++;
        return String.format("%-20s %-30s %s", ((racersCounter + ".") + (racerName)), ("| " + teamName), ("| " + lapTime));
    }
}
