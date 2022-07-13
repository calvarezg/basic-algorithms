import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<T> implements Iterable<T> {
    private Node first;
    private Node last;
    private int size;

    private class Node {
        public T value;
        public Node next;
        public Node previous;

        public Node(T value, Node next, Node previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    private class CustomIterator implements Iterator<T> {

        private Node node;

        @Override
        public boolean hasNext() {
            return (node != null);
        }

        @Override
        public T next() {
            if (node == null)
                throw new NoSuchElementException();
            T value = node.value;
            node = node.next;
            return value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public CustomIterator(Node node) {
            this.node = node;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new CustomIterator(this.first);
    }

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        assureIsNotNull(item);
        Node newFirst = new Node(item, first, null);
        if (first != null)
            first.previous = newFirst;
        if (last == null)
            last = newFirst;
        first = newFirst;
        this.size++;
    }

    public void addLast(T item) {
        assureIsNotNull(item);
        Node newLast = new Node(item, null, last);
        if (last != null)
            last.next = newLast;
        if (first == null)
            first = newLast;
        last = newLast;
        this.size++;
    }

    public T removeFirst() {
        assureNotEmpty();
        T value = first.value;
        first = first.next;
        size--;
        if (size > 0)
            first.previous = null;
        return value;
    }

    public T removeLast() {
        assureNotEmpty();
        T value = last.value;
        last = last.previous;
        size--;
        if (size > 0)
            last.next = null;
        return value;
    }

    private void assureIsNotNull(T value) {
        if (value == null) {
            throw new IllegalArgumentException();
        }
    }

    private void assureNotEmpty() {
        if (this.size == 0)
            throw new NoSuchElementException();
    }

    public static void main(String[] args) {
    }
}
