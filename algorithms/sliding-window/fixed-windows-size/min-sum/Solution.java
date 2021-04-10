import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int solve(int[] arr, int k) {
        int n = arr.length;

        // invalid arr
        if (n < k) {
            return -1;
        }

        int i = 0;
        int j = 0;

        int cur_sum = 0;
        int max_sum = Integer.MAX_VALUE;

        while (j < k) {
            // calculation
            cur_sum += arr[j];

            j++;
        }

        // update ans
        max_sum = cur_sum;
        while (j < n) {
            // calculation
            cur_sum += arr[j] - arr[i];

            // update ans
            max_sum = Integer.max(max_sum, cur_sum);

            // slide window
            i++;
            j++;
        }

        return max_sum;
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

        int res = solve(arr, k);
        System.out.println(res);
    }

    public static int minSum(int[] arr, int k) {
        int n = arr.length;

        if (n < k) return -1;

        int i = 0;
        int j = 0;
        int min_sum = Integer.MAX_VALUE;
        int cur_sum = 0;

        while (j < k) {
            cur_sum += arr[j];
            j++;
        }

        min_sum = cur_sum;
        while (j < n) {
            cur_sum += arr[j] - arr[i];
            min_sum = Integer.min(min_sum, cur_sum);
            i++;
            j++;
        }
        return min_sum;
    }
}
