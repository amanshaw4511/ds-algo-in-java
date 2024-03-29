public  class Sol{

    public static List<String> solvethis(String str) {
        List<String> list = new ArrayList<>();
        solve(str, 0, "", list);
        // sort by length, then alpahabets
        list.sort( (x,y) -> { 
                if (x.length() == y.length())
                    return x.compareTo(y);
                return x.length() - y.length() ;
        });
        return list;

    }

    public static void solve(String str, int index, String out, List<String> list) {
        // base condition
        if (index == str.length()) {
            list.add(out); 
            return;
        }
        

        // lena hai ya nahi lena hai ?
        solve(str, index+1, out, list);        // nahi lena hai
        solve(str, index+1, out + str.charAt(index), list); // lena hai
    }

    public static void solveList(int[] nums, int index, List<Integer> tempResult, List<List<Integer>> results) {
        list.add(new ArrayList<Integer>(tempResult));
        for (int i=index; i<nums.length; i++) {
            tempResult.add(nums[i]);
            solveList(nums, i+1, tempResult, results);
            tempResult.remove(tempResult.length() -1);

        }
    }
}
