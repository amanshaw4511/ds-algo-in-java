
class Solution {
    public static int solve(int[] arr) {
        int n = arr.length;
        int max = Integer.MIN_VALUE;
        int localsum = arr[0];
        for (int i=0; i<n; i++) {
            localsum += arr[i];
            max = Math.max(max, localsum + arr[i]);
        }
        return max;
    }

}
