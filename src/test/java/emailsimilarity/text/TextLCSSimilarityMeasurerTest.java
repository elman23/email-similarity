package emailsimilarity.text;

import org.junit.Test;

import java.util.*;

public class TextLCSSimilarityMeasurerTest {

    private final TextSimilarityMeasurer measurer = new TextSimilarityMeasurer();


    @Test
    public void textDistanceTest() {
        assert measurer.similarity("The quick fox jumped", "The quick fox jumped") == 1;
        assert measurer.similarity("The quick fox jumped", "The fox jumped") >= 0.75;
        assert measurer.similarity("The quick fox jumped", "The fox") >= 0.5;
        assert measurer.similarity("The quick fox jumped", "The fox quick jumped") >= 0.75;
        assert measurer.similarity("The quick for lumped", "The fox quick jumped") > 0.75;
        assert measurer.similarity("The quick for lumped", "asd asd asd") < 0.2;
    }

    @Test
    public void distanceTest() {
        assert measurer.similarity("The quick fox jumped", "The fox jumped") >= 0.75;
        assert measurer.similarity("The quick fox jumped", "The fox") >= 0.5;
        assert measurer.similarity("The quick fox jumped", "The fox quick jumped") >= 0.75;
    }

    @Test
    public void maxSimilariyTest() {

        List<String> textsToEdit = getTexts();
        Map<String, Integer> textToIndex = new HashMap<>();
        for (String row : textsToEdit) {
            textToIndex.put(row, textsToEdit.indexOf(row));
        }
        //for (Map.Entry<String, Integer> entry : textToIndex.entrySet()) {
        //    System.out.println(entry.getValue() + ": " + entry.getKey());
        //}
        Map<String, Double> maxSimilarities = measurer.maxSimilarity(textsToEdit);
        Map<Integer, Double> maxIndexed = new HashMap<>();
        maxSimilarities.forEach((k, v) -> {
            maxIndexed.put(textToIndex.get(k), v);
        });
        //System.out.println(maxIndexed);

        assert maxIndexed.get(0) == 1.0;
        assert maxIndexed.get(1) == 1.0;
        assert maxIndexed.get(2) >= 0.8;
        assert maxIndexed.get(3) >= 0.5;
        assert maxIndexed.get(4) >= 0.5;
        assert maxIndexed.get(5) >= 0.7;
        assert maxIndexed.get(6) >= 0.6;
        assert maxIndexed.get(7) <= 0.2;
    }

    private List<String> getTexts() {
        List<String> texts = new ArrayList<>();
        texts.add("The quick fox jumped");
        texts.add("The fox quick jumped");
        texts.add("The quirk fov jumped");
        texts.add("The fox drives the car");
        texts.add("The jumped");
        texts.add("The fox jumped");
        texts.add("The fox");
        texts.add("I eat biscuits");
        return texts;
    }
}
