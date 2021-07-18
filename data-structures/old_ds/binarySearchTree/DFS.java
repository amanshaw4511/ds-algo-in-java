import java.util.LinkedList;
import java.util.Queue;

import BinarySearchTree;
import Node;

class DFS extends BinarySearchTree {
    public void traverse(){
       if (this.root == null)
           return ;
       Queue<Node> queue = new LinkedList<>(); 
       queue.add(root.value);
       while (! queue.isEmpty()) {
            Node popedNode = queue.remove();
            System.out.println(popedNode.value);
            if (popedNode->left != null) 
                queue.add(popedNode.left);
            if (popedNode->right != null)
                queue.add(popedNode.right);
       }
    }
}
