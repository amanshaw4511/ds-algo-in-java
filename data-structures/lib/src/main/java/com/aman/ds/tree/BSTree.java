
/**
 * Binary Search Tree is a Binary tree where
 * - left sub-tree contains keys less then node key
 * - right sub-tree contains keys greater teen node key
 *
 * search           log(n)
 * add              log(n)
 * remove           log(n)
 * */

package com.aman.ds;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class BSTree<T extends Comparable<T>> implements Iterable<T> {
    private class Node<U> {
        U value;
        Node<U> left;
        Node<U> right;

        Node(U value) {
            this.value = value;
        }
    }

    private Node<T> root;
    private int size;

    BSTree() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean contains(T value) {
        return this.find(this.root, value) != null;
    }

    private Node<T> find(Node<T> curr, T value) {
        if (curr == null) {
            return null;
        } else if (value.compareTo(curr.value) < 0) {
            return this.find(curr.left, value);
        } else if (value.compareTo(curr.value) > 0) {
            return this.find(curr.right, value);
        } else {
            return curr;
        }
    }

    public boolean add(T value) {
        if (this.contains(value))  {
            return false;
        }
        this.root = this._add(this.root, value);
        this.size += 1;
        return true;
    }

    private Node<T> _add(Node<T> curr, T value) {
        if (curr == null) {
            return new Node<T>(value);
        }
        if (value.compareTo(curr.value) < 0) {
            curr.left = this._add(curr.left, value);
        } else {
            curr.right = this._add(curr.right, value);
        }
        return curr;
    }

    public boolean remove(T value) {
        if (!this.contains(value)) {
            return false;
        }
        this.root = this._remove(this.root, value);
        this.size -= 1;
        return true;
    }

    private Node<T> _remove(Node<T> curr, T value) {
        if (curr == null) {
            return null;
        } else if (value.compareTo(curr.value) < 0) {
            curr.left = this._remove(curr.left, value);
        } else if (value.compareTo(curr.value) > 0) {
            curr.right = this._remove(curr.right, value);
        } else {
            if (curr.left == null) {
                return curr.right;
            } else if (curr.right == null) {
                return curr.left;
            } else {
                Node<T> leftLargest = this.maxNode(curr.left);
                curr.value = leftLargest.value;
                curr.left = this._remove(curr.left, leftLargest.value);
            }
        }
        return curr;

    }

    private Node<T> minNode(Node<T> node) {
        Node<T> curr = node;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private Node<T> maxNode(Node<T> node) {
        Node<T> curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

    public int height() {
        return this._height(this.root);
    }

    private int _height(Node<T> curr) {
        if (curr == null) {
            return 0;
        }
        return 1 + Math.max(this._height(curr.left), this._height(curr.right));

    }

    public Iterator<T> traverse(BTreeTraversalOrder traversalOrder) {
        switch (traversalOrder) {
            case PRE_ORDER:
                return this.preOrderTraversal(this.root);
            case IN_ORDER:
                return this.inOrderTraversal(this.root);
            case POST_ORDER:
                return this.postOrderTraversal(this.root);
            case LEVEL_ORDER:
                return this.levelOrderTraversal(this.root);
            default:
                return null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return inOrderTraversal(this.root);
    }

    private Iterator<T> preOrderTraversal(Node<T> node) {
        Stack<Node<T>> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
        }

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                Node<T> node = stack.pop();
                if (node.left != null)
                    stack.push(node.left);
                if (node.right != null)
                    stack.push(node.right);
                return node.value;
            }
        };
    }

    private Iterator<T> inOrderTraversal(Node<T> node) {
        Stack<Node<T>> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
        }

        return new Iterator<T>() {
            Node<T> trav = node;

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public T next() {
                // dig left
                while (trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node<T> curr = stack.pop();

                // try to move right
                if (curr.right != null) {
                    stack.push(curr.right);
                    trav = curr.right;
                }

                return curr.value;
            }
        };
    }

    private Iterator<T> postOrderTraversal(Node<T> node) {
        Stack<Node<T>> stack1 = new Stack<>();
        Stack<Node<T>> stack2 = new Stack<>();
        if (node != null) {
            stack1.push(node);
        }
        while (!stack1.isEmpty()) {
            Node<T> curr = stack1.pop();
            stack2.push(curr);
            if (curr.left != null)
                stack1.push(curr.left);
            if (curr.right != null)
                stack1.push(curr.right);
        }

        return new Iterator<T>() {

            @Override
            public boolean hasNext() {
                return !stack2.isEmpty();
            }

            @Override
            public T next() {
                return stack2.pop().value;
            }
        };
    }

    private Iterator<T> levelOrderTraversal(Node<T> node) {
        Queue<Node<T>> queue = new LinkedList<>();
        if (node != null) {
            queue.add(node);
        }

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public T next() {
                Node<T> curr = queue.poll();
                if (curr.left != null)
                    queue.add(curr.left);
                if (curr.right != null)
                    queue.add(curr.right);
                return curr.value;
            }
        };
    }

    @Override
    public String toString() {
        if (this.size() == 0)
            return "[ ]";
        StringBuilder sb = new StringBuilder("[ ");
        this.forEach(x -> sb.append(x + " "));
        sb.append("]");
        return sb.toString();
    }
}

enum BTreeTraversalOrder {
    PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
}
