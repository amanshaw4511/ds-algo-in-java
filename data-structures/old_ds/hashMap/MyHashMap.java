import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Pair{
    String key;
    int value;
    Pair(String key, int value){
        this.key = key;
        this.value = value;
    }
    
    public String key(){
        return this.key;
    }

    public int value(){
        return this.value;
    }
}


class MyHashMap{
    List<Pair>[] values;
    int length;
    int max_length;

    MyHashMap(int max_length){
        this.values = new LinkedList[max_length];
        for( int i=0; i<values.length; i++ )
            this.values[i] = new LinkedList<Pair>();

        this.length = 0;
        this.max_length = max_length;
    }

    private int hash(String key){
        int hashout = 0;
        for(int i=0; i < key.length(); i++){
            hashout += key.charAt(i) * (i+1);
        }
        hashout %= this.max_length;
        return hashout;
    }


    public boolean put(String key, int value){
        int keyHash = this.hash(key); 
        this.values[keyHash].add(new Pair(key,value));
        this.length++;
        return true;
    }

    public int get(String key){
        int keyHash = this.hash(key);
        for( Pair pair : this.values[keyHash]){
           if ( pair.key().equals(key))
               return pair.value();
        }
        System.out.println("Key Error");
        return 0;
    }

    public boolean remove(String key){
        int keyHash = this.hash(key);
        int i = 0;
        for( Pair pair : this.values[keyHash] ){
            if ( pair.key().equals(key) ){
                this.values[keyHash].remove(i);
                this.length--;
                return true;
            }
            i++;
        }
        this.length--;
        return false;
    }

    public LinkedList<String> keys(){
        LinkedList<String> keys = new LinkedList<>();
        for(int i=0; i<this.max_length; i++){
            for( Pair pair : this.values[i] ){
                keys.add(pair.key());
            }
        }
        return keys;
    }

    public LinkedList<Integer> values(){
        LinkedList<Integer> values = new LinkedList<>();
        for( int i=0; i<this.max_length; i++ ){
            for( Pair pair: this.values[i] )
                values.add(pair.value());
        }
        return values;
    }

    @Override
    public String toString(){
        String out = "{ ";
        for( int i=0; i<this.max_length; i++ ){
            for( Pair pair : this.values[i] ){
                out += pair.key() + " : " + pair.value() + ", ";
            }
        }
        out += "}";
        return out;
    }
}
