package emailsimilarity;

import emailsimilarity.csv.CsvReader;
import emailsimilarity.similarity.SimilarityMeasurer;
import emailsimilarity.similarity.lcs.LCSSimilarityMeasurer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCSSimilarityApp {

    private static String fileName = "emails.csv";
    private static CsvReader reader = new CsvReader(";");
    private static SimilarityMeasurer measurer = new LCSSimilarityMeasurer();

    public static void main(String[] args) {

        List<List<String>> textsToEdit = reader.readCsv(fileName);
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

        for (Map.Entry<String, Double> e : maxSimilarities.entrySet()) {
            System.out.println("[" + e.getKey() + "] spam" + ": " + (e.getValue() > 0.5));
        }
    }
}
