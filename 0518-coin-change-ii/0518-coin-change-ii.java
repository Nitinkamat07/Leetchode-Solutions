

        class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];

        dp[0] = 1;

        for (int coin : coins) {
            for (int currentAmount = coin; currentAmount <= amount; currentAmount++) {
                dp[currentAmount] += dp[currentAmount - coin];
            }
        }

        return dp[amount];
    }
}
    
