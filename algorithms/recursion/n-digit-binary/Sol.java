
class Sol{

    public static void solvethis(int n) {
        solve(n, "", 0);
    }

    public static void solve(int n, String out, int ones) {
        // base condition
        if (n == 0){
            System.out.println(out);
            return;
        }

        // do i have to add zero or not
        if (ones > n/2) 
            solve(n-1, out+"0", ones);
        solve(n-1, out+"1", ones+1);
    }
}
