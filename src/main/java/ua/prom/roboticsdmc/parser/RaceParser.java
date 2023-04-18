package ua.prom.roboticsdmc.parser;

import java.util.List;

import ua.prom.roboticsdmc.domain.Racer;

public interface RaceParser {
    
        List<Racer> parse(List<String> ends, List<String> starts, List<String> abbreviations);
}
