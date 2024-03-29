import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static int solve(int[] arr, int[] brr, int n, int m) {
        int count = 0;
        int i = 0;
        int j = 0;
        while (i<n && j<m) {
            if (arr[i] == brr[j]) {
                int ifreq = 1;
                int jfreq = 1;
                
                int ii = i+1;
                int jj = j+1;

                // frequency of arr[i]
                while (ii < n && arr[ii] == arr[i]) {
                    ifreq++;
                    ii++;
                }
                // frequency of brr[j]
                while (jj < m && brr[jj] == brr[j]) {
                    jfreq++;
                    jj++;
                }
                count += ifreq * jfreq;

                i = ii;
                j = jj;

            }
            else if (arr[i] < brr[j])
                i++;
            else 
                j++;
        }

        return count;
    }

    public static void main(String... args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] brr = new int[m];
        for (int i=0; i<n; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int j=0; j<m; j++) 
            brr[j] = Integer.parseInt(st.nextToken());

        int res = solve(arr, brr, n, m);

        System.out.println(res);

    }
}
