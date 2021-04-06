
class Sol{

    public static void solvethis(int n, int k) {
        solve(IntStream.range(0,n).boxed().collect(Collectors.toList()), 0, k);
    }
    
    public static void solve(List<Integer> peoples, int start, int k) {
        // base condition  
        if (peoples.size() == 1) {
            System.out.println(peoples.get(0));
            return;
        }

        int index = (start + k) % peoples.size();
        peoples.remove(index); 
        solve(peoples, index, k);
    }
} 
