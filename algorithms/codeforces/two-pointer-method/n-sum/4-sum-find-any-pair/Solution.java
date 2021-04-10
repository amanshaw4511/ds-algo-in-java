import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class Solution {

    public static List<List<Integer>> solve(int[] arr, int sum) {
        int n = arr.length;
        Map<Integer,Pair> sumPair = new HashMap<>();
        List<List<Integer>> resList = new ArrayList<>();
        
        // save all 2sums pair
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                int lsum = arr[i] + arr[j];
                sumPair.put(lsum, new Pair(i,j));
            }
        }
        System.out.println(sumPair);


        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                int lsum = sum - arr[i] - arr[j];
                Pair pair = sumPair.get(lsum);
                System.out.println(lsum + ": "+ pair);
                if (pair != null && 
                        noCommon(i, j, pair.key, pair.value)) {
                    resList.add(List.of(arr[i], arr[j], arr[pair.key], arr[pair.value]));
                }
            }
        }
        return resList;
    }
    private static boolean noCommon(int a1, int b1, int a2, int b2) {
        return a1 != a2 && a1 != b2 && b1 != a2 && b1 != b2;
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
        return "(" + key + "," + value + ")";
    }

}
