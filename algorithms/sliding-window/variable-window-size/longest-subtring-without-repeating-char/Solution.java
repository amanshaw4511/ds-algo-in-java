import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int solve(String str) {
        int n = str.length();

        int i = 0;
        int j = 0;
        int longest_substr = 0;

        Map<Character,Integer> charCount = new HashMap<>();


        while (j < n) {

            // calculation for j 
            char ch = str.charAt(j);
            int lcount = charCount.getOrDefault(ch, 0);
            charCount.put(ch, ++lcount);

            System.out.println(ch + " count:" + lcount );
            // update
            if (lcount <= 1) {
                longest_substr = Integer.max(longest_substr, charCount.size());
            }

            if (lcount > 1) {

                while(charCount.get(ch) > 1) { 
                    char ich = str.charAt(i);
                    int icount = charCount.get(ich);
                    charCount.put(ich, --icount);
                    // System.out.println(ich + " count:" + icount );

                    if (icount == 0) {
                        charCount.remove(ich);
                        System.out.println("removed :"+ ich);
                    }
                    i++;
                }
                // do one more time for str[i]
            }
            System.out.println(" i:" + i + " j:"+ j +" size:" + charCount.size());

            j++;
        }
        return longest_substr;
    }

    static int solve2(String str) {
        int n = str.length();
        int max = 0;
        int cur_max = 0;
        int i = 0;
        int j = 0;
        Map<Character,Integer> charIndex = new HashMap<>();
        while (j < n) {
            char ch = str.charAt(j);
            Integer jindex = charIndex.get(ch);
            charIndex.put(ch, j);
            
            if (jindex == null || jindex < i) {
                cur_max++;
            }
            else {
                max = Integer.max(max, cur_max);
                cur_max = j - jindex;
                i = jindex +1;
            }
            
            j++;
        }
        return max;
    }

    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int res = solve2(str);
        System.out.println(res);
    }
}
