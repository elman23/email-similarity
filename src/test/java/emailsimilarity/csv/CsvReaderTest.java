package emailsimilarity.csv;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class CsvReaderTest {

    private String fileName = "test.csv";
    private String content = "I,am,a,dog\nI,am,a,cat";

    @Before
    public void prepareEnv() {
        createFile(fileName);
        writeToFile(fileName, content);
    }

    @After
    public void cleanEnv() {
        deleteFile(fileName);
    }

    @Test
    public void readCsvTest() {
        CsvReader reader = new CsvReader();
        List<List<String>> read = reader.readCsv(fileName);

        assert read.size() == 2;

        String[] lines = content.split("\n");
        assert lines.length == 2;

        for (int j = 0; j < lines.length; j++) {
            List<String> firstLine = read.get(j);
            String[] wordsOfFirstLine = lines[j].split(",");
            for (int i = 0; i < wordsOfFirstLine.length; i++) {
                assert firstLine.get(i).equals(wordsOfFirstLine[i]);
            }
        }
    }

    private void createFile(String fileName) {
        File file = new File(fileName);
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void deleteFile(String fileName) {
        File file = new File(fileName);
        try {
            file.delete();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void writeToFile(String fileName, String content) {
        try {
            FileWriter output = new FileWriter(fileName);
            output.write(content);
            output.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }
    }
}
