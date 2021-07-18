/**
*                 Single LinkedList        Double LinkedList
* access             n, 1, 1                    n, 1, 1
* add                n, 1, 1                    n, 1, 1
* remove             n, 1, n                    n, 1, 1
* search             n                          n
*
*/
package com.aman.ds;

import java.util.Iterator;
import java.lang.Iterable;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.function.Predicate;



class Node<T> {
    public T value;
    public Node<T> next;

    Node(T value) {
        this.value = value;
    }

    Node(T value, Node<T> next) {
        this.value = value;
        this.next = next;
    }
}

public class LList<T> implements Iterable<T> {
    private int size;
    private Node<T> head;
    private Node<T> tail;

    LList() {
    }

    LList(T[] array) {

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> curr = head;

            @Override
            public boolean hasNext() {
                return curr != null;
            }

            @Override
            public T next() {
                T value = curr.value;
                curr = curr.next;
                return value;
            }
        };

    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
    }


    private void addFirstElement(T value) {
        this.head = new Node<>(value);
        this.tail = this.head;
    }

    public void add(T value) {
        if (this.size == 0) {
            addFirstElement(value);
        } else {
            this.tail.next = new Node<>(value);
            this.tail = tail.next;
        } 
        this.size += 1;
    }


    public void addFirst(T value) {
        if (this.size == 0) {
            addFirstElement(value);
        } else {
            Node<T> newNode = new Node<>(value);
            newNode.next = this.head;
            this.head = newNode;
        }
        this.size += 1;
    }

    public void addLast(T value) {
        this.add(value);
    }

    public T peek() {
        return this.getLast();
    }

    public T peekFirst() {
        return this.getFirst();
    }

    public T peekLast() {
        return peek();
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    private Node<T> traverseTo(int index) {
        checkIndex(index);

        Node<T> curr = this.head;
        for (int i=0; i<index; i++) {
            curr = curr.next;
        }
        return curr;
    }

    public T get(int index) {
        checkIndex(index);
        return this.traverseTo(index).value;
    }

    public T getFirst() {
        return this.head.value;
    }
    
    public T getLast() {
        return this.tail.value;
    }

    public void set(int index, T value) {
        checkIndex(index);
        this.traverseTo(index).value = value;
    }

    private void removeOnlyElement() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public boolean remove(T value) {
        return this.removeIf(e -> e == value);
    }

    public boolean removeFirst() {
        if (this.isEmpty()) {
            return false;
        }
        if (this.size == 1) {
            removeOnlyElement();
            return true;
        }
        this.head = this.head.next;
        this.size -= 1;
        return true;
    }


    public void removeLast() {
        this.removeAt(this.size -1);
    }

    public void removeAt(int index) {
        checkIndex(index);
        if (index == 0) {
            removeFirst();
            return;
        }
        Node<T> nodeBeforeNodeToBeRemoved = this.traverseTo(index -1);
        nodeBeforeNodeToBeRemoved.next = nodeBeforeNodeToBeRemoved.next.next;
        if (nodeBeforeNodeToBeRemoved.next == null) { // if last element removed
            this.tail = nodeBeforeNodeToBeRemoved;
        }
        this.size -= 1;
    }

    public boolean removeIf(Predicate<T> predicate) {
        if (this.isEmpty()) {
            return false;
        }
        if (predicate.test(this.head.value)) {
            return this.removeFirst();
        }

        Node<T> curr = this.head;
        while (curr.next != null) {
            if (predicate.test(curr.next.value)) {
                curr.next = curr.next.next;
                if (curr.next == null) { // if last element removed
                    this.tail = curr;
                }
                this.size -= 1;
                return true;
            }
            curr = curr.next;
        }
        return false;

    }

    public boolean equals(LList<T> other) {
        if (this.size() != other.size()) {
            return false;
        }
        Iterator<T> it1 = this.iterator();
        Iterator<T> it2 = other.iterator();
        while (it1.hasNext()) {
            if (! it1.next().equals(it2.next())) {
                return false;
            }
        }
        return true;
    }

    public int indexOf(T value) {
        int i = 0;
        for (T val : this) {
            if (val.equals(value)) {
                return i;
            }
            i += 1;
        }
        return -1;
    }

    public boolean contains(T value) {
        return this.indexOf(value) != -1;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "[ ]";
        }

        StringBuilder sb = new StringBuilder("[ ");
        Node<T> curr = this.head;
        while (curr.next != null) {
            sb.append(curr.value + ", ");
            curr = curr.next;
        }

        sb.append(curr.value + " ]");
        return sb.toString();
    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

}
