class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {

            // If i is a losing position,
            // we can move to a winning position
            // by adding any perfect square.
            if (!dp[i]) {

                for (int j = 1; i + j * j <= n; j++) {
                    dp[i + j * j] = true;
                }

                // n can be reached from a losing position,
                // so the first player can win.
                if (dp[n]) {
                    return true;
                }
            }
        }

        return false;
    }
}