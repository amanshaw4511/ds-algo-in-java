
import java.util.LinkedList;

class Node{
    int value;
    Node next;
    Node(int value){
        this.value = value;
        this.next = null;
    }
}

class MyQueue{
    Node head;
    Node tail;
    int length;

    MyQueue(){
        this.head = null;
        this.tail = null;
        this.length = 0;
    }
    
    public boolean enqueue(int value){
        Node newNode = new Node(value);
        if( this.head == null ){
            this.head = newNode;
            this.tail = newNode;
        }else{
        this.tail.next = newNode;
        this.tail = newNode;
        }
        this.length++ ;
        return true;
    }

    public Integer dequeue(){
        if( this.head == null ){
            System.out.println("Queue is empty");
            return null;
        }
        int dequeueValue = this.head.value;
        this.head = this.head.next;
        this.length-- ;
        return dequeueValue;
    }

    public Integer peek(){
        if( this.head == null ){
            System.out.println("Queue is empty");
            return null;
        }
        return this.head.value;
    }

    public boolean isEmpty(){
        return this.head == null;
    }

    @Override
    public String toString(){
        String out = "Queue( ";
        Node current = this.head;
        while(current != null){
            out += current.value + " ";
            current = current.next;
        }
        out += ")";
        return out;
    }

}
    

