/** Name: Stella Dong
 * NetID: ssd74
 * Testing Partner Name: Eva Farroha
 * Testing Partner NetID: edf55
 * Tell us what you thought about the assignment:
 */

package a3;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implementation of {@code LList<T>} as a singly linked list.
 */
public class SLinkedList<T> implements LList<T> {

    /**
     * Number of values in the linked list.
     */
    private int size;
    /**
     * First and last nodes of the linked list (null if size is 0)
     */
    private Node<T> head, tail;

    /**
     * Creates: an empty linked list.
     */
    public SLinkedList() {
        size = 0;
        head = tail = null;
    }

    boolean classInv() {
        assert size >= 0;
        if (size == 0) {
            return (head == null && tail == null);
        }
        // TODO
        else if (size == 1){
            return (head.equals(tail));
        }
        else if (size > 1){
            return (head != null && tail != null);
        }

        assert tail.next() == null;

        return true;
    }

    public int size() {
        return size;
    }

    public T head() {
        return head.data();
    }

    public T tail() {
        return tail.data();
    }

    public void prepend(T v) {
        assert classInv();
        Node<T> n = new Node<>(v, head);
        head = n;
        if (tail == null) tail = head;
        size++;
        assert classInv();
    }


    /**
     * Return a representation of this list: its values, with "[" at the beginning, "]" at the
     * end, and adjacent ones separated by ", " . Takes time proportional to the length of this
     * list. E.g. for the list containing 4 7 8 in that order, the result it "[4, 7, 8]".
     * E.g. for the list containing two empty strings, the result is "[, ]"
     */
    @Override
    public String toString() {
        assert classInv();
        StringBuilder res = new StringBuilder("[");
        Node<T> n = head;
        while (n != null){
            res.append(n.data());
            if (n.next() != null) res.append(", ");
            n = n.next();
        }

        return res + "]";
    }

    public void append(T v) {
        assert classInv();
        Node<T> n = new Node<T>(v, null);
        if (size() == 0){
            head = n;
        }
        else {
            tail.setNext(n);
        }
        tail = n;
        size++;
        assert classInv();
    }

    public void insertBefore(T x, T y) {
        assert classInv();
        Node<T> n = head;
        Node<T> addNode = new Node<>(x, null);
        if (head.data().equals(y)==false) {
            while (n.next().data().equals(y) == false){
                n = n.next();
            }

            Node oldNext = n.next();
            n.setNext(addNode);
            n = n.next();
            n.setNext(oldNext);
        }

        else if (head.data().equals(y)) {
            Node oldNext = head;
            head = addNode;
            head.setNext(oldNext);
        }
        size++;
        assert classInv();
    }

    public T get(int k) {
        assert classInv();
        Node<T> n = head;
        for (int i = 0; i < k; i++){
            n = n.next();
        }
        return n.data();
    }

    public boolean contains(T value) {
        assert classInv();
        Node<T> n = head;
        while (n != null){
            if (n.data().equals(value)) return true;
            n = n.next();
        }
        return false;
    }

    public boolean remove(T x) {
        assert classInv();
        Node<T> n = head;
        if (contains(x) == false){
            return false;
        }
        if (head.data().equals(x)==false){
            while (n.next().data().equals(x) == false) n = n.next();
            if (n.next().equals(tail) == false) {
                Node removeNode = n.next();
                Node newNext = removeNode.next();
                n.setNext(newNext);
            }
            else{
                n.setNext(null);
            }
        }
        else{
            head = head.next();
        }
        size--;
        assert classInv();
        return true;
    }

    /**
     * Iterator support. This method makes it possible to write
     * a for-loop over a list, e.g.:
     * <pre>
     * {@code LList<String> lst = ... ;}
     * {@code for (String s : lst) {}
     *   ... use s here ...
     * }
     * }
     */
    @Override
    public Iterator<T> iterator() {
        assert classInv();
        return new Iterator<T>() {
            private Node<T> current = head;

            public T next() throws NoSuchElementException {
                if (current != null) {
                    T result = current.data();
                    current = current.next();
                    return result;
                } else {
                    throw new NoSuchElementException();
                }
            }

            public boolean hasNext() {
                return (current != null);
            }
        };
    }
}
