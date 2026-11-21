class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        // Build the original tree
        List<Integer>[] children = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        // Store the updated parent for each node
        int[] newParent = parent.clone();

        // Track the nearest ancestor with each character
        int[] last = new int[26];
        Arrays.fill(last, -1);

        // Find new parents using DFS on the original tree
        dfsUpdate(0, children, s, last, newParent);

        // Build the modified tree
        List<Integer>[] newChildren = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            newChildren[newParent[i]].add(i);
        }

        // Calculate subtree sizes
        int[] answer = new int[n];
        dfsSize(0, newChildren, answer);

        return answer;
    }

    private void dfsUpdate(int node, List<Integer>[] children, String s,
                          int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';

        // Save the previous closest ancestor with this character
        int previous = last[ch];

        // If one exists, update the parent
        if (previous != -1) {
            newParent[node] = previous;
        }

        // Current node becomes the nearest ancestor of this character
        last[ch] = node;

        // Traverse the original tree
        for (int child : children[node]) {
            dfsUpdate(child, children, s, last, newParent);
        }

        // Backtrack: restore the previous ancestor
        last[ch] = previous;
    }

    private int dfsSize(int node, List<Integer>[] children, int[] answer) {
        int size = 1;

        for (int child : children[node]) {
            size += dfsSize(child, children, answer);
        }

        answer[node] = size;
        return size;
    }
}