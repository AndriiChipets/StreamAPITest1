package ua.prom.roboticsdmc;

import java.util.Scanner;

import ua.prom.roboticsdmc.parser.RaceParser;
import ua.prom.roboticsdmc.parser.RaceParserImpl;
import ua.prom.roboticsdmc.provider.RaceViewProvider;
import ua.prom.roboticsdmc.provider.RaceViewProviderImpl;
import ua.prom.roboticsdmc.reader.FileReader;
import ua.prom.roboticsdmc.reader.FileReaderImpl;
import ua.prom.roboticsdmc.validator.FileValidator;
import ua.prom.roboticsdmc.validator.FileValidatorImpl;

public class RaceingApplication {

    public static void main(String[] args) {

        FileValidator fileValidator = new FileValidatorImpl();
        FileReader fileReader = new FileReaderImpl();
        RaceParser raceParser = new RaceParserImpl();
        RaceViewProvider raceViewProvider = new RaceViewProviderImpl();

        try (Scanner scanner = new Scanner(System.in)) {
            RaceingStatisticsCalculator raceingStatisticsCalculator = new RaceingStatisticsCalculator(fileValidator,
                    fileReader, raceParser, raceViewProvider);

            System.out.println("Input Abbreviation File Path: ");
            System.out.println("For example: C:\\workspace\\JavaStreamsAPI\\FilesForReading\\abbreviations.txt");
            String abbreviationFilePath = scanner.nextLine();
            System.out.println("Input Starts File Path: ");
            System.out.println("For example: C:\\workspace\\JavaStreamsAPI\\FilesForReading\\start.log");
            String startsFilePath = scanner.nextLine();
            System.out.println("Input End File Path: ");
            System.out.println("For example: C:\\workspace\\JavaStreamsAPI\\FilesForReading\\end.log");
            String endFilePath = scanner.nextLine();

            String result = raceingStatisticsCalculator.provideStatics(abbreviationFilePath, startsFilePath,
                    endFilePath);
            System.out.println();
            System.out.println("Result:");
            System.out.println();
            System.out.println(result);
        }
    }
}
