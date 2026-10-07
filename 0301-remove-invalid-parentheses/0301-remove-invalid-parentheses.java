import java.util.*;

class Solution {
    Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {
        int leftRemove = 0;
        int rightRemove = 0;

        // Find the minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                leftRemove++;
            } 
            else if (ch == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } 
                else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, leftRemove, rightRemove, 0, "");

        return new ArrayList<>(result);
    }

    private void dfs(String s, int index,
                     int leftRemove, int rightRemove,
                     int balance, String current) {

        // Reached end
        if (index == s.length()) {
            if (leftRemove == 0 &&
                rightRemove == 0 &&
                balance == 0) {

                result.add(current);
            }
            return;
        }

        char ch = s.charAt(index);

        // Option 1: Remove current parenthesis
        if (ch == '(' && leftRemove > 0) {
            dfs(s, index + 1,
                leftRemove - 1, rightRemove,
                balance, current);
        }

        if (ch == ')' && rightRemove > 0) {
            dfs(s, index + 1,
                leftRemove, rightRemove - 1,
                balance, current);
        }

        // Option 2: Keep current character
        if (ch != '(' && ch != ')') {
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance, current + ch);
        }
        else if (ch == '(') {
            dfs(s, index + 1,
                leftRemove, rightRemove,
                balance + 1, current + ch);
        }
        else {
            // We cannot have more ')' than '('
            if (balance > 0) {
                dfs(s, index + 1,
                    leftRemove, rightRemove,
                    balance - 1, current + ch);
            }
        }
    }
}