import java.util.*;

class Node {
    Node[] children = new Node[26];
    boolean isEndOfWord;

    Node() {
        isEndOfWord = false;
        for (int i = 0; i < 26; i++)
            children[i] = null;
    }
}

class Trie {
    Node root;
    public Trie(){
        root = new Node();
    }

    public void insert(String str) {
        Node next = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = c - 'a';

            if (next.children[index] == null) {
                next.children[index] = new Node();
            }
            next = next.children[index];

        }

        next.isEndOfWord = true;
    }

    public int search(String str) {
        Node next = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int index = c - 'a';
            if (next.children[index] == null) {
                return 0;
            }
            next = next.children[index];
        }
        return (next != null && next.isEndOfWord) ? 1 : 0;
    }
}

class SolutionTrie {
    public static void main(String[] args) {
        // code

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            Trie trie = new Trie();
            
            int n = sc.nextInt();
            while (n-- > 0) {
                String str = sc.next();
                trie.insert(str);
            }

            String str1 = sc.next();

            System.out.println(trie.search(str1));
        }
        sc.close();

    }
}
