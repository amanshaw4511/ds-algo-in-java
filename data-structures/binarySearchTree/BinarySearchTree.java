import java.util.LinkedList;
import java.util.Queue;

class Node {
    int value;
    Node left;
    Node right;

    Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class BinarySearchTree {
    Node root;
    int length;

    private Integer min(Node root) {
        if (root == null)
            return null;
        Node current = root;
        while (current.left != null)
            current = current.left;
        return current.value;
    }

    public BinarySearchTree() {
        this.root = null;
        this.length = 0;
    }

    public Node lookup(int value) {
        Node current = this.root;
        while (current != null) {
            if (value > current.value)
                current = current.right;
            else if (value < current.value)
                current = current.left;
            else
                return current;
        }
        return null;
    }

    public boolean add(int value) {
        Node newNode = new Node(value);
        // if BST is empty
        if (this.root == null) {
            this.root = newNode;
            return true;
        }

        Node current = this.root;
        while (true) {
            if (value >= current.value) {
                if (current.right == null) {
                    current.right = newNode;
                    return true;
                }
                current = current.right;
            } else if (value < current.value) {
                if (current.left == null) {
                    current.left = newNode;
                    return true;
                }
                current = current.left;
            }
        }
    }

    public void remove(int value) {
        this.removeHelper(this.root, value);
    }

    public Node removeHelper(Node current, int value) {
        if (current == null)
            return null;

        if (value > current.value) {
            current.right = this.removeHelper(current.right, value);
        } else if (value < current.value) {
            current.left = this.removeHelper(current.left, value);
        }
        // value == current.value
        else {
            // 1. node have no child
            if (current.left == null && current.right == null) {
                return null;
            }
            // 2. node have one child (left)
            if (current.right == null) {
                return current.left;
            }
            // 3. node have one child (right)
            if (current.left == null) {
                return current.right;
            }
            // 4. node have two child
            int minValue = this.min(current.right);
            current.value = minValue;
            current.right = this.removeHelper(current.right, minValue);
        }

        return current;

    }

    @Override
    public String toString() {
        String out = "";
        this.printTree();
        return out;
    }


    public void printTree() {
        LinkedList<Integer>[] ll = new LinkedList[50];
        for (int i = 0; i < ll.length; i++)
            ll[i] = new LinkedList<Integer>();
        if (this.root != null)
            ll[0].add(this.root.value);
        int pos = printTreeHelper(this.root, ll, 0);
        System.out.println(pos);
        for (int i = 0; i < ll.length; i++) {
            if (ll[i].isEmpty())
                break;
            ll[i].forEach(x -> System.out.print(x + " "));
            System.out.println();
        }
    }
    private int printTreeHelper(Node current, LinkedList<Integer>[] ll, int pos) {
        if (current == null)
            return pos;
        int posFromLeft = 0;
        int posFromRight = 0;

        // ll[pos].add(current.value);
        if (current.left != null) {
            ll[pos + 1].add(current.left.value);
            posFromLeft = printTreeHelper(current.left, ll, pos + 1);
        } else {
            ll[pos + 1].add(0);
        }
        if (current.right != null) {
            ll[pos + 1].add(current.right.value);
            posFromRight = printTreeHelper(current.right, ll, pos + 1);
        } else {
            ll[pos + 1].add(0);
        }
        return Integer.max(posFromRight, posFromLeft);
    }

    // dfs traversal
    public void inOrderTraverse(Node current) {
        if (current == null)
            return;
        this.inOrderTraverse(current.left);
        System.out.print(current.value + " ");
        this.inOrderTraverse(current.right);
    }

    public void preOrderTraverse(Node current) {
        if (current == null)
            return;
        System.out.print(current.value + " ");
        this.preOrderTraverse(current.left);
        this.preOrderTraverse(current.right);
    }

    public void postOrderTraverse(Node current) {
        if (current == null)
            return;
        this.postOrderTraverse(current.left);
        this.postOrderTraverse(current.right);
        System.out.print(current.value + " ");
    }

    public void bfsTraversal() {
        if (this.root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this.root);
        while (!queue.isEmpty()) {
            Node current = queue.remove();
            System.out.println(current.value + " ");
            if (current.left != null)
                queue.add(current.left);
            if (current.right != null)
                queue.add(current.right);

        }
    }

}
