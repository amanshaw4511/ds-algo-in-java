import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int solve(int[] arr, int sum) {
        int n = arr.length;
        
        int longest_subarray = 0;

        int i = 0;
        int j = 0;
        int cur_sum = 0;

        while (j < n) {

            // calculation for j 
            cur_sum += arr[j];

            if (cur_sum == sum) {
                // update ans
                longest_subarray = Integer.max(longest_subarray, j-i+1);
            }

            else if (cur_sum > sum) {
                while (cur_sum > sum) {
                    // calculation for i
                    cur_sum -= arr[i];
                    i++;

                }
                if (cur_sum == sum) {
                    // update ans
                    longest_subarray = Integer.max(longest_subarray, j-i+1);
                }
            }
            j++;
        }


        return longest_subarray;
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
}
