
class BinaryTreeUsingArray{
    private int[] data;
    private int pos;

    public BinaryTreeUsingArray(int capacity) {
        this.data = new int[capacity + 1]; // use form index 1
        this.pos = 1;
    }
   // left child -> i*2
   // right child -> i*2 + 1
   // parent of child -> i/2

   
    public int getLeftChild() {
        return data[this.pos * 2];
    }
    public int getRightChild() {
        return data[this.pos * 2 + 1];
    }
    public int getParent() {
        return data[this.pos / 2];
    }

    public int gotoLeftChild() {
        this.pos = this.pos * 2;
        return this.pos;
    }
    public int gotoRightChild() {
        this.pos = this.pos * 2 + 1;
        return this.pos;
    }
    public int gotoParent() {
        this.pos = this.pos / 2;
        return this.pos;
    }


}
