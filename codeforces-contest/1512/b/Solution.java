import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {


    static void solve(int x1, int x2, int y1, int y2, int n) {
        
        int ox1 = 0;
        int ox2 = 0;
        int oy1 = 0;
        int oy2 = 0;

        if (y1 == y2) {
            ox1 = x1;
            ox2 = x2;

            if (y1 +1 < n) {
                oy1 = y1+1;
                oy2 = y2+1;
            }
            else {
                oy1 = y1-1;
                oy2 = y2-1;
            }
        }
        else if( x1 == x2 ){
            oy1 = y1;
            oy2 = y2;

            if (x1 +1 < n) {

                ox1 = x1+1;
                ox2 = x2+1;
            }
            else {
                ox1 = x1-1;
                ox2 = x2-1;
            }

        }
        else if (x1 > x2 ) {
            ox1 = x1;
            oy1 = y2;

            ox2 = x2;
            oy2 = y1;
        }
        else {
            ox1 = x2;
            oy1 = y1;

            ox2 = x1;
            oy2 = y2;
        
        }
        for (int i=0; i< n; i++) {
            for (int j=0; j<n; j++) {
                if (i == x1 && j == y1)
                    System.out.print("*");
                else if(i == x2 && j == y2)
                    System.out.print("*");

                else if (i == ox1 && j == oy1)
                    System.out.print("*");
                else if (i == ox2 && j == oy2)
                    System.out.print("*");
                else 
                    System.out.print(".");
            }
            System.out.println();
        }

    }

    public static void main(String... args) throws IOException {

        FastReader fr = new FastReader();

        fr.loadLine();
        int cases = fr.nextInt();
        while (cases-- > 0) {
            fr.loadLine();
            int n = fr.nextInt();
 
            List<Integer> x = new ArrayList<>();
            List<Integer> y = new ArrayList<>();


            for (int i=0; i<n; i++) {
                String s = fr.readLine();
                for (int j=0; j<s.length(); j++) {
                    if (s.charAt(j) == '*') {
                        x.add(i);
                        y.add(j);
                    }
                }

            }

        solve(x.get(0), x.get(1), y.get(0), y.get(1), n);
        }
    }
}

class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String readLine() throws IOException {
        return br.readLine();
    }

    void loadLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    int nextInt() {
        return Integer.parseInt(st.nextToken());
    }

    long nextLong() {
        return Long.parseLong(st.nextToken());
    }

    double nextDouble() {
        return Double.parseDouble(st.nextToken());
    }

    char nextChar() {
        return st.nextToken().charAt(0);
    }

    String nextString() {
        return st.nextToken();
    }

}
