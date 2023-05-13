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

        int cur_sum = 0;
        for (int i=0; i<k; i++) {
            cur_sum += arr[i];
        }

        int min_sum = cur_sum;

        for (int i=k; i<n; i++) {
            cur_sum += arr[i] - arr[i-k];
            min_sum = Integer.min(min_sum, cur_sum);
        }

        return min_sum;
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
