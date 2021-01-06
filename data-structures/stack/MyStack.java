
class MyStack{
    int[] values;
    int length;

    MyStack(int max_length){
        this.length = 0;
        this.values = new int[max_length];
    }

    public boolean push(int value){
        if( this.length == values.length ){
            System.out.println("Stack Overflow");
            return false;
        }
        this.values[this.length] = value;
        this.length++;
        return true;
    }

    public Integer pop(){
        if( this.length == 0 ){
            System.out.println("Stack Underflow");
            return null;
        }
        this.length--;
        return this.values[this.length];
    }

    public Integer peek(){
        if( this.length == 0 ){
            System.out.println("Stack is Empty");
            return null;
        }
        return this.values[this.length -1];
    }

    public boolean isEmpty(){
        return this.length == 0;
    }

    @Override
    public String toString(){
        String out = "Stack( ";
        for( int i=0; i<this.length ; i++ ){
            out += this.values[i] + " ";
        }
        out += ")";
        return out;
    }
}
