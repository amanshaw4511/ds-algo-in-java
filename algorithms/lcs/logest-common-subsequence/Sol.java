
class Sol{

    public static int solve(String s1, String s2, char method) {
        int dp[][] = new int[s1.length()+1][s2.length()+1];
        for (int i=0; i<=s1.length(); i++)
            for (int j=0; j<=s2.length(); j++)
                dp[i][j] = -1;
        
        switch (method) {
            case 'r' : return solveRec(s1, s2, s1.length(), s2.length());
            case 'm' : return solveMem(s1, s2, s1.length(), s2.length(), dp);
            case 'd' : return solveDp(s1, s2, dp);

            default  : System.out.println("Invalid method");
                       return 1;
        }
    }

    // basic recursive solution
    public static int solveRec(String s1, String s2, int m, int n) {
        // base condition 
        if (m==0 || n==0)
            return 0;

        if (s1.charAt(m-1) == s2.charAt(n-1)) 
            return 1 + solveRec(s1, s2, m-1, n-1);
        else
            return Integer.max(solveRec(s1, s2, m-1, n), solveRec(s1, s2, m, n-1));
    }

    // memoization code
    public static int solveMem(String s1, String s2, int m, int n, int[][] dp) {
        if (m==0 || n==0) {
            dp[m][n] = 0;
            return dp[m][n];
        };
        if (dp[m][n] != -1)
            return dp[m][n];

        if (s1.charAt(m-1) == s2.charAt(n-1)) {
            dp[m][n] = 1 + solveMem(s1, s2, m-1, n-1, dp);
        } else {
            dp[m][n] = Integer.max( solveMem(s1, s2, m-1, n, dp),
                                    solveMem(s1, s2, m, n-1, dp));
        }
        return dp[m][n];
    }

    // top-down upproach
    public static int solveDp(String s1, String s2, int[][] dp) {

        for (int i=0; i<=s1.length(); i++) {
            for (int j=0; j<=s2.length(); j++) {
                // base condition
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }

                if (s1.charAt(i-1) == s2.charAt(j-1))
                    dp[i][j] = 1 + dp[i-1][j-1];
                else
                    dp[i][j] = Integer.max(dp[i][j-1], dp[i-1][j]);
            }
        }
        return dp[s1.length()][s2.length()];
    }

    // public static String solve(String s1, String s2, int i, int j) {
    //     // base condition 
    //     if (i == s1.length() || j == s2.length())
    //         return "";

    //     if (s1.charAt(i) == s2.charAt(j)) 
    //         return s1.charAt(i) + solve(s1, s2, i+1, j+1);
    //     else{
    //         String a = solve(s1, s2, i+1, j);
    //         String b = solve(s1, s2, i, j+1);
    //         return a.length() > b.length() ? a : b ;
        // }
    // }

}




