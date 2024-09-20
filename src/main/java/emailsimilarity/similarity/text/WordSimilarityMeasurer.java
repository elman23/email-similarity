package emailsimilarity.similarity.text;

import java.util.*;

public class WordSimilarityMeasurer {

    public double wordDistance(String s1, String s2) {

        if (s1 == null || s2 == null) {
            return 0.0;
        }

        String longer = s1.length() > s2.length() ? s1 : s2;
        String shorter = s1.length() <= s2.length() ? s1 : s2;

        if (longer.isEmpty()) {
            return 1.0;
        }
        if (shorter.isEmpty()) {
            return 0.0;
        }

        int numberOfCommonLetters = 0;

        List<Character> onlyLongerChars = getCharList(longer);

        for (char c : shorter.toCharArray()) {
            if (onlyLongerChars.contains(c)) {
                numberOfCommonLetters++;
                onlyLongerChars.remove((Character) c);
            }
        }

        return ((double) numberOfCommonLetters) / longer.length();
    }

    private List<Character> getCharList(String word) {
        List<Character> charList = new ArrayList<>();
        for (char c : word.toCharArray()) {
            charList.add(c);
        }
        return charList;
    }

    public double getHighestSimilarity(String word, List<String> otherWords) {
        double distance = 0.0;
        for (String text : otherWords) {
            double newDistance = wordDistance(word, text);
            if (newDistance > distance) {
                distance = newDistance;
            }
        }
        return distance;
    }

    public String getHighestSimilarityWord(List<String> words1, List<String> words2) {
        if (words2 == null || words1 == null || words1.isEmpty() || words2.isEmpty()) {
            return null;
        }
        double highestSimilarity = 0.0;
        String theWord = words1.get(0);
        for (String word : words1) {
            double highest = getHighestSimilarity(word, words2);
            if (highest > highestSimilarity) {
                highestSimilarity = highest;
                theWord = word;
            }
        }
        return theWord;
    }

    public String getMostSimilarFromList(String word, List<String> otherWords) {
        double distance = 0.0;
        String nearest = null;
        for (String text : otherWords) {
            double newDistance = wordDistance(word, text);
            if (newDistance > distance) {
                distance = newDistance;
                nearest = text;
            }
        }
        return nearest;
    }
}
