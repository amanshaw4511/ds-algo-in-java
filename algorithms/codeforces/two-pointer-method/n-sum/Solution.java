import java.util.List;
import java.util.ArrayList;

public class Solution {
    public static List<List<Integer>> solve(int[] arr, int n, int sum) {
        return nSum(arr, 0, sum, n);
    }

    public static List<List<Integer>> nSum(int[] arr, int start, int sum, int k) { // O (n^(k-1))
        if (k == 2) {
            return twoSum(arr, start, sum);
        }
        //
        int n = arr.length;
        List<List<Integer>> resList = new ArrayList<>();

        for (int i=start; i<n; i++) {
            int lsum = sum - arr[i]; 
            List<List<Integer>> nMinusOneSumList = nSum(arr, i+1, lsum, k-1);
            if (nMinusOneSumList.size() > 0) {
                for (List<Integer> nMinusOneSum : nMinusOneSumList) {
                    // make n sum 
                    nMinusOneSum.add(arr[i]);
                    // add n sum to result
                    resList.add(nMinusOneSum);
                }
            }
        }
        return resList;
    }

//     public static List<List<Integer>> threeSum(int[] arr, int sum) { // O (n^2)
//         int n = arr.length;
//         List<List<Integer>> resList = new ArrayList<>();
        

//         for (int i=0; i<n; i++) {
//             int lsum = sum - arr[i];
//             List<List<Integer>> localResList = twoSum(arr, i+1,  lsum);
//             if (localResList.size() > 0) {
//                 for (List<Integer> twoSum : localResList) {
//                     // make 3 sum
//                     twoSum.add(arr[i]);
//                     // add 3 sum to res
//                     resList.add(twoSum);
//                 } 
//             } 
            
//         }
//         return resList;
//     }


    public static List<List<Integer>> twoSum(int[] arr, int start, int sum) { // O (n)
        int n = arr.length;
        int i = start;
        int j = n -1;
        List<List<Integer>> resList = new ArrayList<>();

        while (i < j) {
            int lsum = arr[i] + arr[j];
            if (lsum == sum) {
                // if arr[i] and arr[j] are equal
                if (arr[i] == arr[j]) {
                    int freqMinusOne = j - i;
                    int lcount = freqMinusOne * (freqMinusOne+1) / 2;
                    for (int t=0; t<lcount; t++) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(arr[i]);
                        pair.add(arr[j]);
                        resList.add(pair);
                    }
                    break;
                }
                int ii = i+1;
                int jj = j-1;
                int ifreq = 1;
                int jfreq = 1;
                // frequency of arr[i]
                while (ii < j && arr[ii] == arr[i]) {
                    ifreq++;
                    ii++;
                }
                // frequency of arr[j]
                while (i < jj && arr[jj] == arr[j]) {
                    jfreq++;
                    jj--;
                }

                for (int t=0; t<ifreq; t++)
                    for (int u=0; u<jfreq; u++) {
                        List<Integer> pair = new ArrayList<>();
                        pair.add(arr[i]);
                        pair.add(arr[j]);
                        resList.add(pair);
                    }

                i = ii;
                j = jj;

            }
            else if(lsum < sum) {
                i++;
            }
            else {
               j--;
            }
        }
        return resList;
    }
}
