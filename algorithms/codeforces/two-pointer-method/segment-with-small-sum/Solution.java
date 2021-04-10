import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    public static int solve(int[] arr, int sum) {
        int n = arr.length;
        int cur_sum = 0;
        int longest_good_seg = 0;

        int i = 0;
        int j = 0;
        while (j < n) {
            cur_sum += arr[j];

            while (cur_sum > sum && i < n) {
                cur_sum -= arr[i];
                i++;
            }
            longest_good_seg = Integer.max(longest_good_seg, j - i + 1);
            j++;
        }

        return longest_good_seg;
    }

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int sum = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) 
            arr[i] = Integer.parseInt(st.nextToken());

        int ans = solve(arr, sum);
        System.out.println(ans);
    }
}
