package com.aman.ds;
import java.util.Optional;


class QueueOverflowException extends Exception {

}

@SuppressWarnings("unchecked")
public class Queue<T> {
    private int capacity;
    private int head;
    private int tail;
    private T[] data;

    Queue() {
        this(16);
    }

    Queue(int capacity) {
        this.capacity = capacity;
        this.head = -1;
        this.tail = -1;
        this.data = (T[]) new Object[capacity];
    }

    public boolean isEmpty() {
        return this.head == -1;
    }

    public int size() {
        if (this.isEmpty()) {
            return 0;
        }
        return this.head - this.tail + 1;
    }

    public void enqueue(T value) throws QueueOverflowException {
        if (this.tail +1 == this.capacity) {
            throw new QueueOverflowException();
        }

        if (this.isEmpty()) {
            this.head = 0;
            this.tail = 0;
            this.data[0] = value;
        } else {
            this.data[this.tail] = value;
            this.tail -= 1;
        }

    }
 
    public Optional<T> dequeue() {
        Optional<T> value = this.peek();
        if (this.head == this.tail) { // when only one element present
            this.head = -1;
            this.tail = -1;
        } else {
            this.head += 1;
        }
        return value;
    }

    public Optional<T> peek() {
        if (this.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.data[head]);
    }


}
