
void subSequence(String s, String out, int pos, List<String> o) {
    if (pos == s.length()) {
        o.add(out);
     return;
    }
    subSequence(s, out + s.charAt(pos) , pos+1, o);
    subSequence(s, out , pos+1, o);
}



void subString(String s, String out, int pos , List<String> o) {
    if (pos == s.length()) {
        o.add(out);
        return;
    }
        subString(s, out + s.charAt(pos), pos+1, o);
        subString(s, out , pos+1,  o);

}

void subString1(String s) {
    for (int i=0; i<s.length(); i++) {
        for (int j=i+1; j<s.length()+1; j++) {
            System.out.println(s.substring(i,j));
        }
    }
}


int commonSubsequence(String s1, String s2, int i, int j) {
    if (i == s1.length() || j == s2.length()) return 0;
    
    int l1 = 0;
    int l2, l3;
    if (s1.charAt(i) == s2.charAt(j)) {
        l1 = 1 + commonSubsequence(s1, s2, i+1, j+1);
    }
    l2 = commonSubsequence(s1, s2, i, j+1);
    l3 = commonSubsequence(s1, s2, i+1, j);
    return Math.max(l1, Math.max(l2, l3));
}

int commonSubstring(String s1, String s2, int i, int j, int ans) {
    if (i == s1.length() || j == s2.length()) return ans;

    int l1 = 0;
    int l2 = 0;
    int l3 = 0;
    if (s1.charAt(i) == s2.charAt(j)) {
       l1 = commonSubstring(s1, s2, i+1, j+1, ans+1);
    }
    l2 = commonSubstring(s1, s2, i+1, j, 0);
    l3 = commonSubstring(s1, s2, i, j+1, 0);
    return List.of(l1,l2,l3,ans).stream()
        .reduce(0, (a,b) -> Math.max(a,b));
}

