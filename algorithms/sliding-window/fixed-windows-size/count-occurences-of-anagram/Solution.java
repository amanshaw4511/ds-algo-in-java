import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    static boolean allZero(Map<Character,Integer> map) {
        for (Character s : map.keySet()) {
            if (map.get(s) != 0) 
                return false;
        }
        return true;
    }

    public static int solve(String str, String pattern) {
        int n = str.length();
        int k = pattern.length(); // window size
        if (k > n) {
            return 0;
        }

        Map<Character,Integer> letterCount = new HashMap<Character,Integer>();
        
        for (int i=0; i<k; i++) {
            char ch = pattern.charAt(i);
            int lcount = letterCount.getOrDefault(ch, 0);
            letterCount.put(ch, lcount+1);
        }

        int anagramCount = 0;
        int i = 0;
        int j = 0;

        while (j < k) {
            // calculation
            char ch = str.charAt(j);
            Integer lcount = letterCount.get(ch);
            if (lcount != null) {
                letterCount.put(ch, --lcount);
            }
            j++;
        }
        // update ans
        if (allZero(letterCount)) {
            anagramCount++;
        }

        while (j < n) {
            // calculation
            char ich = str.charAt(i);
            char jch = str.charAt(j);
            Integer icount = letterCount.get(ich);
            if (icount != null) {
                letterCount.put(ich, ++icount);
            }

            Integer jcount = letterCount.get(jch);
            if (jcount != null) {
                letterCount.put(jch, --jcount);
            }

            // update ans
            if (allZero(letterCount)) {
                anagramCount++;
            }

            // slide window
            i++;
            j++;
        }

        return anagramCount;
    }
    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String text = br.readLine();
        String pattern = br.readLine();


        int res = solve(text, pattern);
        System.out.println(res);
    }
}
