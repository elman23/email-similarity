package emailsimilarity.lcs;

import emailsimilarity.SimilarityMeasurer;

public class LCSSimilarityMeasurer extends SimilarityMeasurer {

    private final LCSFinder lcsFinder;

    public LCSSimilarityMeasurer() {
        this.lcsFinder = new LCSFinder();
    }

    public double similarity(String s1, String s2) {
        String lcs1 = lcsFinder.lcs(s1, s2);
        String lcs2 = lcsFinder.lcs(s2, s1);
        int l1 = s1.length();
        int l2 = s2.length();
        int l = l1 > l2 ? l1 : l2;
        double similarity = ((double) Math.max(lcs1.length(), lcs2.length())) / l;
        return similarity;
    }
}
