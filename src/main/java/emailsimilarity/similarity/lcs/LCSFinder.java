package emailsimilarity.similarity.lcs;

public class LCSFinder {

    public String lcs(String s1, String s2) {
        int[][] m = buildLcsMatrix(s1, s2);
        char[] lcs = getLcsSequence(s1, s2, m);
        return new String(lcs);
    }

    private int[][] buildLcsMatrix(String s1, String s2) {
        int l1 = s1.length();
        int l2 = s2.length();

        int[][] m = new int[l1 + 1][l2 + 1];

        for (int i = 0; i <= l1; i++) {
            for (int j = 0; j <= l2; j++) {
                if (i == 0 || j == 0) {
                    m[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    m[i][j] = m[i - 1][j - 1] + 1;
                } else {
                    m[i][j] = Math.max(m[i - 1][j], m[i][j - 1]);
                }
            }
        }

        return m;
    }

    private char[] getLcsSequence(String s1, String s2, int[][] m) {
        int i = s1.length();
        int j = s2.length();

        int l = m[i][j];
        char[] lcs = new char[l];

        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                lcs[l - 1] = s1.charAt(i - 1);
                i--;
                j--;
                l--;
            } else if (m[i - 1][j] > m[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return lcs;
    }
}