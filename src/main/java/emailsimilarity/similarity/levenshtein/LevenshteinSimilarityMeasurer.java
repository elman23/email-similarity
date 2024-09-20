package emailsimilarity.similarity.levenshtein;

import emailsimilarity.similarity.SimilarityMeasurer;

import java.util.Arrays;

public class LevenshteinSimilarityMeasurer extends SimilarityMeasurer {

    public double similarity(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return 0.0;
        }
        int longerLen = Math.max(s1.length(), s2.length());
        int distance = distance(s1, s2);
        return ((double) longerLen - distance) / (double) longerLen;
    }

    public int distance(String s1, String s2) {
        int[][] m = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    m[i][j] = j;
                } else if (j == 0) {
                    m[i][j] = i;
                } else {
                    int x = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                    m[i][j] = min(m[i - 1][j - 1] + x, m[i - 1][j] + 1, m[i][j - 1] + 1);
                }
            }
        }

        return m[s1.length()][s2.length()];
    }

    public int min(int... numbers) {
        return Arrays.stream(numbers).min().orElse(Integer.MAX_VALUE);
    }
}
