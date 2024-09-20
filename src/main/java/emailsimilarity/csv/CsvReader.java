package emailsimilarity.csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvReader {

    private final String delimiter;

    public CsvReader() {
        delimiter = ",";
    }

    public CsvReader(String delimiter) {
        this.delimiter = delimiter;
    }

    public List<List<String>> readCsv(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }
}
