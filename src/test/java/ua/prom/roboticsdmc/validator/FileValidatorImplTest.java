package ua.prom.roboticsdmc.validator;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileValidatorImplTest {

    @TempDir
    static Path tempDir;

    FileValidator fileValidator = new FileValidatorImpl();

    @Test
    void validate_shouldReturnIllegalArgumentException_whenAbbreviationFilePathIsNull() {

        String abbreviationFilePath = null;
        String startsFilePath = "start.log";
        String endFilePath = "end.log";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenStartsFilePathIsNull() {

        String abbreviationFilePath = "abbreviations.txt";
        String startsFilePath = null;
        String endFilePath = "end.log";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenEndFilePathEndFilePath() {

        String abbreviationFilePath = "abbreviations.txt";
        String startsFilePath = "start.log";
        String endFilePath = null;

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path is null", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenAbbreviationFilePathIsEmpty() {

        String abbreviationFilePath = "";
        String startsFilePath = "start.log";
        String endFilePath = "end.log";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenStartsFilePathIsEmpty() {

        String abbreviationFilePath = "abbreviations.txt";
        String startsFilePath = "";
        String endFilePath = "end.log";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenEndFilePathIsEmpty() {

        String abbreviationFilePath = "abbreviations.txt";
        String startsFilePath = "start.log";
        String endFilePath = "";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path is empty", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenAbbreviationFilePathContainsOnlySpacesOrTabs() {

        String abbreviationFilePath = "   ";
        String startsFilePath = "start.log";
        String endFilePath = "end.log";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path contains only spaces or (and) tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenStartsFilePathContainsOnlySpacesOrTabs() {

        String abbreviationFilePath = "abbreviations.txt";
        String startsFilePath = "     ";
        String endFilePath = "end.log";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path contains only spaces or (and) tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnIllegalArgumentException_whenEndFilePathContainsOnlySpacesOrTabs() {

        String abbreviationFilePath = "abbreviations.txt";
        String startsFilePath = "start.log";
        String endFilePath = " ";

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File path contains only spaces or (and) tabs", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenAbbreviationFilePathIsDirectory() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationDirectoryPath = tempDir.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsFilePath = startsPath.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endFilePath = endPath.toString();

        String fileText = "File exist";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);

        Exception exception = assertThrows(InvalidPathException.class,
                () -> fileValidator.validate(abbreviationDirectoryPath, startsFilePath, endFilePath));
        assertEquals("File is directory: Ither abbreviation or starts or end", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenStartsFilePathIsDirectory() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationFilePath = abbreviationPath.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsDirectoryPath = tempDir.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endFilePath = endPath.toString();

        String fileText = "File exist";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);
        Exception exception = assertThrows(InvalidPathException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsDirectoryPath, endFilePath));
        assertEquals("File is directory: Ither abbreviation or starts or end", exception.getMessage());
    }

    @Test
    void validate_shouldReturnInvalidPathException_whenEndFilePathIsDirectory() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationFilePath = abbreviationPath.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsFilePath = startsPath.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endDirectoryPath = tempDir.toString();

        String fileText = "File exist";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);

        Exception exception = assertThrows(InvalidPathException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, endDirectoryPath));
        assertEquals("File is directory: Ither abbreviation or starts or end", exception.getMessage());
    }

    @Test
    void validater_shouldReturnInvalidPathException_whenAbbreviationFileNotExist() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationFilePath = abbreviationPath.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsFilePath = startsPath.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endFilePath = endPath.toString();

        String fileText = "File exist";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);

        String wrongAbbreviationFilePath = "wrong" + abbreviationFilePath;

        Exception exception = assertThrows(InvalidPathException.class,
                () -> fileValidator.validate(wrongAbbreviationFilePath, startsFilePath, endFilePath));
        assertEquals("File doesn't exist: Ither abbreviation or starts or end", exception.getMessage());
    }

    @Test
    void validater_shouldReturnInvalidPathException_whenStartsFileNotExist() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationFilePath = abbreviationPath.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsFilePath = startsPath.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endFilePath = endPath.toString();

        String fileText = "File exist";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);

        String wrongStartsFilePath = "wrong" + startsFilePath;

        Exception exception = assertThrows(InvalidPathException.class,
                () -> fileValidator.validate(abbreviationFilePath, wrongStartsFilePath, endFilePath));
        assertEquals("File doesn't exist: Ither abbreviation or starts or end", exception.getMessage());
    }

    @Test
    void validater_shouldReturnInvalidPathException_whenEndFileNotExist() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationFilePath = abbreviationPath.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsFilePath = startsPath.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endFilePath = endPath.toString();

        String fileText = "File exist";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);

        String wrongEndFilePath = "wrong" + endFilePath;

        Exception exception = assertThrows(InvalidPathException.class,
                () -> fileValidator.validate(abbreviationFilePath, startsFilePath, wrongEndFilePath));
        assertEquals("File doesn't exist: Ither abbreviation or starts or end", exception.getMessage());
    }

    @Test
    void validater_shouldReturnNothing_whenAllPathAndFilesAreCorrect() throws IOException {

        Path abbreviationPath = tempDir.resolve("AbbreviationTempFile.txt");
        String abbreviationFilePath = abbreviationPath.toString();

        Path startsPath = tempDir.resolve("StartsTempFile.txt");
        String startsFilePath = startsPath.toString();

        Path endPath = tempDir.resolve("EndTempFile.txt");
        String endFilePath = endPath.toString();

        String fileText = "All path is correct";

        Files.writeString(abbreviationPath, fileText);
        Files.writeString(startsPath, fileText);
        Files.writeString(endPath, fileText);

        assertDoesNotThrow(() -> fileValidator.validate(abbreviationFilePath, startsFilePath, endFilePath));
    }
}
