import java.io.*;
import java.util.*;

public class Solution {


    static String solve(String str) {
        int n = str.length();
        if (n%3 != 0) return "NO";

        int k = n/3;

        int t_cnt = 0;
        int m_cnt = 0;
        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (ch == 'T') t_cnt++;
            else if (ch == 'M') {
                m_cnt++;

                if (t_cnt < m_cnt || (2*k - t_cnt) < m_cnt) 
                    return "NO";
            }
        }
        if (m_cnt != k) return "NO";
        return "YES";
    }

    public static void main(String... args) throws IOException {
        FastReader fr = new FastReader();
        fr.loadLine();
        int cases = fr.nextInt();

        while (cases-- > 0) {

            fr.loadLine();
            int n = fr.nextInt();


            String str = fr.readLine();

            String ans = solve(str);
            print(ans);

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
