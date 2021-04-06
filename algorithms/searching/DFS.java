import BinarySearchTree;
import MyQueue;

class DFS extends BinarySearchTree {
    public void traverse(){
       if (this.root == null)
           return ;
       MyQueue queue = new MyQueue(); 
       stack.enqueue(root.value);
       while (! stack.isEmpty()) {
            Node popedNode = stack.dequeue();
            System.out.println(popedNode.value);
            if (popedNode->left != null) 
                queue.enqueue(popedNode.left);
            if (popedNode->right != null)
                queue.enqueue(popedNode.right);
       }
    }
}
