package emailsimilarity;

import java.util.*;

public abstract class SimilarityMeasurer {


    public abstract double similarity(String text1, String text2);

    public Map<String, List<Double>> similarities(List<String> texts) {
        Map<String, List<Double>> similarityMap = new HashMap<>();

        for (String text : texts) {
            List<Double> similarities = new ArrayList<>();
            for (String other : texts) {
                if (!other.equals(text)) {
                    similarities.add(similarity(text, other));
                }
            }
            similarityMap.put(text, similarities);
        }
        return similarityMap;
    }

    public Map<String, Double> maxSimilarity(List<String> texts) {
        Map<String, Double> maxSimilarityMap = new HashMap<>();
        Map<String, List<Double>> similarityMap = similarities(texts);
        similarityMap.forEach((k, v) -> maxSimilarityMap.put(k, Collections.max(v)));
        return maxSimilarityMap;
    }
}
