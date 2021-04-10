import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static int solve(String str) {
        int n = str.length();
        int k = 2; // no of unique char

        int i = 0;
        int j = 0;
        int longest_substr = 0;

        Map<Character,Integer> charCount = new HashMap<>();


        while (j < n) {
            // calc for j
            char ch = str.charAt(j);
            charCount.put(ch, charCount.getOrDefault(ch,0) + 1);

            if (charCount.size() == 2) {
                // update ans
                longest_substr = Integer.max(longest_substr, j-i+1);
            }

            else if (charCount.size() > 2) {
                while(charCount.size() >2) {
                    // update for i
                    char ich = str.charAt(i);
                    int icount = charCount.get(ich);
                    charCount.put(ich, --icount); 
                    if (icount == 0) {
                        charCount.remove(ich);
                    }
                    i++;
                }

            }
            j++;
        }
        return longest_substr;
    }


    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int res = solve(str);
        System.out.println(res);
    }
}
