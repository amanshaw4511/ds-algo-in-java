import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
    public static int solve(String str, int k) {
        int n = str.length();
        
        int i = 0;
        int j = 0;
        int longest_substr = -1;

        Map<Character,Integer> charCount = new HashMap<>();


        while (j < n) {
 
            // calculation for j 
            char ch = str.charAt(j);
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);

            if (charCount.size() == k ) {
                // update ans
                longest_substr = Integer.max(longest_substr, j-i+1);
            }

            else if (charCount.size() > k) {
                while (charCount.size() > k) {
                    // calculation for i
                    char ich = str.charAt(i);
                    charCount.put(ich, charCount.get(ich) -1);
                    if (charCount.get(ich) == 0)
                        charCount.remove(ich);
                    i++;

                }
            }
            j++;
        }

        return longest_substr;
    }

    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        String str = br.readLine();

        int res = solve(str, k);
        System.out.println(res);
    }
}
