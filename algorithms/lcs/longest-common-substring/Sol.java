
class Sol{

    public static int solve(String s1, String s2) {
        return solveRec(s1, s2, s1.length(), s2.length(), 0);
    }


    private static int solveRec(String s1, String s2, int m, int n, int ans) {
        // base condition 
        if (m == 0 || n == 0)
            return ans;

        int ret = 0;
        if (s1.charAt(m-1) == s2.charAt(n-1))
            ret =  solveRec(s1, s2, m-1, n-1, ans +1);

        int max = Integer.max( solveRec(s1, s2, m-1, n, 0),
                            solveRec(s1, s2, m, n-1, 0));
        return Integer.max( ret, Integer.max(max, ans) );
    }
}


/// no completed
