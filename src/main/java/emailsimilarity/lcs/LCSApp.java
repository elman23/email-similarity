package emailsimilarity.lcs;

public class LCSApp {

    public static void main(String[] args) {
        String S1 = "The brown fox jumps over the lazy dog";
        String S2 = "The fox jumps over the dog";
        LCSFinder lcsFinder = new LCSFinder();
        String lcs = lcsFinder.lcs(S1, S2);
        System.out.println(lcs);
    }
}