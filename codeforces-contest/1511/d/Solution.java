import java.io.*;
import java.util.*;

public class Solution {

    static void solve(int n, int k) {
        char[] arr = new char[n];

        if (k == 1) {
            for (int i=0; i<n; i++) {
                System.out.print('a');
            }
            System.out.println();
            return;
        }

        Arrays.fill(arr,'a');
        char b = 'b';
        char c = 'c';
        int j = 0;
        for (int i=2; i<n; i+=2) {
          arr[i] = (j%2 == 0) ? b  : c; 
        }
        System.out.println(String.valueOf(arr));
    }

    public static void main(String... args) throws IOException {
        FastReader fr = new FastReader();
        fr.loadLine();
        int n = fr.nextInt();
        int k = fr.nextInt();
        solve(n, k);

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
