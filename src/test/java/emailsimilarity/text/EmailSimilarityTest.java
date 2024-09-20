package emailsimilarity.text;

import org.junit.Test;
import emailsimilarity.csv.CsvReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailSimilarityTest {

    private CsvReader reader = new CsvReader(";");
    private TextSimilarityMeasurer measurer = new TextSimilarityMeasurer();


    @Test
    public void maxSimilariyTest() {

        List<List<String>> textsToEdit = reader.readCsv("emails.csv");
        List<String> texts = new ArrayList<>();
        Map<String, Integer> textToIndex = new HashMap<>();
        for (List<String> row : textsToEdit) {
            textToIndex.put(row.get(0), textsToEdit.indexOf(row));
        }
        //for (Map.Entry<String, Integer> entry : textToIndex.entrySet()) {
        //    System.out.println(entry.getValue() + ": " + entry.getKey());
        //}
        for (List<String> row : textsToEdit) {
            texts.add(row.get(0));
        }
        Map<String, Double> maxSimilarities = measurer.maxSimilarity(texts);
        Map<Integer, Double> maxIndexed = new HashMap<>();
        maxSimilarities.forEach((k, v) -> {
            maxIndexed.put(textToIndex.get(k), v);
        });
        //System.out.println(maxIndexed);

        assert maxIndexed.get(0) > 0.8;
        assert maxIndexed.get(1) > 0.8;
        assert maxIndexed.get(2) > 0.8;
        assert maxIndexed.get(3) < 0.5;
        assert maxIndexed.get(4) < 0.5;
    }
}
