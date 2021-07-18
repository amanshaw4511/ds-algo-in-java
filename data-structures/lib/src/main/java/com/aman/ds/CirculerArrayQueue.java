package com.aman.ds;

import java.util.Optional;

@SuppressWarnings("unchecked")
class CirculerArrayQueue<T> {
    private int capacity;
    private int head;
    private int tail;
    private T[] data;

    CirculerArrayQueue(int capacity) {
        this.capacity = capacity;
        this.head = -1;
        this.tail = -1;
        this.data = (T[]) new Object[capacity];
    }

    public int capacity() {
        return this.capacity;
    }

    public int size() {
        if (this.head == -1) {
            return 0;
        }
        if (this.tail > this.head) {
            return this.tail - this.head + 1;
        }
        return (this.capacity - this.head) + this.tail + 1;
    }

    public boolean isEmpty() {
        return this.head == -1; 
    }

    public boolean isFull() {
        return this.tail + 1 == this.head || 
            (this.head == 0 && this.tail == this.capacity() -1);
    }

    private void checkFull() {
        if (this.isFull()) {
            throw new RuntimeException("Queue is full");
        }
    }

    public void addFirst(T value) {
        this.checkFull();

        if (this.isEmpty()) {
            this.head = 0;
            this.tail = 0;
        } else if (this.tail == 0) {
            this.head = this.capacity() -1;
        } else {
            this.head -= 1;
        }
        this.data[tail] = value;
    }

    public void addLast(T value) {
        this.checkFull();

        if (this.isEmpty()) {
            this.head = 0;
            this.tail = 0;
        } else if (this.tail == this.capacity() -1) {
            this.head = 0;
        } else {
            this.head += 1;
        }
        this.data[head] = value;
    }

    public Optional<T> peekFirst() {
        if (this.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.data[tail]);
    }

    public Optional<T> peekLast() {
        if (this.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.data[tail]);
    }

    public Optional<T> removeFirst() {
        Optional<T> value = this.peekFirst();
        if (this.head == this.tail) { // only one element exist
            this.head = -1;
            this.tail = -1;
        } else if(this.tail == this.capacity() -1){
            this.tail = 0;
        } else {
            this.tail += 1;
        }
        return value;
    }

    public Optional<T> removeLast() {
        Optional<T> value = this.peekLast();
        if (this.head == this.tail) { // only one element exist
            this.head = -1;
            this.tail = -1;
        } else if (this.head == 0) {
            this.head = this.capacity() -1;
        } else {
            this.head -= 1;
        }
        return value;
    }

    public boolean contains(T value) {
        int i = this.head;
        if (isEmpty()) {
            return false;
        }
        while (i != this.tail) {
            if (this.data[i] == value) {
                return true;
            }

            i = (i == this.capacity() -1) ? 0 : i+1;
        }

        if (this.data[this.tail] == value)  {
            return true;
        } 

        return false;
    }

}
