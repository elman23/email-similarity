package emailsimilarity.similarity.lcs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LCSSimilarityMeasurerTest {

    private final LCSSimilarityMeasurer measurer = new LCSSimilarityMeasurer();

    @Test
    public void textDistanceTest() {
        assertEquals(1.0, measurer.similarity("The quick fox jumped", "The quick fox jumped"), 0.2);
        assertTrue(measurer.similarity("The quick fox jumped", "The fox jumped") >= 0.5);
        assertTrue(measurer.similarity("The quick fox jumped", "The fox") <= 0.5);
        assertTrue(measurer.similarity("The quick fox jumped", "The fox quick jumped") >= 0.5);
        assertTrue(measurer.similarity("The quick for lumped", "The fox quick jumped") > 0.5);
        assertTrue(measurer.similarity("The quick for lumped", "asd asd asd") < 0.5);
    }

    @Test
    public void distanceTest() {
        assertTrue(measurer.similarity("The quick fox jumped", "The fox jumped") >= 0.5);
        assertTrue(measurer.similarity("The quick fox jumped", "The fox") < 0.5);
        assertTrue(measurer.similarity("The quick fox jumped", "The fox quick jumped") >= 0.5);
    }

    @Test
    public void similariyTest() {

        List<String> textsToEdit = getTexts();
        Map<String, Integer> textToIndex = new HashMap<>();
        for (String row : textsToEdit) {
            textToIndex.put(row, textsToEdit.indexOf(row));
        }
        Map<String, Double> maxSimilarities = measurer.maxSimilarity(textsToEdit);
        Map<Integer, Double> maxIndexed = new HashMap<>();
        maxSimilarities.forEach((k, v) -> {
            maxIndexed.put(textToIndex.get(k), v);
        });

        assertEquals(1.0, maxIndexed.get(0), 0.2);
        assertEquals(1.0, maxIndexed.get(1), 0.2);
        assertTrue(maxIndexed.get(2) >= 0.5);
        assertTrue(maxIndexed.get(3) >= 0.5);
        assertTrue(maxIndexed.get(4) >= 0.5);
        assertTrue(maxIndexed.get(5) >= 0.5);
        assertTrue(maxIndexed.get(6) >= 0.5);
        assertTrue(maxIndexed.get(7) <= 0.5);
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
