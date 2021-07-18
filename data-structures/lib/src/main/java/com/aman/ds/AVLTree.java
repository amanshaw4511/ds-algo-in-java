import java.util.Iterator;
import java.util.Stack;
import java.util.stream.StreamSupport;

class AVLTree<T extends Comparable<T>> implements Iterable<T> {
    class Node<U> {
        U value;
        Node<U> left;
        Node<U> right;
        int height;

        Node(U value) {
            this.value = value;
            this.height = 1;
        }
    }

    private Node<T> root;
    private int size;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private int height(Node<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private Node<T> rotateLeft(Node<T> a) {
        Node<T> b = a.right;
        Node<T> T2 = b.left;

        b.left = a;
        a.right = T2;

        // update height
        a.height = 1 + Math.max(this.height(a.left), this.height(a.right));
        b.height = 1 + Math.max(this.height(b.left), this.height(b.right));
        return b;
    }

    private Node<T> rotateRight(Node<T> a) {
        Node<T> b = a.left;
        Node<T> T2 = b.right;

        b.right = a;
        a.left = T2;

        // update height
        a.height = 1 + Math.max(this.height(a.left), this.height(a.right));
        b.height = 1 + Math.max(this.height(b.left), this.height(b.right));
        return b;
    }

    private int balenceFactor(Node<T> node) {
        return Math.abs(node.left.height - node.right.height);
    }

    public boolean contains(T value) {
        return find(this.root, value) != null;
    }

    private Node<T> find(Node<T> curr, T value) {
        if (curr == null) {
            return null;
        }
        int cmp = value.compareTo(curr.value);

        if (cmp < 0) {
            return find(curr.left, value);
        }
        if (cmp > 0) {
            return find(curr.right, value);
        } 

        return curr;
    }

    public boolean insert(T value) {
        if (value == null) {
            return false;
        }
        if (!this.contains(value)) {
            this.root = this.insert(this.root, value);
            this.size += 1;
            return true;
        }
        return false;
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) {
            return new Node<T>(value);
        }

        if (value.compareTo(node.value) < 0) {
            node.left = this.insert(node.left, value);
        } else {
            node.right = this.insert(node.right, value);
        }

        return this.balence(node, value);
    }

    private Node<T> balence(Node<T> node, T value) {
        int balenceFactor = balenceFactor(node);
        if (balenceFactor > 1 && value.compareTo(node.left.value) < 0)  { // LL
            return this.rotateRight(node);
        } else if (balenceFactor < -1 && value.compareTo(node.right.value) > 0 ) { //RR
            return this.rotateLeft(node);
        } else if (balenceFactor > 1 && value.compareTo(node.left.value) > 0) { // LR
            node.left = this.rotateLeft(node.left);
            return this.rotateRight(node);
        } else if (balenceFactor < -1 && value.compareTo(node.right.value) < 0) { // RL
            node.right = this.rotateRight(node.right);
            return this.rotateLeft(node);
        }

        // already balenced  (balenceFactor = [-1,0,1])
        return node;


    }

    public boolean remove(T value) {
        if (value == null) {
            return false;
        }
        if (this.contains(value)) {
            this.root = this.remove(this.root, value);
            this.size -= 1;
            return true;
        }
        return false;
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) {
            return null;
        }

        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = this.remove(node.left, value);
        } else if(cmp > 0) {
            node.right = this.remove(node.right, value);
        } else { // matched
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else { // node.right != null && node.left != null
                Node<T> leftMaxNode = this.maxNode(node.left);
                node.value = leftMaxNode.value;
                node.left = this.remove(node.left, leftMaxNode.value);
            }
        }
        node.height = 1 + Math.max(this.height(node.left), this.height(node.right));

        return this.balence(node, value);
    }

    private Node<T> maxNode(Node<T> node) {
        Node<T> curr = node;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }

	@Override
	public Iterator<T> iterator() {
        Stack<Node<T>> stack = new Stack<>();
        if (this.root != null) {
            stack.push(root);
        }
        return new Iterator<T>() {
            Node<T> trav = root;
			@Override
			public boolean hasNext() {
                return !stack.isEmpty();
			}

			@Override
			public T next() {
                while (trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }
                Node<T> curr = stack.pop();
                if (curr.right != null) {
                    stack.push(curr.right);
                    trav = curr.right;
                }
				return curr.value;
			}
        };
	}

    @Override
    public String toString() {
        if (this.size == 0) return "[ ]";
        StringBuilder sb = new StringBuilder("[ ");
        this.forEach(e -> sb.append(e + ", "));
        sb.delete(this.size-2, this.size);
        return sb.toString();
    }


    public boolean isValidBST() {
        T prev = this.root.value;
        for (T value : this) {
            if (value.compareTo(prev) > 0) {
                return false;
            }
            prev = value;
        }
        return true;
    }

    private T prev = null;
    public boolean isValidBST(Node<T> curr) {
        if (curr == null) {
            return true;
        }
        if (!isValidBST(curr.left)) {
            return false;
        }
        if (prev != null && prev.compareTo(curr.value) >= 0) {
            return false;
        }
        prev = curr.value;

        return isValidBST(curr.right);

    }


    public boolean isValidBST(Node<T> curr, T min, T max) {
        if (curr == null) {
            return true;
        }
        if (curr.value.compareTo(min) <= 0 || curr.value.compareTo(max) >= 0) {
            return false;
        }

        return isValidBST(curr.left, min, curr.value) && isValidBST(curr.right, curr.value, max);
    }

    public boolean isValidBST(Node<T> curr, Node<T> min, Node<T> max) {
        if (curr == null) {
            return true;
        }
        if (min != null && curr.value.compareTo(min.value) <= 0) {
            return false;
        }
        if (max != null && curr.value.compareTo(max.value) >= 0) {
            return false;
        }
        return isValidBST(curr.left, min, curr) && isValidBST(curr.right, curr, max);
    }

}
