package emailsimilarity.similarity.lcs;

import emailsimilarity.csv.CsvReader;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class EmailLCSSimilarityTest {

    private CsvReader reader = new CsvReader(";");
    private LCSSimilarityMeasurer measurer = new LCSSimilarityMeasurer();


    @Test
    public void maxSimilariyTest() {

        List<List<String>> textsToEdit = reader.readCsv("emails.csv");
        List<String> texts = new ArrayList<>();
        Map<String, Integer> textToIndex = new HashMap<>();
        for (List<String> row : textsToEdit) {
            textToIndex.put(row.get(0), textsToEdit.indexOf(row));
        }
        for (List<String> row : textsToEdit) {
            texts.add(row.get(0));
        }
        Map<String, Double> maxSimilarities = measurer.maxSimilarity(texts);
        Map<Integer, Double> maxIndexed = new HashMap<>();
        maxSimilarities.forEach((k, v) -> {
            maxIndexed.put(textToIndex.get(k), v);
        });

        assertTrue(maxIndexed.get(0) > 0.8);
        assertTrue(maxIndexed.get(1) > 0.8);
        assertTrue(maxIndexed.get(2) > 0.8);
        assertTrue(maxIndexed.get(3) < 0.5);
        assertTrue(maxIndexed.get(4) < 0.5);
    }
}
