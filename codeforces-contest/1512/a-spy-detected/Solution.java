import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {


    static int solve(int[] arr) {
        int n = arr.length;

        int repeating = 0;
        if (arr[0] == arr[1])
            repeating = arr[0];
        if (arr[1] == arr[2])
            repeating = arr[1];
        if (arr[0] == arr[2]) 
            repeating = arr[0];

        for (int i=0; i<n; i++) {
            if (arr[i] != repeating)
                return i+1;
        }
        return 0;

    }

    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int cases = Integer.parseInt(st.nextToken());
        while (cases-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];

            st = new StringTokenizer(br.readLine());
            for (int i=0; i<n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            int res = solve(arr);
            System.out.println(res);

        }
    }
}
