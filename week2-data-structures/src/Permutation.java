import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int numberOfElements = Integer.parseInt(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        while (readFromInput(queue))
            ;
        for (int i = 0; i < numberOfElements; i++)
            System.out.println(queue.dequeue());

    }

    private static boolean readFromInput(RandomizedQueue<String> queue) {
        try {
            String value = StdIn.readString();
            queue.enqueue(value);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }
}
