package emailsimilarity.text;

import org.junit.Test;

public class WordLCSSimilarityMeasurerTest {

    private WordSimilarityMeasurer measurer = new WordSimilarityMeasurer();

    @Test
    public void wordDistanceTest() {
        assert measurer.wordDistance("fox", "fox") == 1.0;
        assert measurer.wordDistance("for", "fox") > 0.6;
        assert measurer.wordDistance("adda", "assa") == 0.5;
        assert measurer.wordDistance("kitten", "sitting") > 0.5;
        assert measurer.wordDistance("quick", "quirk") > 0.7;
        assert measurer.wordDistance("awer", "zxcv") == 0;
        assert measurer.wordDistance("asd", "asd") == 1;
        assert measurer.wordDistance("asd", "yui") == 0;
    }
}
