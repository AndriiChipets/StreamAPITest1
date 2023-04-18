package ua.prom.roboticsdmc.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderImpl implements FileReader {

    @Override
    public List<String> read(String path) {

        List<String> linesFromFile = new ArrayList<>();
        Path filePath = Paths.get(path);

        try (Stream<String> streamOfString = Files.lines(filePath)) {
            linesFromFile = streamOfString.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return linesFromFile;
    }
}
