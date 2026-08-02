/**
 * A singly-linked node that holds data a reference to the next node.
 *
 * @param <T> the type of data stored in this node
 * @author gsilva37
 * @version 1.0.0
 */
public class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * Constructs a node with the given data and next reference.
     *
     * @param data the data to store; must not be null
     * @param next the next node, or null if this is the last node
     * @throws IllegalArgumentException if data is null
     */
    public Node(T data, Node<T> next) throws IllegalArgumentException {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null");
        }

        this.data = data;
        this.next = next;
    }

    /**
     * Constructs a node with the given data and no next reference.
     *
     * @param data the data to store; must not be null
     * @throws IllegalArgumentException if data is null
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * Returns the data stored in this node.
     *
     * @return the data element
     */
    public T getData() {
        return this.data;
    }

    /**
     * Returns the next node in the list.
     *
     * @return the next node, or null if there is none
     */
    public Node<T> getNext() {
        return this.next;
    }

    /**
     * Replaces the data stored in this node.
     *
     * @param newData the replacement data; must not be null
     * @throws IllegalArgumentException if newData is null
     */
    public void setData(T newData) throws IllegalArgumentException {
        if (newData == null) {
            throw new IllegalArgumentException("Node data cannot be null");
        }

        this.data = newData;
    }

    /**
     * Sets the next node reference.
     *
     * @param newNext the node that should follow this one, or null
     */
    public void setNext(Node<T> newNext) {
        this.next = newNext;
    }
}
