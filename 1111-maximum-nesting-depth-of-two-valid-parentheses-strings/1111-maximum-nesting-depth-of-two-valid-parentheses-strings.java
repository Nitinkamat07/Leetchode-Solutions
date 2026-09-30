class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];

        int depth = 0;

        for (int i = 0; i < n; i++) {

            if (seq.charAt(i) == '(') {
                depth++;

                // Odd depth -> group 0
                // Even depth -> group 1
                ans[i] = depth % 2;
            } 
            else {
                // Closing bracket belongs to the
                // same group as its opening bracket
                ans[i] = depth % 2;

                depth--;
            }
        }

        return ans;
    }
}