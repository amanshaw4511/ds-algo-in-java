import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {
    public static int[] solve(int[] arr1, int[] arr2, int n, int m) {
        int i=0;
        int j=0;
        int[] ans = new int[m];
        while (j < m) {
            while (i < n && arr1[i] < arr2[j]) {
                i++;
            } 
            ans[j] = i;
            j++;
        }
        return ans;
    }


    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr1 = new int[n];
        int[] arr2 = new int[m];
        for (int i=0; i<n; i++) 
            arr1[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int j=0; j<m; j++)
            arr2[j] = Integer.parseInt(st.nextToken());

        int[] res = solve(arr1, arr2, n, m); 
        for (int ele : res) 
            System.out.println(ele);

    }
}
