package L_31;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    public int indexOf(T data) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (current.data.equals(data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;  // элемент не найден
    }

    public void add(T data) {
        if (contains(data)) {
            throw new IllegalArgumentException("Duplicated items are not allowed.");
        }

        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current.data;
    }

    public void set(int index, T data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (contains(data)) {
            throw new IllegalArgumentException("Duplicated items are not allowed.");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        current.data = data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insert(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (contains(data)) {
            throw new IllegalArgumentException("Duplicated items are not allowed.");
        }

        Node<T> newNode = new Node<>(data);

        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) tail = head;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            newNode.prev = current;
            current.next.prev = newNode;
            current.next = newNode;
        }
        size++;
    }

    public void bubbleSort() {
        if (size <= 1) return;

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> next = head.next;
            Node<T> prev = null;

            while (next != null) {
                if (((Comparable<T>) current.data).compareTo(next.data) > 0) {
                    swapped = true;

                    if (prev != null) {
                        prev.next = next;
                    } else {
                        head = next;
                    }

                    current.next = next.next;
                    if (next.next != null) {
                        next.next.prev = current;
                    }
                    next.next = current;
                    next.prev = current.prev;
                    current.prev = next;

                    prev = next;
                    next = current.next;
                } else {
                    prev = current;
                    current = next;
                    next = next.next;
                }
            }

            if (!swapped) {
                tail = current;
            }

        } while (swapped);
    }

    public void bubbleSort(Function<T, ? extends Comparable> keyExtractor) {
        if (size <= 1) return;

        boolean swapped;
        do {
            swapped = false;
            Node<T> current = head;
            Node<T> next = head.next;
            Node<T> prev = null;

            while (next != null) {
                Comparable key1 = keyExtractor.apply(current.data);
                Comparable key2 = keyExtractor.apply(next.data);

                if (key1.compareTo(key2) > 0) {
                    swapped = true;

                    if (prev != null) {
                        prev.next = next;
                    } else {
                        head = next;
                    }

                    current.next = next.next;
                    if (next.next != null) {
                        next.next.prev = current;
                    }
                    next.next = current;
                    next.prev = current.prev;
                    current.prev = next;

                    prev = next;
                    next = current.next;
                } else {
                    prev = current;
                    current = next;
                    next = next.next;
                }
            }

            if (!swapped) {
                tail = current;
            }

        } while (swapped);
    }

    public int binarySearch(T item) {
        if (!(item instanceof Comparable)) {
            throw new IllegalArgumentException("The item should implement Comparable.");
        }

        Node<T> current = head;
        int low = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Node<T> midNode = getNodeAt(mid);
            if (((Comparable<T>) midNode.data).compareTo(item) == 0) {
                return mid;
            } else if (((Comparable<T>) midNode.data).compareTo(item) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private Node<T> getNodeAt(int index) {
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    public T find(Predicate<T> condition) {
        Node<T> current = head;
        while (current != null) {
            if (condition.test(current.data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public List<T> findAll(Predicate<T> condition) {
        List<T> results = new ArrayList<>();
        Node<T> current = head;
        while (current != null) {
            if (condition.test(current.data)) {
                results.add(current.data);
            }
            current = current.next;
        }
        return results;
    }

    public int findIndex(Predicate<T> condition) {
        int index = 0;
        Node<T> current = head;
        while (current != null) {
            if (condition.test(current.data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean exists(Predicate<T> condition) {
        return find(condition) != null;
    }

    public void forEach(Consumer<T> action) {
        Node<T> current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }

    public <U> LinkedList<U> convertAll(UnaryOperator<U> converter) {
        LinkedList<U> newList = new LinkedList<>();
        Node<T> current = head;
        while (current != null) {
            newList.add(converter.apply((U) current.data));
            current = current.next;
        }
        return newList;
    }

    public boolean trueForAll(Predicate<T> condition) {
        Node<T> current = head;
        while (current != null) {
            if (!condition.test(current.data)) {
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public void reverse() {
        tail = head;
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            Node<T> nextTemp = current.next;
            current.next = prev;
            current.prev = nextTemp;
            prev = current;
            current = nextTemp;
        }
        head = prev;
    }

    public LinkedList<T> asReadOnly() {
        LinkedList<T> readOnlyList = new LinkedList<>();
        Node<T> current = head;
        while (current != null) {
            readOnlyList.add(current.data);
            current = current.next;
        }
        return readOnlyList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            current = current.next;
            if (current != null) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}