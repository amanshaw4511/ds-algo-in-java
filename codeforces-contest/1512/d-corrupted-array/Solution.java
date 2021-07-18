import java.io.*;
import java.util.*;

public class Solution {


    static int[] solve(int[] arr, int n) {
        int[] rrr = new int[n];

        Arrays.sort(arr);
        int sum = Arrays.stream(arr).sum();
        int max = arr[arr.length -1];
        int secondMax = arr[arr.length -2];

        print(arr);
        print(" ::", max, secondMax);

        int sumEle = max;
        int x = secondMax;
        boolean flag = false;
        if (sum - max == secondMax) {
            sumEle = secondMax;
            x = sum;
            flag = true;
        }
        int i = 0;
        while (i<n+2 && !flag) {
            if (sum - arr[i] == max) {
                sumEle = max;
                x = secondMax;
                flag = true;
                break;
            }
            i++;
        }

        if (!flag) {
            int[] r = {-1};
            return r;
        }

        int k = 0 ; // index of rrr
        for (int e : arr) {
            if (e != sumEle && e != x) 
                rrr[k++] = e;
        }

        return rrr;
    }

    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int cases = Integer.parseInt(st.nextToken());
        while (cases-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] arr = new int[n+2];
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int[] res = solve(arr,n);
            for (int x : res) {
                System.out.print(x + " ");
            }
            System.out.println();

        }
    }
    static void print(Object... o) {
        int n = o.length;
        for (int i=0; i<n-1; i++) {
            System.out.print(o[i] + " ");
        }
        System.out.println(o[n-1]);
    }
}


