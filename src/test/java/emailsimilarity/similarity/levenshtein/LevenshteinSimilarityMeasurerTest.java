package emailsimilarity.similarity.levenshtein;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LevenshteinSimilarityMeasurerTest {

    private LevenshteinSimilarityMeasurer measurer = new LevenshteinSimilarityMeasurer();

    @Test
    public void testLevenshteinSimilarity() {
        assertEquals(3, measurer.distance("kitten", "sitting"));
        assertEquals(1, measurer.distance("uninformed", "uniformed"));
    }

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
