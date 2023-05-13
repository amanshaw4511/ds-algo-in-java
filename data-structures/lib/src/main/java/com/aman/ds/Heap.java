
// https://youtu.be/t0Cq6tVNRBA
//
// heap is complete binary tree
// last level is complete i.e all leaves are at lower left side of if not compele in last level

/**
 * Peek            1
 * Add            log(n)
 * Poll           log(n)
 * contains         n      // with Map<value, TreeSet<index>>  : log(n)
 * Remove           n      // with Map<value, TreeSet<index>>  : log(n)
 * */
package com.aman.ds;

import java.util.Arrays;
import java.util.Optional;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class Heap<T extends Comparable<T>> {
    private int capacity;
    private int size;
    private T[] data;
    private Comparator<T> comparator;

    Heap(int capacity) {
        this(capacity, (x, y) -> x.compareTo(y));
    }

    Heap(int capacity, Comparator<T> comparator) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (T[]) new Comparable[capacity];
        this.comparator = comparator;
    }

    private int getLeftChildIndex(int i) {
        return i * 2;
    }

    private int getRightChildIndex(int i) {
        return i * 2 + 1;
    }

    private int getParentIndex(int i) {
        return i / 2;
    }

    private boolean hasLeftChild(int i) {
        return getLeftChildIndex(i) < size();
    }

    private boolean hasRightChild(int i) {
        return getRightChildIndex(i) < size();
    }

    private boolean hasParent(int i) {
        return getParentIndex(i) >= 0;
    }

    private boolean less(int i, int j) {
        return comparator.compare(this.data[i], this.data[j]) < 0;
    }


    private void swap(int i, int j) {
        T temp = this.data[i];
        this.data[i] = this.data[j];
        this.data[j] = temp;
    }

    private void ensureExtraCapacity() {
        if (size == capacity) {
            data = Arrays.copyOf(data, capacity * 2);
            this.capacity *= 2;
        }
    }

    public int capacity() {
        return this.capacity;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public void clear() {
        this.size = 0;
    }

    public boolean contains(T value) {
        return this.indexOf(value) != -1;
    }

    private int indexOf(T value) {
        for (int i = 0; i< this.size -1; i++) {
            if (this.data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public Optional<T> peek() {
        if (this.size() == 0) {
            return Optional.empty();
        }
        return Optional.of(this.data[0]);
    }

    public Optional<T> poll() {
        Optional<T> value = this.peek();
        if (value.isPresent()) {
            this.data[0] = this.data[this.size - 1];
            this.size -= 1;
            this.heapifyDown(0);
        }
        return value;
    }

    public boolean remove(T value) {
        if (value == null) {
            return false;
        }

        int index = this.indexOf(value);
        if (index == -1) {
            return false;
        }
        this.removeAt(index);
        return true;
    }

    private void removeAt(int index) {
        // replace value with last element
        this.data[index] = this.data[this.size -1];
        // remove last element
        this.data[this.size -1] = null;
        this.size -= 1;

        // try heapifyUp and down
        heapifyUp(index);
        heapifyDown(index);
    }

    public void add(T value) {
        this.ensureExtraCapacity();
        this.data[this.size] = value;
        this.heapifyUp(this.size);
        this.size += 1;
    }

    private void heapifyDown(int i) {
        while (hasLeftChild(i)) {
            int smallChildIndex = getLeftChildIndex(i);
            if (hasRightChild(i) && less(getRightChildIndex(i), getLeftChildIndex(i))) {
                smallChildIndex = getRightChildIndex(i);
            }

            if (less(smallChildIndex, i)) {
                swap(smallChildIndex, i);
                i = smallChildIndex;
            } else {
                break;
            }
        }
    }


    private void heapifyUp(int i) {
        while (hasParent(i) && less(i, getParentIndex(i))) {
            this.swap(i, getParentIndex(i));
            i = getParentIndex(i);
        }
    }

    public String toString() {
        if (this.size() == 0) return "[  ]";
        StringBuilder sb = new StringBuilder("[ ");
        for (int i=0; i<this.size-1; i++) {
            sb.append(this.data[i] + ", ");
        }
        sb.append(this.data[this.size -1] + " ]");
        return sb.toString();
    }
}
