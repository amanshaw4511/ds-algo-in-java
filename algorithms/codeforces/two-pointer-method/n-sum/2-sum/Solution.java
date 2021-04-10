import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static List<Pair> solve(int[] arr, int sum) {
        int n = arr.length;
        int i = 0;
        int j = n -1;
        int count = 0;
        List<Pair> resList = new ArrayList<Pair>();

        while (i < j) {
            int lsum = arr[i] + arr[j];
            if (lsum == sum) {
                // if arr[i] and arr[j] are same
                // then 
                if (arr[i] == arr[j]) {
                    int freqMinusOne = j - i ;
                    // sum of n number
                    int lcount =  freqMinusOne * (freqMinusOne+1) / 2;
                    for (int t=0; t<lcount; t++) 
                        resList.add(new Pair(arr[i], arr[j]));
                    count += lcount; 
                    break;
                }

                int ii = i + 1;
                int jj = j - 1;
                int ifreq = 1;
                int jfreq = 1;
                // find frequency of arr[i]
                while (ii < j && arr[ii] == arr[i]) {
                    ifreq++;
                    ii++;
                }
                // find frequency of arr[j]
                while (i < jj  && arr[jj] == arr[j]) {
                    jfreq++;
                    jj--;
                }

                for (int t=0; t<ifreq; t++)
                    for (int u=0; u<jfreq; u++)
                        resList.add(new Pair(arr[i], arr[j]));
                System.out.println(i + " " + j + ":" + arr[i] + " " + arr[j]);
                count += ifreq * jfreq;
                j = jj;
                i = ii;
            }
            else if(lsum < sum) {
                i++;
            }
            else {
                j--;
            }
        }
        if (count != resList.size())
            System.out.println("something is wrong");
        return resList;
    }
}


class Pair {
    Integer key;
    Integer value;

    public Pair(Integer key, Integer value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + key + "," + value + ")";
    }

}
