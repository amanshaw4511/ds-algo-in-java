import java.io.*;
import java.util.*;

public class Solution {


    static int solve(int[] arr, int[] zrr, int zlen, int qcolor) {
        int n = arr.length;

        int pos = 0;
        for (int i=zlen-1; i>=0; i--) {
            if (zrr[i] == qcolor) {
                System.out.print(pos +1);
                zrr[zlen] = zrr[i];
                zrr[i] = -1;
                return zlen + 1;
            }
            if (zrr[i] != -1) pos++;
        }

        for (int i=0; i<n; i++) {
            if (arr[i] == qcolor) {
                System.out.print(pos +1);
                zrr[zlen] = arr[i];
                arr[i] = -1;
                return zlen +1;
            }
            if (arr[i] != -1) pos++;
        }

        return zlen;

    }
    static solve2(int arr[], int qcolor) {
        int n = arr.length;
        Map<Integer,Integer> indexMap = HashMap<>(); 
        for (int i=0; i<n; i++) {
            if (! indexMap.containsKey(arr[i])) indexMap.put(arr[i],i);
        }

        int qindex = indexMap.get(qcolor);
        indexMap.put(qcolor, 0);
        for (Integer key : indexMap.keySet()) {
            if (indexMap.get(key) < qindex) 
               indexMap.put(key, indexMap.get(key) - 1);
        }
        System.out.print(qindex +1);
    }

    public static void main(String... args) throws IOException {
        FastReader fr = new FastReader();
        fr.loadLine();
        int n = fr.nextInt();
        int q = fr.nextInt();

        fr.loadLine();
        int[] arr = new int[n];
        int[] zrr = new int[n];
        int zlen = 0;
        for (int i=0; i<n; i++)
            arr[i] = fr.nextInt();

        fr.loadLine();
        for (int i=0; i<q; i++) {
            int qcolor = fr.nextInt();
            zlen = solve(arr, zrr, zlen, qcolor);
            if (i != q-1) {
                System.out.print(" ");
            }
        }
        System.out.println();
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
