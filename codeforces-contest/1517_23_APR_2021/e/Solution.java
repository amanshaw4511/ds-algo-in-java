import java.io.*;
import java.util.*;

public class Solution {

    // static int solve(int[] arr) {
    //     int n = arr.length;
    //     return 0;
    // }

    public static void main(String... args) throws IOException {
        FastReader fr = new FastReader();
        fr.loadLine();
        int cases = fr.nextInt();

        while (cases-- > 0) {

            // fr.loadLine();
            // int n = fr.nextInt();

            // int[] arr = new int[n];

            // fr.loadLine();
            // for (int i=0; i<n; i++)
            //     arr[i] = fr.nextInt();

            // int res = solve(arr);
            // System.out.println(res);

        }
    }

    
    static void println(Object... o) {
        int n = o.length;
        for (int i=0; i<n-1; i++)
            System.out.print(o[i] + " ");
        System.out.println(o[n-1]);
    }

    static void print(Object... o) {
        int n = o.length;
        for (int i=0; i<n-1; i++)
            System.out.print(o[i] + " ");
        System.out.print(o[n-1]);
    }

    static void printArray(Object[] o) {
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
