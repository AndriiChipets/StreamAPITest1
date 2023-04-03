package ua.prom.roboticsdmc.provider;

import java.util.List;

import ua.prom.roboticsdmc.domain.Racer;

public interface RaceViewProvider {
    
    String provideView(List<Racer> racers);
}
