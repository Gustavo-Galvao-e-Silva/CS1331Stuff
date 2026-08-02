import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list implementation.
 *
 * @param <T> the type of elements stored in this list
 * @author gsilva37
 * @version 1.0.0
 */
public class LinkedList<T> implements List<T> {
    private Node<T> head;
    private int size;

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList() {
        head = null;
        size = 0;
    }

    /**
     * Constructs a LinkedList with the elements of the given array.
     *
     * @param data array of elements to add; must not be null and must not contain
     *             null elements
     * @throws IllegalArgumentException if data is null or contains a null element
     */
    public LinkedList(T[] data) throws IllegalArgumentException {
        this();
        if (data == null) {
            throw new IllegalArgumentException("Data array passed cannot be null");
        }
        for (T datum : data) {
            if (datum == null) {
                throw new IllegalArgumentException("Value in data array passed cannot be null");
            }
            this.add(datum);
        }
    }

    /**
     * Returns the head node of this list.
     *
     * @return the head node, or null if the list is empty
     */
    public Node<T> getHead() {
        return this.head;
    }

    /**
     * Returns an array containing all elements in this list in order.
     *
     * @return an array of all elements
     */
    public T[] toArray() {
        T[] array = (T[]) new Object[this.size];
        Iterator<T> it = this.iterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = it.next();
            i++;
        }

        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                String.format("===== LINKEDLIST %d =====\nisEmpty: %s\nsize: %d\nhead: %s\ndata: [",
                        hashCode(),
                        isEmpty(),
                        size(),
                        (head == null ? "null" : head.getData())));

        T[] data = toArray();
        if (data == null) {
            sb.append("TODO: Implement toArray method...");
        } else {
            for (int i = 0; i < data.length - 1; ++i) {
                sb.append(String.format("%s, ", data[i]));
            }
            if (data.length > 0) {
                sb.append(String.format("%s", data[data.length - 1]));
            }
        }
        sb.append("]\n============================");
        return sb.toString();
    }

    @Override
    public void add(T element) throws IllegalArgumentException {
        this.add(this.size, element);
    }

    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (!this.isValidIndex(index, true)) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        if (element == null) {
            throw new IllegalArgumentException("Cannot add null element to the list");
        }

        Node<T> node = new Node<T>(element);
        if (index == 0) {
            node.setNext(this.head);
            this.head = node;
        } else {
            Node<T> prev = this.getNode(index - 1);
            node.setNext(prev.getNext());
            prev.setNext(node);
        }

        this.size++;
    }

    @Override
    public T remove() throws NoSuchElementException {
        return this.remove(0);
    }

    @Override
    public T remove(int index) throws NoSuchElementException, IndexOutOfBoundsException {
        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }

        if (!this.isValidIndex(index, false)) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        T data;
        if (index == 0) {
            data = head.getData();
            head = head.getNext();
        } else {
            Node<T> prev = this.getNode(index - 1);
            data = prev.getNext().getData();
            prev.setNext(prev.getNext().getNext());
        }

        size--;
        return data;
    }

    @Override
    public T remove(T element) throws IllegalArgumentException, NoSuchElementException {
        if (element == null) {
            throw new IllegalArgumentException("Cannot remove a null element");
        }

        if (this.isEmpty()) {
            throw new NoSuchElementException("Cannot remove from an empty list");
        }

        if (head.getData().equals(element)) {
            T data = head.getData();
            head = head.getNext();
            size--;
            return data;
        }

        Node<T> prev = getPreviousNode(element);
        if (prev == null) {
            throw new NoSuchElementException("Element not found in the list");
        }

        T data = prev.getNext().getData();
        prev.setNext(prev.getNext().getNext());
        size--;
        return data;
    }

    @Override
    public T set(int index, T element) throws IndexOutOfBoundsException, IllegalArgumentException {
        if (!this.isValidIndex(index, false)) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        if (element == null) {
            throw new IllegalArgumentException("Cannot set a null element in the list");
        }

        Node<T> target = this.getNode(index);
        T oldValue = target.getData();
        target.setData(element);
        return oldValue;
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        if (!this.isValidIndex(index, false)) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        Iterator<T> it = this.iterator();
        int i = 0;
        while (it.hasNext()) {
            T val = it.next();
            if (i == index) {
                return val;
            }

            i++;
        }

        throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
    }

    @Override
    public boolean contains(T element) throws IllegalArgumentException {
        if (element == null) {
            throw new IllegalArgumentException("Cannot search for a null element");
        }

        Iterator<T> it = this.iterator();
        while (it.hasNext()) {
            T val = it.next();
            if (val.equals(element)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void clear() {
        while (!(this.isEmpty())) {
            this.remove();
        }
    }

    @Override
    public boolean isEmpty() {
        return (this.size == 0);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<T>(this);
    }

    private boolean isValidIndex(int index, boolean allowPlusOne) {
        return (index >= 0 && index <= this.size - (!(allowPlusOne) ? 1 : 0));
    }

    private Node<T> getNode(int index) {
        int i = 0;
        Node<T> curr = this.head;
        while (i < index) {
            curr = curr.getNext();
            i++;
        }

        return curr;
    }

    private Node<T> getPreviousNode(T element) {
        Node<T> prev = this.head;
        while (prev.getNext() != null) {
            if (prev.getNext().getData().equals(element)) {
                return prev;
            }
            prev = prev.getNext();
        }

        return null;
    }
}
