
class QueueUsingStack{
    MyStack stack1 ;
    MyStack stack2 ;

    QueueUsingStack(int max_size){
        int stack1size;
        int stack2size;
        if( max_size % 2 == 0 ){
            stack1size = max_size/2;
            stack2size = max_size/2;
        }else{
            stack1size = (int)max_size/2;
            stack2size = ( (int)max_size/2 );
        }

        this.stack1 = new MyStack(stack1size);
        this.stack2 = new MyStack(stack2size);
    }

    public boolean enqueue(int value){
        stack1.push(value);
        return true;
    }

    public Integer dequeue(){
        this.peek();
        return this.stack2.pop();
    }

    public Integer peek(){
        if( stack2.isEmpty() ){
            this.moveItemsToStack2();
        }
        if( stack2.isEmpty() ){
            System.out.println("Queue is Empty");
            return null;
        }
        return this.stack2.peek();

    }

    public boolean isEmpty(){
        return stack1.isEmpty() && stack2.isEmpty();
    }

    private void moveItemsToStack2(){
        while(! stack1.isEmpty() ){
            stack2.push(stack1.pop());
        }

    }
}
