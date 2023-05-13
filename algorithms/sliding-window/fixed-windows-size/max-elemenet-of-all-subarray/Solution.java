import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    public static int[] solve(int[] arr, int k) {
        int n = arr.length;

        List<Integer> result = new ArrayList<>();

        int curMax = 0;
        int curSum = 0;

        for (int i=0; i<k; i++) {
            curSum += arr[i];
            curMax = Math.max(curMax, arr[i]);
        }

        result.add(curMax);

        for (int i=k; i<n; i++) {
            int right = i;
            int left = i-k;
            curSum += arr[right] - arr[left];

            if (arr[right] > curMax) {
                curMax = arr[right];
            }
            if (arr[left] == curMax) {
                curMax = max(arr, left+1, right+1);
            }
            result.add(curMax);
        }

        return result.stream().mapToInt(x->x).toArray();
    }

    public static int max(int[] arr, int start, int end) {
        int m = 0;
        for (int i=start; i<end; i++) {
            m = Math.max(m, arr[i]);
        }
        return m;
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
