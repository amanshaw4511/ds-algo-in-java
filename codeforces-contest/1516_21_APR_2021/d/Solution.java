import java.io.*;
import java.util.*;

public class Solution {

    static void solve(int[] arr, int l, int r) {
        int n = arr.length;
        l--; r--; // change to 0 based index

        int prev = arr[l];
        int cnt = 1;
        for (int i=l; i<=r; i++) {
            if (arr[i] != prev) {
                // println(arr[i], prev, ": ", i);
                prev = arr[i];
                cnt++;
            }
        }
        println(l, r);
        println(cnt);

    }
    static int hcf(int a, int b) {
        if (a == 0) return b;
        return hcf(b%a, a);
    }
    static int lcm(int a, int b) {
        return (a*b)/hcf(a,b);
    }

    static int[] cal(int[] arr) {
        int n = arr.length;
        int out[] = new int[n];
        int curLcm = 1;
        int mul = 1;
        int k = 0;
        for (int i=0; i<n; i++) {
            curLcm = lcm(curLcm, arr[i]);
            mul = mul * arr[i];
            if (curLcm == mul) {
                out[i] = k;
            } else {
                curLcm = arr[i];
                mul = arr[i];
                out[i] = ++k;
            }
        }
        return out;
    }

    public static void main(String... args) throws IOException {
        FastReader fr = new FastReader();
        fr.loadLine();
        int n = fr.nextInt();
        int q = fr.nextInt();
        int[] arr = new int[n];
        fr.loadLine();
        for (int i=0; i<n; i++)
            arr[i] = fr.nextInt();

        int[] out = cal(arr);

        printArray(out);

        while (q-- > 0) {
            fr.loadLine();
            int l = fr.nextInt();
            int r = fr.nextInt();
            solve(out, l, r);
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

    static void printArray(int[] o) {
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
