import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Trie {
    Node root;

    Trie() {
        this.root = new Node();
    }

    public void add(String str) {
        int n = str.length();
        Node curr = this.root;
        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (!curr.hasChild(ch)) {
                curr.addChild(ch);
            }
            curr.getChild(ch).wordPrefix += 1;
            curr = curr.getChild(ch);
        }
        curr.wordEnd += 1;
    }

    // returns if word exist in dictionary
    public boolean contains(String str) {
        return this.find(str) != 0;
    }


    // returns no of words in dictionary
    public int find(String str) {
        int n = str.length();
        Node curr = this.root;
        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (!curr.hasChild(ch)) {
                return 0;
            }
            curr = curr.getChild(ch);
        }
        return curr.wordEnd;
    }

    

    public boolean remove(String str) {
        int n = str.length();
        Node curr = this.root;
        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (!curr.hasChild(ch)) {
                return false;
            }
            curr.getChild(ch).wordPrefix -= 1;
            curr = curr.getChild(ch);
        }
        if (!curr.isWordEnding()) {
            return false;
        } 
        curr.wordEnd -= 1;
        return true;
    }

    public int countSuffix(String str) {
        int n = str.length();
        int count = 0;
        Node curr = this.root;
        for (int i=0; i<n; i++) {
            char ch = str.charAt(i);
            if (!curr.hasChild(ch)) {
                return 0;
            }
            curr = curr.getChild(ch);
        }
        return curr.wordPrefix;
    }


    // may contains wildcard
    // autoComplete("a.c") -> abc  acc  abcd
    public List<String> autoComplete(String str, int count) { 
        List<String> l = new ArrayList<>();
        this.autoComplete(this.root, str, 0, "", count, l);
        return l;
    }

    public void autoComplete(Node curr, String str, int index, String out, int count, List<String> l) {
        int n = str.length();
        if (curr == null) {
            return;
        }
        if (l.size() == count) {
            return;
        }

        //
        if (index >= str.length()) {
            if (curr.isWordEnding()) {
                l.add(out);
            }
            // return;
        }

        char ch;
        if (index < str.length() && (ch=str.charAt(index)) != '.') {
            autoComplete(curr.getChild(ch), str, index+1, out+ch, count, l);
        } else {
            for (int i=0; i<26; i++) {
                ch = (char)('a' + i);
                autoComplete(curr.getChild(ch), str, index+1, out+ch, count, l);
            }
        }
        return;
    }


    /**
     * @param str  string to be searched (may contains wildcard)
     * @param count  max no. of words to be search
     * @return list of words
     */
    // return exactmatching words (support . wild card)
    // find("a.c")  -> abc,  adc, aac
    public List<String> find(String str, int count) {
        List<String> l = new ArrayList<>();
        this.autoComplete(this.root, str, 0, "", count, l);
        return l;
    }

    public void find(Node curr, String str, int index, String out, int count, List<String> l) {
        int n = str.length();
        if (curr == null) {
            return;
        }
        if (l.size() == count) {
            return;
        }

        if (index == str.length()) {
            if (curr.isWordEnding()) {
                l.add(out);
            }
            return;
        }

        char ch;
        if ((ch=str.charAt(index)) != '.') {
            find(curr.getChild(ch), str, index+1, out+ch, count, l);
        } else {
            for (int i=0; i<26; i++) {
                ch = (char)('a' + i);
                find(curr.getChild(ch), str, index+1, out+ch, count, l);
            }
        }
        return;
    }


    class Node {
        Node[] child;
        int wordEnd;
        int wordPrefix;

        Node() {
            this.child = new Node[26];
            this.wordEnd = 0;
            this.wordPrefix = 0;
        }

        public Node getChild(char ch) {
            return this.child[ch - 'a'];
        }

        public Node getChild(int childNo) {
            return this.child[childNo];
        }

        public void addChild(char ch) {
            this.child[ch - 'a'] = new Node();
        }


        public boolean hasChild(char ch) {
            return this.getChild(ch) != null;
        }

        public boolean isWordEnding() {
            return this.wordEnd > 0;
        }
    }
}
