/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private int answer = 0;

    public int averageOfSubtree(TreeNode root) {
        dfs(root);
        return answer;
    }

    // returns {sum, count}
    private int[] dfs(TreeNode node) {

        if (node == null) {
            return new int[]{0, 0};
        }

        // Process left and right subtrees first
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);

        // Calculate sum and count of current subtree
        int sum = node.val + left[0] + right[0];
        int count = 1 + left[1] + right[1];

        // Integer division automatically rounds down
        int average = sum / count;

        if (average == node.val) {
            answer++;
        }

        return new int[]{sum, count};
    }
}