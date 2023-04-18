package ua.prom.roboticsdmc.reader;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileReaderImplTest {

    FileReader fileReader = new FileReaderImpl();

    @TempDir
    static Path tempDir;
    
    @Test
    void read_shouldReturnListOfLinesFromFile_whenInputIsCorrectFilePath() throws IOException {

        Path tempFilePath = tempDir.resolve("TempFile.txt");
        String tempFilePathString = tempFilePath.toString();
        
        String fileText = 
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
        
        Files.writeString(tempFilePath, fileText);
                
        List<String> expectedList = new ArrayList<>(Arrays.asList(
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
        
        assertAll(
                () -> assertTrue(Files.exists(tempFilePath), "File should exist"),
                () -> assertEquals(expectedList, fileReader.read(tempFilePathString)));
    }
    
    @Test
    void read_shouldReturnshouldReturnIllegalArgumentException_whenInpetIsNotCorrectPathAndFileNotExist() {

        String notCorrectFilePathString = "notCorectPath";
        assertThrows(IllegalArgumentException.class, () -> fileReader.read(notCorrectFilePathString));
    }
}
