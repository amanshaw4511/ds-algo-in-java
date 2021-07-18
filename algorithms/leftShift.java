class Result {

    /*
     * Complete the 'rotLeft' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER d
     */

    public static List<Integer> rotLeft(List<Integer> a, int d) {
        int n = a.size();
        int r= d % n;
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<n; i++) {
            int newIndex = (i+r >= n) ? i - (n-r) : i+r;
            ans.add(a.get(newIndex));
        }
        return ans;
    }

}
