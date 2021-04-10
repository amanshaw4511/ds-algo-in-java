import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {

    static String solve2(String str, String pattern) {
        int n = str.length();
        int k = pattern.length();
        
        Map<Character,Integer> map = new HashMap<>();
        for (int i = 0; i<k; i++) {
            char ch = pattern.charAt(i);
            if (! map.containsKey(ch)) {
                map.put(ch, 1);
            }
        }

        // corner case
        if (n < map.size()) return "";

        // stores count of element in map value > 0
        int count = map.size();

        Pair resPair = new Pair(0,Integer.MAX_VALUE);

        int i = 0;
        int j = 0; 
        while (j < n) {
            char jch = str.charAt(j);
            // move j
            if (map.containsKey(jch)) {
                int jcount = map.get(jch);
                map.put(jch, --jcount);
                if (jcount == 0) {
                    count--;
                }
            }
            while (count == 0) {
                // update result
                resPair = Pair.min(resPair, new Pair(i,j));
                
                // move i
                char ich = str.charAt(i);
                if (map.containsKey(ich)) {
                    int icount = map.get(ich);
                    map.put(ich, ++icount);
                    if (icount == 1) {
                        count++;
                    }
                }
                i++;
            }
            j++;
        }

        // System.out.println(resPair);
        return resPair.value == Integer.MAX_VALUE ? "" :
                    str.substring(resPair.key, resPair.value +1);
    }


    public static void main(String... args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String pattern = br.readLine();

        String res = solve2(str, pattern);
        System.out.println(res);
    }
}

class Pair {
    int key;
    int value;
    public Pair(int key, int value) {
        this.key = key;
        this.value = value;
    }
    @Override
    public String toString() {
        return "Pair [key=" + key + ", value=" + value + "]";
    }

    public static Pair min(Pair p, Pair q) {
        return p.value - p.key < q.value - q.key ? p : q;
    }

}
