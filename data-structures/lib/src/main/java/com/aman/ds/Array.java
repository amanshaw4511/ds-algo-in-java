//*        Static Array    Dynamic Array
// Access      1               1
// Modifiy     1               1 
// Add         NA              1
// Remove      NA              n
// Insert      NA              n

package com.aman.ds;

import java.lang.IllegalArgumentException;
import java.lang.IndexOutOfBoundsException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@SuppressWarnings("unchecked")
public class Array<T> implements Iterable<T> {
    private T[] data;
    private int size;
    private int capacity;

    Array() {
        this(16);
    }

    Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("invalid capacity");
        }

        this.data = (T[]) new Object[capacity];
        this.size = 0;
        this.capacity = capacity;
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("invalid index");
        }
    }

    public T get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    public void set(int index, T value) {
        checkIndex(index);
        this.data[index] = value;
    }

    public void clear() {
        for (int i = 0; i < this.capacity; i++) {
            this.data[i] = null;
        }
        this.size = 0;
    }
    
    private void ensureExtraCapacity() {
        if (this.size == this.capacity) {
            this.capacity = (this.capacity == 0) ? 1 : this.capacity * 2;
            this.data = Arrays.copyOf(this.data, this.capacity);
        }
    }

    public void add(T value) {
        ensureExtraCapacity();

        this.data[this.size] = value; // add new element
        this.size += 1;
    }

    public void insert(int index, T value) {
        checkIndex(index);
        ensureExtraCapacity();

        for (int i=this.size-1; i>=index; i--) {
            this.data[i+1] = this.data[i];
        }
        this.data[index] = value;
    }

    public T removeAt(int index) {
        checkIndex(index);
        T toBeRemoved = this.data[index];
        for (int i = index; i < this.size - 1; i++) {
            this.data[i] = this.data[i + 1];
        }
        this.data[this.size - 1] = null;
        this.size -= 1;
        return toBeRemoved;
    }

    public boolean remove(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(value)) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    public int indexOf(T value) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return data[index++];
            }

        };
    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }
    
    public Stream<T> parallelStream() {
        return StreamSupport.stream(this.spliterator(), true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");
        int i;
        for (i = 0; i < this.size - 1; i++) {
            sb.append(this.data[i] + ", ");
        }
        sb.append(this.data[i] + " ]");
        return sb.toString();
    }
}
