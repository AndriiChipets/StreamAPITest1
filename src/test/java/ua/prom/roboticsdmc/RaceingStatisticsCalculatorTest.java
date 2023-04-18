package ua.prom.roboticsdmc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ua.prom.roboticsdmc.domain.Racer;
import ua.prom.roboticsdmc.parser.RaceParser;
import ua.prom.roboticsdmc.provider.RaceViewProvider;
import ua.prom.roboticsdmc.reader.FileReader;
import ua.prom.roboticsdmc.validator.FileValidator;

@ExtendWith(MockitoExtension.class)
class RaceingStatisticsCalculatorTest {

    @Mock
    FileValidator fileValidator;

    @Mock
    FileReader reader;

    @Mock
    RaceParser raceParcer;

    @Mock
    RaceViewProvider raceViewProvider;

    @InjectMocks
    RaceingStatisticsCalculator raceingStatisticsCalculator;

    @TempDir
    static Path tempDir;

    @Test
    void provideStatics_shouldReturnCorrectTable_whenInputPathAndFilesAreCorrect()
            throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        Path endPath = tempDir.resolve("EndTempFile.txt");

        String abbreviationFileText = 
                "DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER\n"
                + "SVF_Sebastian Vettel_FERRARI\n"
                + "LHM_Lewis Hamilton_MERCEDES\n"
                + "KRF_Kimi Raikkonen_FERRARI\n"
                + "VBM_Valtteri Bottas_MERCEDES\n"
                + "EOF_Esteban Ocon_FORCE INDIA MERCEDES\n"
                + "FAM_Fernando Alonso_MCLAREN RENAULT\n"
                + "CSR_Carlos Sainz_RENAULT\n"
                + "SPF_Sergio Perez_FORCE INDIA MERCEDES\n"
                + "PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA\n"
                + "NHR_Nico Hulkenberg_RENAULT\n"
                + "SVM_Stoffel Vandoorne_MCLAREN RENAULT\n"
                + "SSW_Sergey Sirotkin_WILLIAMS MERCEDES\n"
                + "CLS_Charles Leclerc_SAUBER FERRARI\n"
                + "RGH_Romain Grosjean_HAAS FERRARI\n"
                + "BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA\n"
                + "MES_Marcus Ericsson_SAUBER FERRARI\n"
                + "LSW_Lance Stroll_WILLIAMS MERCEDES\n"
                + "KMH_Kevin Magnussen_HAAS FERRARI";
        
        String startsFileText = 
                "SVF2018-05-24_12:02:58.917\n"
                + "NHR2018-05-24_12:02:49.914\n"
                + "FAM2018-05-24_12:13:04.512\n"
                + "KRF2018-05-24_12:03:01.250\n"
                + "SVM2018-05-24_12:18:37.735\n"
                + "MES2018-05-24_12:04:45.513\n"
                + "LSW2018-05-24_12:06:13.511\n"
                + "BHS2018-05-24_12:14:51.985\n"
                + "EOF2018-05-24_12:17:58.810\n"
                + "RGH2018-05-24_12:05:14.511\n"
                + "SSW2018-05-24_12:16:11.648\n"
                + "KMH2018-05-24_12:02:51.003\n"
                + "PGS2018-05-24_12:07:23.645\n"
                + "CSR2018-05-24_12:03:15.145\n"
                + "SPF2018-05-24_12:12:01.035\n"
                + "DRR2018-05-24_12:14:12.054\n"
                + "LHM2018-05-24_12:18:20.125\n"
                + "CLS2018-05-24_12:09:41.921\n"
                + "VBM2018-05-24_12:00:00.000";
        
        String endFileText = 
                "MES2018-05-24_12:05:58.778\n"
                + "RGH2018-05-24_12:06:27.441\n"
                + "SPF2018-05-24_12:13:13.883\n"
                + "LSW2018-05-24_12:07:26.834\n"
                + "DRR2018-05-24_12:15:24.067\n"
                + "NHR2018-05-24_12:04:02.979\n"
                + "CSR2018-05-24_12:04:28.095\n"
                + "KMH2018-05-24_12:04:04.396\n"
                + "BHS2018-05-24_12:16:05.164\n"
                + "SVM2018-05-24_12:19:50.198\n"
                + "KRF2018-05-24_12:04:13.889\n"
                + "VBM2018-05-24_12:01:12.434\n"
                + "SVF2018-05-24_12:04:03.332\n"
                + "EOF2018-05-24_12:19:11.838\n"
                + "PGS2018-05-24_12:08:36.586\n"
                + "SSW2018-05-24_12:17:24.354\n"
                + "FAM2018-05-24_12:14:17.169\n"
                + "CLS2018-05-24_12:10:54.750\n"
                + "LHM2018-05-24_12:19:32.585";

        Files.writeString(abbreviationPath, abbreviationFileText);
        Files.writeString(startsPath, startsFileText);
        Files.writeString(endPath, endFileText);

