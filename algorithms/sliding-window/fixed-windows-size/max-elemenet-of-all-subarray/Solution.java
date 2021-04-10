import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int[] solve(int[] arr, int k) {
        int n = arr.length;

        // invalid arr
        // if (k > n) {
            // return ;
        // }

        int[] res = new int[n-2];
        int r = 0; // index of res
        
        int i = 0;
        int j = 0;
        int curMax = Integer.MIN_VALUE;

        // first window
        while (j < k) {
            // calculation
            curMax = Integer.max(curMax, arr[j]);

            j++;
        }

        // update result
        res[r++] = curMax;

        while (j < n) {

            // calculation
            if (arr[i] == curMax) {
                for (int t=i+1; t<j; t++) {
                    curMax = Integer.max(curMax, arr[t]);
                }
            }
            curMax = Integer.max(curMax, arr[j]);

            // update ans
            res[r++] = curMax;

            // slide the window
            i++;
            j++;
        }

        return res;
    }
    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken()); 
        }

        int[] res = solve(arr, k);

        for (int ele : res)
            System.out.print(ele + " ");
    }
}
