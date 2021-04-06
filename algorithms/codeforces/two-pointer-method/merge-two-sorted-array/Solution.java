import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution {


    public static int[] solve(int[] arr1, int[] arr2) {
        int i = 0;
        int j = 0;
        int k = 0;
        int[] res = new int[arr1.length + arr2.length];

        while (i<arr1.length && j<arr2.length) {
            if (arr1[i] < arr2[j]) {
                res[k++] = arr1[i++];
            }
            else {
                res[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            res[k++] = arr1[i++];
        }
        
        while (j < arr2.length) {
            res[k++] = arr2[j++];
        }

        return res;
    }

    public static int[] get(int[] arr1, int[] arr2) {
        return solve(arr1, arr2);
    }

    public static void main(String... args) throws IOException  {
        FastReader fr = new FastReader();

        fr.readLine();
        int n = fr.nextInt();
        int m = fr.nextInt();

        int[] arr1 = new int[n];
        int[] arr2 = new int[m];

        fr.readLine();
        for (int i=0; i<n; i++) 
            arr1[i] = fr.nextInt();

        fr.readLine();
        for (int j=0; j<m; j++)
            arr2[j] = fr.nextInt();

        // int[] arr1 = Arrays.stream(sc.nextLine().split(" "))
        //             .map(Integer::parseInt)
        //             .mapToInt(x->x)
        //             .toArray();
        // int[] arr2 = Arrays.stream(sc.nextLine().split(" "))
        //             .map(Integer::parseInt)
        //             .mapToInt(x->x)
        //             .toArray();

        int[] ans = solve(arr1, arr2);
        for (int i=0; i<n+m; i++) 
            System.out.println(ans[i]);
  }


}

class FastReader {
    BufferedReader br ; 
    StringTokenizer st ;

    public FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    void readLine() {
        try {
            st = new StringTokenizer(br.readLine());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    long nextLong() {
        System.out.println("in long");
        return Long.parseLong(st.nextToken());
    }

    double nextDouble() {
        System.out.println("in double");
        return Double.parseDouble(st.nextToken());
    }

    String nextString() {
        return st.nextToken();
    }

    String nextLine() {
        String str = "";
        try {
            str = br.readLine();
        } catch(IOException e) {
            e.printStackTrace();
        }
        return str;
    }

}
