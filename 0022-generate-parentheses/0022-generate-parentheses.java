class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack("", 0, 0, n, result);

        return result;
    }

    private void backtrack(
        String current,
        int open,
        int close,
        int n,
        List<String> result
    ) {
        // We have used all parentheses
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        // We can still add an opening parenthesis
        if (open < n) {
            backtrack(current + "(", open + 1, close, n, result);
        }

        // We can add a closing parenthesis only if
        // there is an unmatched opening parenthesis
        if (close < open) {
            backtrack(current + ")", open, close + 1, n, result);
        }
    }
}