
class Sol{
    

    public static void solvethis(int n){
        solve(n*2, 0, "");
    }

    public static void solve(int n, int count, String out){
    // base condition
    if (n == 0) {
        System.out.println(out);
        return ;
    }

    // logic
        if (count < n) 
            solve(n-1, count+1, out+"(");
        if (count > 0)
            solve(n-1, count-1, out+")");
    }

    
}
