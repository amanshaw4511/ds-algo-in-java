import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

class LongestDublicateSubstring {
    int mod = 10000007;

    class Node {
        Node[] child;
        boolean isWordEnding;

        Node() {
            this.child = new Node[26];
        }
    }

    Node root;

    // trie + bs
    public String ss(String str) {
        int n = str.length();
        Node curr = this.root;
        int l = 0;
        int r = n - 1;
        int ans = 0;
        int ansi = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (((r - l) / 2) % 2 != 0) {
                mid += 1;
            }
            Node root = new Node();
            boolean res = false;
            int i = 0;
            for (; i < n - mid + 1; i++) {

                res = this.add(str.substring(i, i + mid), root);
                if (res) {
                    break;
                }
            }

            if (res) {
                ans = mid;
                ansi = i;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        if (ansi == -1)
            return "";
        return str.substring(ansi, ansi + ans);
    }

    public boolean add(String str, Node root) {
        int n = str.length();
        Node curr = root;
        for (int i = 0; i < n; i++) {
            int ch = str.charAt(i) - 'a';
            if (curr.child[ch] == null) {
                curr.child[ch] = new Node();
            }
            curr = curr.child[ch];
        }
        boolean res = curr.isWordEnding;
        curr.isWordEnding = true;
        return res;
    }

    public String sustr(String str) {
        int n = str.length();
        int l = 0;
        int r = n - 1;
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; i++) {
            power[i] = power[i - 1] * 26 % mod;
        }

        String ans = "";
        while (l <= r) {
            int mid = l + (r - l) / 2;
            System.out.println(mid);
            int i = 0;
            String matchedStr = this.matched(str, mid, power);
            if (!matchedStr.isEmpty()) {
                ans = matchedStr;
                l = mid + 1;
            } else {
                r = mid - 1;
            }

        }
        return ans;
    }

    public String matched(String str, int size, int[] power) {
        int n = str.length();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int hash = 0;
        int i = 0;
        for (; i < size; i++) {
            int ch = str.charAt(i);
            hash = (hash * 26 + ch) % mod;
        }
        // put to list
        List<Integer> list = map.getOrDefault(hash, new ArrayList<Integer>());
        list.add(0);

        map.put(hash, list);
        for (; i < n; i++) {
            int ch = str.charAt(i);
            int startIndex = i - size + 1;
            hash = (hash - power[size - 1] * str.charAt(startIndex - 1)) % mod;
            hash = (hash * 26 + ch) % mod;

            // if hash matched
            if (map.containsKey(hash)) {
                String currString = str.substring(startIndex, i + 1);
                list = map.get(hash);
                // if substring matched
                if (list.stream().anyMatch(s -> str.substring(s, s + size).equals(currString))) {
                    // update ans and return true
                    // System.out.println((i-size + 1) + " " + (i+1));
                    return currString;
                }
            }
            
            // put hash
            list = map.getOrDefault(hash, new ArrayList<Integer>());
            list.add(i-size+1);
            map.put(hash, list);
        }
        return "";
    }

    public String lcsSolve(String str) {
        return lcs(str, str, 0, 0, "");
    }

    public String lcs(String s1, String s2, int index1, int index2, String out) {
        int n1 = s1.length();
        int n2 = s2.length();
        if (index1 == n1 || index2 == n2) {
            return out;
        }
        char ch1 = s1.charAt(index1);
        char ch2 = s2.charAt(index2);
        if (ch1 == ch2 && index1 != index2) {
            return lcs(s1, s2, index1+1, index2+1, out + ch1);
        }

        String ans1 = lcs(s1, s2, index1, index2+1, "");
        String ans2 = lcs(s1, s2, index1+1, index2, "");
        return (ans1.length() > ans2.length()) ? ans1 : ans2;
    }

}
