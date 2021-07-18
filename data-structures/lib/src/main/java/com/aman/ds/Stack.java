package com.aman.ds;
import java.util.Optional;
import java.lang.Exception;


class StackOverflowException extends Exception {

}


@SuppressWarnings("unchecked")
public class Stack<T>  {

    private int capacity;
    private T[] data;
    private int top;

    Stack() {
        this(16);
    }

    Stack(int capacity) {
        this.capacity = capacity;
        this.top = -1;
        this.data = (T[]) new Object[capacity];
    }

    public int size() {
        return this.top + 1;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return this.top == -1;
    }

    public void push(T value) throws StackOverflowException {
        if (top+1 == capacity) {
            throw new StackOverflowException();
        }
        this.top += 1;
        this.data[top] = value;
    }

    public Optional<T> pop() {
        Optional<T> value = this.peek();
        if (value.isPresent()) {
            top -= 1;
        }
        return value;
    }

    public Optional<T> peek() {
        if (isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(this.data[top]);
    }

    public void clear() {
        this.top = -1;
    }

}
