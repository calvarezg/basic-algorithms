import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        double i = 1;
        String champion = "";
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            double p = 1 / i++;
            boolean result = StdRandom.bernoulli(p);
            if (result)
                champion = word;
        }
        StdOut.println(champion);
    }
}
