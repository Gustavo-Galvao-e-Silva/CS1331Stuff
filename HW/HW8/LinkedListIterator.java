import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that traverses a LinkedList.
 *
 * @param <T> the type of elements returned by this iterator
 * @author gsilva37
 * @version 1.0.0
 */
public class LinkedListIterator<T> implements Iterator<T> {
    private Node<T> next;

    /**
     * Constructs an iterator starting at the head of the given list.
     *
     * @param list the list to iterate over; must not be null
     * @throws IllegalArgumentException if list is null
     */
    public LinkedListIterator(LinkedList<T> list) throws IllegalArgumentException {
        if (list == null) {
            throw new IllegalArgumentException("Got null constructing linked list iterator");
        }

        this.next = list.getHead();
    }

    @Override
    public boolean hasNext() {
        return (this.next != null);
    }

    @Override
    public T next() throws NoSuchElementException {
        if (next == null) {
            throw new NoSuchElementException("No more nodes to iterate over");
        }

        T data = this.next.getData();
        this.next = next.getNext();
        return data;
    }
}
