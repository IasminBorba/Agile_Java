package AgileJavaExercises.clock;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Ring<T> implements Iterable<T> {
    private Node<T> current;

    private static class Node<T> {
        T element;
        Node<T> next;
        Node<T> previous;

        Node(T element) {
            this.element = element;
        }
    }

    public void add(T element) throws Exception {
        assert element != null : "Element not added, because element is null";
        Node<T> node = new Node<>(element);

        if (current == null) {
            node.next = node;
            node.previous = node;
            current = node;
        } else if (hasElement(element)) {
            throw new Exception("This element already exists");
        } else {
            node.next = current.next;
            node.previous = current;
            current.next.previous = node;
            current.next = node;
        }
    }

    public void remove(T element) throws Exception {
        Node<T> nodeRemove = findNode(element);
        if (current == null)
            throw new Exception("Cannot remove element from list as list is empty");

        if (current.next == current) {
            current.next = null;
            current.previous = null;
            current = null;
        } else {
            Node<T> nodeBack = nodeRemove.previous;
            Node<T> nodeAdvanced = nodeRemove.next;
            nodeBack.next = nodeAdvanced;
            nodeAdvanced.previous = nodeBack;
            current = nodeRemove.next;
        }
    }

    public void advanced() throws Exception {
        if (current == null)
            throw new Exception("This list is empty");
        if (this.size() == 1)
            throw new Exception("This list only has a single element");

        current = current.next;
    }

    public void back() throws Exception {
        if (current == null)
            throw new Exception("This list is empty");
        if (this.size() == 1)
            throw new Exception("This list only has a single element");

        current = current.previous;
    }

    public boolean hasElement(T element) {
        if (current != null) {
            Iterator<T> it = iterator();
            while (it.hasNext()) {
                if (it.next() == element)
                    return true;
            }
        }
        return false;
    }

    public int size() {
        if (current == null)
            return 0;

        int countElements = 0;
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (it.next() != null)
                countElements++;
        }
        return countElements;
    }

    public T getCurrentElement() throws Exception {
        if (current == null)
            throw new Exception("Cannot get the current element because the list is empty");

        return current.element;
    }

    public void alterCurrentElement(T element) throws Exception {
        Node<T> nodeAlter = findNode(element);
        if (current != nodeAlter)
            current = nodeAlter;
        else
            throw new Exception("This element is already the current element");
    }

    public Node<T> findNode(T data) throws Exception {
        if (current == null)
            throw new Exception("List is empty, element not found");

        Node<T> pointer = current;
        do {
            if (pointer.element.equals(data))
                return pointer;

            pointer = pointer.next;
        } while (pointer != current);

        throw new Exception("Element not found");
    }

    public String printRing() throws Exception {
        if (current == null)
            throw new Exception("Cannot print because the list is empty");

        StringBuilder elements = new StringBuilder();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            T element = it.next();
            if (element != null)
                elements.append(element).append(" <-> ");
        }
        return elements.append(current.element).toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new RingIterator();
    }

    private class RingIterator implements Iterator<T> {
        private final Node<T> start = current;
        private Node<T> pointer = current;
        private boolean firstIteration = true;

        @Override
        public boolean hasNext() {
            return pointer != null && (firstIteration || pointer != start);
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();

            T data = pointer.element;
            pointer = pointer.next;
            firstIteration = false;
            return data;
        }
    }
}
