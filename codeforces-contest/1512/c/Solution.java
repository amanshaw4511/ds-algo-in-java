import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {


    static String solve(String str, int a, int b) {
        int n = str.length();
        int[] count = {a,b};

        int filled = 0;
        char[] s = str.toCharArray(); 

        // System.out.println(String.valueOf(s));

        int i=0;
        int j = n-1;
        while (i<j) {
            char ich = s[i];
            char jch = s[j];

            if (ich != '?') {
                count[Integer.parseInt(String.valueOf(ich))]--;
                filled++; 
                if (jch == '?') {
                    s[j] = ich;
                    count[Integer.parseInt(String.valueOf(ich))]--;
                    filled++; 
                }

            }
            if (jch != '?') {
                count[Integer.parseInt(String.valueOf(jch))]--;
                filled++; 
                if (ich == '?') {
                    s[i] = jch;
                    count[Integer.parseInt(String.valueOf(jch))]--;
                    filled++; 
                }

            }
            // System.out.println(filled);

            // check for palindram
            if (ich != '?' && jch != '?') {
                if (ich != jch) return "-1";
            }

            i++;
            j--;
        }
        if (i == j && s[i] != '?') {
            filled++;
            count[Integer.parseInt(String.valueOf(s[i]))]--;
        }
        a = count[0];
        b = count[1];

        // System.out.println(String.valueOf(s));
        // System.out.println("a:" +a+ " b:" +b+ " filled:" +filled);

        // if (a+b != unfilled) System.out.println(-1);

        i = 0; j = n-1;
        while (i < j) {
            if ( s[i] == '?' /* && j == '?' */ ) {
                if (a > 1) {
                    s[i] = '0';
                    s[j] = '0';
                    a -= 2;
                    filled += 2;
                }
                else if(b >1) {
                    s[i] = '1';
                    s[j] = '1';
                    b -= 2;
                    filled += 2;
                }
            }

            i++;
            j--;
        }

        if (i == j && s[i] == '?') {
            if (a>0) {
                s[i] = '0';
                a--;
                filled++; }
            if (b>0) {
                s[i] = '1';
                b--;
                filled++;
            }
        }
        
        
        if (filled != n ||  a !=0 || b != 0) return "-1";
        else return String.valueOf(s);
        // System.out.println("a:" +a+ " b:" +b+ " unfilled:" +unfilled);
        // System.out.println(String.valueOf(s));
        // System.out.println("........................");


    }

    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        st = new StringTokenizer(br.readLine());
        int cases = Integer.parseInt(st.nextToken());
        while (cases-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            String str = br.readLine();

            String res = solve(str, a, b);
            System.out.println(res);

        }
    }
}
