package emailsimilarity.similarity.text;

import emailsimilarity.similarity.SimilarityMeasurer;

import java.util.*;

public class TextSimilarityMeasurer extends SimilarityMeasurer {

    private final String wordSplitter;
    private final WordSimilarityMeasurer wordSimilarityMeasurer;

    public TextSimilarityMeasurer() {
        this.wordSplitter = " ";
        this.wordSimilarityMeasurer = new WordSimilarityMeasurer();
    }

    public double similarity(String text1, String text2) {
        return textSimilarity(text1, text2);
    }

    private double textSimilarity(String s1, String s2) {

        String longer = s1.length() > s2.length() ? s1 : s2;
        String shorter = s1.length() <= s2.length() ? s1 : s2;

        if (longer.isBlank()) {
            return 1.0;
        }

        // Words in the longer text.
        String[] longerWords = longer.split(wordSplitter);

        // Words in the shorter text.
        String[] shorterWords = shorter.split(wordSplitter);

        // Work with lists: initially, one is full, the other empty.
        List<String> onlyLongerWordList = new ArrayList<>(Arrays.asList(longerWords));
        List<String> onlyShorterWordList = new ArrayList<>();

        // Simple approach.
        //double distance = ((double) getNumberOfCommonWords(shorterWords, onlyLongerWordList, onlyShorterWordList));
        //distance /= (distance + onlyLongerWordList.size() + onlyShorterWordList.size());

        // Other approach, consider also words not in common.
        double distance = getNumberOfCommonWords(shorterWords, onlyLongerWordList, onlyShorterWordList);
        double sum = 0.0;

        //for (String word : onlyShorterWordList) {
        //    String nearest = wordSimilarityMeasurer.getMostSimilarFromList(word, onlyLongerWordList);
        //    sum += wordSimilarityMeasurer.wordDistance(word, nearest);
        //}

        while (!onlyShorterWordList.isEmpty()) {
            String higestSimilarityWord = wordSimilarityMeasurer.getHighestSimilarityWord(onlyShorterWordList, onlyLongerWordList);
            onlyShorterWordList.remove(higestSimilarityWord);
            String mostSimilar = wordSimilarityMeasurer.getMostSimilarFromList(higestSimilarityWord, onlyLongerWordList);
            sum += wordSimilarityMeasurer.wordDistance(higestSimilarityWord, mostSimilar);
        }

        distance = distance + sum;

        return distance / longerWords.length;
    }

    private int getNumberOfCommonWords(String[] words, List<String> onlyLonger, List<String> onlyShorter) {
        int numberOfCommonWords = 0;
        for (String word : words) {
            if (!onlyLonger.contains(word)) {
                onlyShorter.add(word);
            } else {
                numberOfCommonWords++;
                onlyLonger.remove(word);
            }
        }
        return numberOfCommonWords;
    }
}
