class IndexException extends Exception{
	private static final long serialVersionUID = 1L;

	IndexException(String message){
        super(message);
    }    
}

class Node{
    int value;
    Node next;

    public Node(int value){
        this.value = value;
        this.next = null;
    }
}


class MyLinkedList{
    Node head;
    Node tail;
    int length;
    
    public MyLinkedList(){
        this.head = this.tail = null;
        this.length = 0;
    }

    public MyLinkedList(int[] values){
        ;
    }

    public int length(){
        return this.length;
    }

    private Node traverseToIndex(int index){
        if ( index >= this.length ){
            ;
        }
        Node current = this.head;
        for( int i=0; i<index ; i++ )
            current = current.next;
        return current;
    }

    private boolean checkIndex(int index){
        if (index < 0 || index >= this.length)
            return false;
        return true;
    }

    public boolean isEmpty(){
        if (this.head == null) 
            return true;
        return false;
    }

    public boolean push(int value){
        Node newNode = new Node(value);

        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            this.length++;
            return true;
        }

        this.tail.next = newNode;
        this.tail = newNode;
        this.length++;
        return true;
    }

    public boolean pushFront(int value){
        Node newNode = new Node(value);

        if (this.isEmpty()) {
            this.head = newNode;
            this.tail = newNode;
            this.length++;
            return true;
        }
        
        newNode.next = this.head;
        this.head = newNode;
        this.length++;
        return true;
    }
    
    public int pop(){
        int lastValue = this.tail.value;
        Node secondLast = this.traverseToIndex( this.length -2 );
        secondLast.next = null;
        this.tail = secondLast; 
        this.length--;
        return lastValue;
    }

    public int popFront(){
        int firstValue = this.head.value;
        this.head = this.head.next;
        this.length--;
        return firstValue;
    }

    public boolean insert(int index, int value) throws IndexException{
        if( index == 0 )
            return this.pushFront(value);
        if( index == this.length )
            return this.push(value);
        if (!this.checkIndex(index))
            throw new IndexException("Invalid Index");
        Node newNode = new Node(value);
        Node prevNode = this.traverseToIndex(index - 1);
        Node nextNode = prevNode.next;
        // prevNode -> newNode -> nextNode;
        prevNode.next = newNode;
        newNode.next = nextNode;
        this.length++;
        return true;
    }
    
    public boolean remove(int value){
        Node current = this.head;
        if ( current.value == value ){
            this.popFront();
            return true;
        }

        while ( current.next.next != null ){
            if ( current.next.value == value ){
                current.next = current.next.next; 
                this.length--;
                return true;
            }
            current = current.next;
        }

        if ( current.next.value == value ){
            this.pop();
            return true;
        }
        return false;
    }

    public boolean removeAt(int index) throws IndexException {
        if ( index == 0 ){
            this.popFront();
            return true;
        }
        if ( index == this.length -1 ){
            this.pop();
            return true;
        }
        if (! this.checkIndex(index) )
            throw new IndexException("Invalid Index");
        Node current = this.traverseToIndex(index -1 );
        current.next = current.next.next;
        return true;
    }

    @Override
    public String toString(){
        String out = "[ ";
        Node current = this.head;
        while (current != null){
            out = out + current.value + " ";
            current = current.next;
        }
        out = out + "]";
        return out;
    }

    public void reverse(){
        // if no element or only one element present
        if( this.head == null || this.head.next == null )
            return ;

        Node prevNode = this.head;
        Node currentNode = this.head.next;

        // for first node
        prevNode.next = null;
        this.tail = prevNode;

        while(currentNode != null){
           Node nextNode = currentNode.next;
           currentNode.next = prevNode;
           // increment
           prevNode = currentNode;
           currentNode = nextNode;
        }
        // for last node
        this.head = prevNode;
    }

}