        String abbreviationFilePath = abbreviationPath.toString();
        String startsFilePath = startsPath.toString();
        String endFilePath = endPath.toString();

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
        
        List<Racer> racers = new ArrayList<>();
        racers.add(Racer.builder()
                .withTeam("RED BULL RACING TAG HEUER")
                .withAbbreviation("DRR")
                .withRacerName("Daniel Ricciardo")
                .withStart(LocalDateTime.parse("2018-05-24T12:14:12.054"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:15:24.067")).build());
        racers.add(Racer.builder()
                .withTeam("FERRARI")
                .withAbbreviation("SVF")
                .withRacerName("Sebastian Vettel")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:58.917"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:03.332")).build());
        racers.add(Racer.builder()
                .withTeam("MERCEDES")
                .withAbbreviation("LHM")
                .withRacerName("Lewis Hamilton")
                .withStart(LocalDateTime.parse("2018-05-24T12:18:20.125"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:32.585")).build());
        racers.add(Racer.builder()
                .withTeam("FERRARI")
                .withAbbreviation("KRF")
                .withRacerName("Kimi Raikkonen")
                .withStart(LocalDateTime.parse("2018-05-24T12:03:01.250"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:13.889")).build());
        racers.add(Racer.builder()
                .withTeam("MERCEDES")
                .withAbbreviation("VBM")
                .withRacerName("Valtteri Bottas")
                .withStart(LocalDateTime.parse("2018-05-24T12:00"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:01:12.434")).build());
        racers.add(Racer.builder()
                .withTeam("FORCE INDIA MERCEDES")
                .withAbbreviation("EOF")
                .withRacerName("Esteban Ocon")
                .withStart(LocalDateTime.parse("2018-05-24T12:17:58.810"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:11.838")).build());
        racers.add(Racer.builder()
                .withTeam("MCLAREN RENAULT")
                .withAbbreviation("FAM")
                .withRacerName("Fernando Alonso")
                .withStart(LocalDateTime.parse("2018-05-24T12:13:04.512"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:14:17.169")).build());
        racers.add(Racer.builder()
                .withTeam("RENAULT")
                .withAbbreviation("CSR")
                .withRacerName("Carlos Sainz")
                .withStart(LocalDateTime.parse("2018-05-24T12:03:15.145"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:28.095")).build());
       racers.add(Racer.builder()
                .withTeam("FORCE INDIA MERCEDES")
                .withAbbreviation("SPF")
                .withRacerName("Sergio Perez")
                .withStart(LocalDateTime.parse("2018-05-24T12:12:01.035"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:13:13.883")).build());
        racers.add(Racer.builder()
                .withTeam("SCUDERIA TORO ROSSO HONDA")
                .withAbbreviation("PGS")
                .withRacerName("Pierre Gasly")
                .withStart(LocalDateTime.parse("2018-05-24T12:07:23.645"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:08:36.586")).build());
        racers.add(Racer.builder()
                .withTeam("RENAULT")
                .withAbbreviation("NHR")
                .withRacerName("Nico Hulkenberg")
                .withStart(LocalDateTime.parse("2018-05-24T12:02:49.914"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:04:02.979")).build());
        racers.add(Racer.builder()
                .withTeam("MCLAREN RENAULT")
                .withAbbreviation("SVM")
                .withRacerName("Stoffel Vandoorne")
                .withStart(LocalDateTime.parse("2018-05-24T12:18:37.735"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:19:50.198")).build());
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
                .withTeam("HAAS FERRARI")
                .withAbbreviation("RGH")
                .withRacerName("Romain Grosjean")
                .withStart(LocalDateTime.parse("2018-05-24T12:05:14.511"))
                .withEnd(LocalDateTime.parse("2018-05-24T12:06:27.441")).build());
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

        doNothing().when(fileValidator).validate(abbreviationFilePath, startsFilePath, endFilePath);
        when(reader.read(abbreviationFilePath)).thenReturn(abbreviations);
        when(reader.read(startsFilePath)).thenReturn(starts);
        when(reader.read(endFilePath)).thenReturn(ends);
        when(raceParcer.parse(ends, starts, abbreviations)).thenReturn(racers);
        when(raceViewProvider.provideView(racers)).thenReturn(expectedTableOfRacers);

        assertEquals(expectedTableOfRacers,
                raceingStatisticsCalculator.provideStatics(abbreviationFilePath, startsFilePath, endFilePath));

        InOrder inOrder = inOrder(fileValidator, reader, raceParcer, raceViewProvider);
        inOrder.verify(fileValidator).validate(abbreviationFilePath, startsFilePath, endFilePath);
        inOrder.verify(reader).read(abbreviationFilePath);
        inOrder.verify(reader).read(startsFilePath);
        inOrder.verify(reader).read(endFilePath);
        inOrder.verify(raceParcer).parse(ends, starts, abbreviations);
        inOrder.verify(raceViewProvider).provideView(racers);
    }
}
