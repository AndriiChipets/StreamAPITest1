package ua.prom.roboticsdmc.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import ua.prom.roboticsdmc.domain.Racer;

class RaceParserImplTest {

    RaceParser raceParser = new RaceParserImpl();

    @Test
    void parse_shouldReturnCorrectListOfRacers_whenInputIsCorrectThreeListsWithCoincidenceForAllAbbreviations() {

        List<String> ends = new ArrayList<>(Arrays.asList(
                "MES2018-05-24_12:05:58.778",
                "RGH2018-05-24_12:06:27.441",
                "SPF2018-05-24_12:13:13.883",
                "LSW2018-05-24_12:07:26.834",
                "DRR2018-05-24_12:15:24.067",
                "NHR2018-05-24_12:04:02.979",
                "CSR2018-05-24_12:04:28.095",
                "KMH2018-05-24_12:04:04.396",
                "BHS2018-05-24_12:16:05.164",
                "SVM2018-05-24_12:19:50.198",
                "KRF2018-05-24_12:04:13.889",
                "VBM2018-05-24_12:01:12.434",
                "SVF2018-05-24_12:04:03.332",
                "EOF2018-05-24_12:19:11.838",
                "PGS2018-05-24_12:08:36.586",
                "SSW2018-05-24_12:17:24.354",
                "FAM2018-05-24_12:14:17.169",
                "CLS2018-05-24_12:10:54.750",
                "LHM2018-05-24_12:19:32.585"));
       
        List<String> starts = new ArrayList<>(Arrays.asList(
                "SVF2018-05-24_12:02:58.917",
                "NHR2018-05-24_12:02:49.914",
                "FAM2018-05-24_12:13:04.512",
                "KRF2018-05-24_12:03:01.250",
                "SVM2018-05-24_12:18:37.735",
                "MES2018-05-24_12:04:45.513",
                "LSW2018-05-24_12:06:13.511",
                "BHS2018-05-24_12:14:51.985",
                "EOF2018-05-24_12:17:58.810",
                "RGH2018-05-24_12:05:14.511",
                "SSW2018-05-24_12:16:11.648",
                "KMH2018-05-24_12:02:51.003",
                "PGS2018-05-24_12:07:23.645",
                "CSR2018-05-24_12:03:15.145",
                "SPF2018-05-24_12:12:01.035",
                "DRR2018-05-24_12:14:12.054",
                "LHM2018-05-24_12:18:20.125",
                "CLS2018-05-24_12:09:41.921",
                "VBM2018-05-24_12:00:00.000"));
        
        List<String> abbreviations = new ArrayList<>(Arrays.asList(
                "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
                "SVF_Sebastian Vettel_FERRARI",
                "LHM_Lewis Hamilton_MERCEDES",
                "KRF_Kimi Raikkonen_FERRARI",
                "VBM_Valtteri Bottas_MERCEDES",
                "EOF_Esteban Ocon_FORCE INDIA MERCEDES",
                "FAM_Fernando Alonso_MCLAREN RENAULT",
                "CSR_Carlos Sainz_RENAULT",
                "SPF_Sergio Perez_FORCE INDIA MERCEDES",
                "PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA",
                "NHR_Nico Hulkenberg_RENAULT",
                "SVM_Stoffel Vandoorne_MCLAREN RENAULT",
                "SSW_Sergey Sirotkin_WILLIAMS MERCEDES",
                "CLS_Charles Leclerc_SAUBER FERRARI",
                "RGH_Romain Grosjean_HAAS FERRARI",
                "BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA",
                "MES_Marcus Ericsson_SAUBER FERRARI",
                "LSW_Lance Stroll_WILLIAMS MERCEDES",
                "KMH_Kevin Magnussen_HAAS FERRARI"));
        
        List<Racer> expectedListOfRacers = new ArrayList<>();        
        expectedListOfRacers.add(Racer.builder()
                .withTeam("RED BULL RACING TAG HEUER")
                .withAbbreviation("DRR")
                .withRacerName("Daniel Ricciardo")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:12.054"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:15:24.067")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("FERRARI")
                .withAbbreviation("SVF")
                .withRacerName("Sebastian Vettel")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:58.917"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:03.332")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("MERCEDES")
                .withAbbreviation("LHM")
                .withRacerName("Lewis Hamilton")
                .withStart(LocalDateTime.parse("2018-05-24T12:18:20.125"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:32.585")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("FERRARI")
                .withAbbreviation("KRF")
                .withRacerName("Kimi Raikkonen")
                .withStart(LocalDateTime.parse("2018-05-24T12:03:01.250"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:13.889")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("MERCEDES")
                .withAbbreviation("VBM")
                .withRacerName("Valtteri Bottas")
                .withStart(LocalDateTime.parse("2018-05-24T12:00"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:01:12.434")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("FORCE INDIA MERCEDES")
                .withAbbreviation("EOF")
                .withRacerName("Esteban Ocon")
                .withStart(LocalDateTime.parse("2018-05-24T12:17:58.810"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:11.838")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("MCLAREN RENAULT")
                .withAbbreviation("FAM")
                .withRacerName("Fernando Alonso")
                .withStart(LocalDateTime.parse("2018-05-24T12:13:04.512"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:14:17.169")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("RENAULT")
                .withAbbreviation("CSR")
                .withRacerName("Carlos Sainz")
                .withStart(LocalDateTime.parse("2018-05-24T12:03:15.145"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:28.095")).build());
       expectedListOfRacers.add(Racer.builder()
                .withTeam("FORCE INDIA MERCEDES")
                .withAbbreviation("SPF")
                .withRacerName("Sergio Perez")
                .withStart(LocalDateTime.parse("2018-05-24T12:12:01.035"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:13:13.883")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("SCUDERIA TORO ROSSO HONDA")
                .withAbbreviation("PGS")
                .withRacerName("Pierre Gasly")
                .withStart(LocalDateTime.parse("2018-05-24T12:07:23.645"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:08:36.586")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("RENAULT")
                .withAbbreviation("NHR")
                .withRacerName("Nico Hulkenberg")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:49.914"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:02.979")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("MCLAREN RENAULT")
                .withAbbreviation("SVM")
                .withRacerName("Stoffel Vandoorne")
                .withStart(LocalDateTime.parse("2018-05-24T12:18:37.735"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:50.198")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("WILLIAMS MERCEDES")
                .withAbbreviation("SSW")
                .withRacerName("Sergey Sirotkin")
                .withStart(LocalDateTime.parse("2018-05-24T12:16:11.648"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:17:24.354")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("SAUBER FERRARI")
                .withAbbreviation("CLS")
                .withRacerName("Charles Leclerc")
                .withStart(LocalDateTime.parse("2018-05-24T12:09:41.921"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:10:54.750")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("HAAS FERRARI")
                .withAbbreviation("RGH")
                .withRacerName("Romain Grosjean")
                .withStart(LocalDateTime.parse("2018-05-24T12:05:14.511"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:06:27.441")).build());
       expectedListOfRacers.add(Racer.builder()
                .withTeam("SCUDERIA TORO ROSSO HONDA")
                .withAbbreviation("BHS")
                .withRacerName("Brendon Hartley")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:51.985"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:16:05.164")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("SAUBER FERRARI")
                .withAbbreviation("MES")
                .withRacerName("Marcus Ericsson")
                .withStart(LocalDateTime.parse("2018-05-24T12:04:45.513"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:05:58.778")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("WILLIAMS MERCEDES")
                .withAbbreviation("LSW")
                .withRacerName("Lance Stroll")
                .withStart(LocalDateTime.parse("2018-05-24T12:06:13.511"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:07:26.834")).build());
        expectedListOfRacers.add(Racer.builder()
                .withTeam("HAAS FERRARI")
                .withAbbreviation("KMH")
                .withRacerName("Kevin Magnussen")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:51.003"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:04.396")).build());

        assertEquals(expectedListOfRacers, raceParser.parse(ends, starts, abbreviations));
    }
    
    @Test
    void parse_shouldReturnIllegalArgumentException_whenInputHasNoNessaryAbbreviationCoincidenceInFiles() {

        List<String> ends = new ArrayList<>(Arrays.asList("MES2018-05-24_12:05:58.778"));
        List<String> starts = new ArrayList<>(Arrays.asList("MES2018-05-24_12:04:45.513"));
        List<String> abbreviations = new ArrayList<>(Arrays.asList("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER"));

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> raceParser.parse(ends, starts, abbreviations));
        assertEquals("There is no nessary abbreviation coincidence in \"abbreviation\" and \"time\" files",
                exception.getMessage());
    }
}
