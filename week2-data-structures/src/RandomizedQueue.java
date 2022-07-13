import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Object[] elements;
    private int size = 0;
    private int start;
    private int end;

    public RandomizedQueue() {
        int initialSize = 3;
        start = StdRandom.uniform(0, initialSize);
        end = start;
        elements = new Object[initialSize];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        assureIsNotNull(item);
        resizeIfNecessary();
        if (goesToTheLeft())
            enqueueToTheLeft(item);
        else
            enqueueToTheRight(item);
        size++;
    }

    private void enqueueToTheLeft(Item item) {
        if (start == end)
            end++;
        elements[start--] = item;
    }

    private void enqueueToTheRight(Item item) {
        if (start == end)
            start--;
        elements[end++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        int indexToRemove;
        if (size == 0)
            throw new NoSuchElementException();
        else if (goesToTheLeft()) {
            indexToRemove = start + 1;
            start++;
        } else {
            indexToRemove = end - 1;
            end--;
        }
        Item returnValue = (Item) elements[indexToRemove];
        elements[indexToRemove] = null;
        size--;
        return returnValue;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0)
            throw new NoSuchElementException();
        int element = StdRandom.uniform(start + 1, end);
        return (Item) elements[element];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return null;
    }

    private void assureIsNotNull(Item value) {
        if (value == null)
            throw new IllegalArgumentException();
    }

    private void resizeIfNecessary() {
        if (start < 0 || end == elements.length) {
            int center = this.elements.length;
            int newSize = this.elements.length * 2 + 1;
            Object[] newElements = new Object[newSize];
            int currentPosition = center - ((size + 1) / 2);
            for (int i = start + 1; i < end; i++)
                newElements[currentPosition++] = this.elements[i];
            elements = newElements;
            start = center - ((size + 1) / 2) - 1;
            end = currentPosition;
        }
    }

    private boolean goesToTheLeft() {
        return StdRandom.uniform() > 0.5;
    }

    public void print() {
        for (int i = 0; i < elements.length; i++)
            if (elements[i] != null)
                System.out.print(elements[i] + " ");
            else
                System.out.print("0 ");
        System.out.println();
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
        queue.print();
        for (int i = 1; i < 10; i++) {
            queue.enqueue(i);
            queue.print();
        }
    }
}
