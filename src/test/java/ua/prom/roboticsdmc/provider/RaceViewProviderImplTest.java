package ua.prom.roboticsdmc.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.prom.roboticsdmc.domain.Racer;

class RaceViewProviderImplTest {

    RaceViewProvider raceViewProvider = new RaceViewProviderImpl();

    @Test
    void provideView_shouldReturnCorrectTableOfRacers_whenInputIsListOfRacers() {

        List<Racer> racers = new ArrayList<>(); 
        racers.add(Racer.builder()
                .withTeam("FERRARI")
                .withAbbreviation("SVF")
                .withRacerName("Sebastian Vettel")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:58.917"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:03.332")).build());
        racers.add(Racer.builder()
                .withTeam("RED BULL RACING TAG HEUER")
                .withAbbreviation("DRR")
                .withRacerName("Daniel Ricciardo")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:12.054"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:15:24.067")).build());
        racers.add(Racer.builder()
                .withTeam("MERCEDES")
                .withAbbreviation("VBM")
                .withRacerName("Valtteri Bottas")
                .withStart(LocalDateTime.parse("2018-05-24T12:00"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:01:12.434")).build());
        racers.add(Racer.builder()
                .withTeam("MERCEDES")
                .withAbbreviation("LHM")
                .withRacerName("Lewis Hamilton")
                .withStart(LocalDateTime.parse("2018-05-24T12:18:20.125"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:32.585")).build());
        racers.add(Racer.builder()
                .withTeam("MCLAREN RENAULT")
                .withAbbreviation("SVM")
                .withRacerName("Stoffel Vandoorne")
                .withStart(LocalDateTime.parse("2018-05-24T12:18:37.735"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:50.198")).build());
        racers.add(Racer.builder()
                .withTeam("FERRARI")
                .withAbbreviation("KRF")
                .withRacerName("Kimi Raikkonen")
                .withStart(LocalDateTime.parse("2018-05-24T12:03:01.250"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:13.889")).build());
        racers.add(Racer.builder()
                .withTeam("MCLAREN RENAULT")
                .withAbbreviation("FAM")
                .withRacerName("Fernando Alonso")
                .withStart(LocalDateTime.parse("2018-05-24T12:13:04.512"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:14:17.169")).build());
        racers.add(Racer.builder()
                .withTeam("WILLIAMS MERCEDES")
                .withAbbreviation("SSW")
                .withRacerName("Sergey Sirotkin")
                .withStart(LocalDateTime.parse("2018-05-24T12:16:11.648"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:17:24.354")).build());
        racers.add(Racer.builder()
                .withTeam("SAUBER FERRARI")
                .withAbbreviation("CLS")
                .withRacerName("Charles Leclerc")
                .withStart(LocalDateTime.parse("2018-05-24T12:09:41.921"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:10:54.750")).build());
        racers.add(Racer.builder()
                .withTeam("FORCE INDIA MERCEDES")
                .withAbbreviation("SPF")
                .withRacerName("Sergio Perez")
                .withStart(LocalDateTime.parse("2018-05-24T12:12:01.035"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:13:13.883")).build());
        racers.add(Racer.builder()
                .withTeam("HAAS FERRARI")
                .withAbbreviation("RGH")
                .withRacerName("Romain Grosjean")
                .withStart(LocalDateTime.parse("2018-05-24T12:05:14.511"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:06:27.441")).build());
        racers.add(Racer.builder()
                .withTeam("SCUDERIA TORO ROSSO HONDA")
                .withAbbreviation("PGS")
                .withRacerName("Pierre Gasly")
                .withStart(LocalDateTime.parse("2018-05-24T12:07:23.645"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:08:36.586")).build());
        racers.add(Racer.builder()
                .withTeam("RENAULT")
                .withAbbreviation("CSR")
                .withRacerName("Carlos Sainz")
                .withStart(LocalDateTime.parse("2018-05-24T12:03:15.145"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:28.095")).build());
        racers.add(Racer.builder()
                .withTeam("FORCE INDIA MERCEDES")
                .withAbbreviation("EOF")
                .withRacerName("Esteban Ocon")
                .withStart(LocalDateTime.parse("2018-05-24T12:17:58.810"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:11.838")).build());
        racers.add(Racer.builder()
                .withTeam("RENAULT")
                .withAbbreviation("NHR")
                .withRacerName("Nico Hulkenberg")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:49.914"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:02.979")).build());
        racers.add(Racer.builder()
                .withTeam("SCUDERIA TORO ROSSO HONDA")
                .withAbbreviation("BHS")
                .withRacerName("Brendon Hartley")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:51.985"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:16:05.164")).build());
        racers.add(Racer.builder()
                .withTeam("SAUBER FERRARI")
                .withAbbreviation("MES")
                .withRacerName("Marcus Ericsson")
                .withStart(LocalDateTime.parse("2018-05-24T12:04:45.513"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:05:58.778")).build());
        racers.add(Racer.builder()
                .withTeam("WILLIAMS MERCEDES")
                .withAbbreviation("LSW")
                .withRacerName("Lance Stroll")
                .withStart(LocalDateTime.parse("2018-05-24T12:06:13.511"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:07:26.834")).build());
        racers.add(Racer.builder()
                .withTeam("HAAS FERRARI")
                .withAbbreviation("KMH")
                .withRacerName("Kevin Magnussen")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:51.003"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:04.396")).build());

        String expectedTableOfRacers = 
                 "1.Sebastian Vettel   | FERRARI                      | 1:04.415\r\n"
                +"2.Daniel Ricciardo   | RED BULL RACING TAG HEUER    | 1:12.013\r\n"
                +"3.Valtteri Bottas    | MERCEDES                     | 1:12.434\r\n"
                +"4.Lewis Hamilton     | MERCEDES                     | 1:12.460\r\n"
                +"5.Stoffel Vandoorne  | MCLAREN RENAULT              | 1:12.463\r\n"
                +"6.Kimi Raikkonen     | FERRARI                      | 1:12.639\r\n"
                +"7.Fernando Alonso    | MCLAREN RENAULT              | 1:12.657\r\n"
                +"8.Sergey Sirotkin    | WILLIAMS MERCEDES            | 1:12.706\r\n"
                +"9.Charles Leclerc    | SAUBER FERRARI               | 1:12.829\r\n"
                +"10.Sergio Perez      | FORCE INDIA MERCEDES         | 1:12.848\r\n"
                +"11.Romain Grosjean   | HAAS FERRARI                 | 1:12.930\r\n"
                +"12.Pierre Gasly      | SCUDERIA TORO ROSSO HONDA    | 1:12.941\r\n"
                +"13.Carlos Sainz      | RENAULT                      | 1:12.950\r\n"
                +"14.Esteban Ocon      | FORCE INDIA MERCEDES         | 1:13.028\r\n"
                +"15.Nico Hulkenberg   | RENAULT                      | 1:13.065\r\n"
                +"--------------------------------------------------------------\r\n"
                +"16.Brendon Hartley   | SCUDERIA TORO ROSSO HONDA    | 1:13.179\r\n"
                +"17.Marcus Ericsson   | SAUBER FERRARI               | 1:13.265\r\n"
                +"18.Lance Stroll      | WILLIAMS MERCEDES            | 1:13.323\r\n"
                +"19.Kevin Magnussen   | HAAS FERRARI                 | 1:13.393\r\n";

        assertEquals(expectedTableOfRacers, raceViewProvider.provideView(racers));
    }
}
