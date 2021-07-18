import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {


    static void solve(int n,int k) {
        int possiblek = (n%2 == 0) ? n/2 -1 : n/2;
        if (k > possiblek) {
            print(-1);return;
        }

        if (n == 0) { print(-1); return; };
        if (n == 1) {
            if (k==0) {print(1) ; return ;}
            if (k>0) {print(-1) ; return ;}
        }

        if (n == 2) {
            if (k==0) {print(1,2) ; return;}
            if (k>0) {print(-1); return ;}
        }

        int[] arr = IntStream.rangeClosed(1,n).toArray();

        int j=2;
        int i=1;
        while(k-- > 0) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            j += 2;
            i +=2 ;
        }
        for (int x=0; x< n-1; x++) {
            System.out.print(arr[x]+ " ");
        }
        System.out.println(arr[n-1]);
        
    }

    

    public static void main(String... args) throws IOException {
        FastReader fr = new FastReader();
        fr.loadLine();
        int cases = fr.nextInt();

        while (cases-- > 0) {

            fr.loadLine();
            int n = fr.nextInt();
            int k = fr.nextInt();
            solve(n,k);

        }
    }

    static void print(Object... o) {
        int n = o.length;
        for (int i=0; i<n-1; i++)
            System.out.print(o[i] + " ");
        System.out.println(o[n-1]);
    }
}

class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String readLine() throws IOException {
        return br.readLine();
    }

    void loadLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    long nextLong() {
        return Long.parseLong(st.nextToken());
    }

    double nextDouble() {
        return Double.parseDouble(st.nextToken());
    }

    char nextChar() {
        return st.nextToken().charAt(0);
    }

    String nextString() {
        return st.nextToken();
    }

}

class Pair<K,V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + ", " + value + ")";
    }

}
