package ua.prom.roboticsdmc.validator;

import java.io.File;
import java.nio.file.InvalidPathException;

public class FileValidatorImpl implements FileValidator {

    @Override
    public void validate(String abbreviationFilePath, String startsFilePath, String endFilePath) {

        if (abbreviationFilePath == null || startsFilePath == null || endFilePath == null) {
            throw new IllegalArgumentException("File path is null");
        }
        if (abbreviationFilePath.isEmpty() || startsFilePath.isEmpty() || endFilePath.isEmpty()) {
            throw new IllegalArgumentException("File path is empty");
        }
        if (abbreviationFilePath.trim().isEmpty() || startsFilePath.trim().isEmpty() || endFilePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path contains only spaces or (and) tabs");
        }

        File abbreviationFile = new File(abbreviationFilePath);
        File startsFile = new File(startsFilePath);
        File endFile = new File(endFilePath);

        if (abbreviationFile.isDirectory() || startsFile.isDirectory() || endFile.isDirectory()) {
            throw new InvalidPathException("Ither abbreviation or starts or end", "File is directory");
        }
        if (!abbreviationFile.exists() || !startsFile.exists() || !endFile.exists()) {
            throw new InvalidPathException("Ither abbreviation or starts or end", "File doesn't exist");
        }
    }
}
