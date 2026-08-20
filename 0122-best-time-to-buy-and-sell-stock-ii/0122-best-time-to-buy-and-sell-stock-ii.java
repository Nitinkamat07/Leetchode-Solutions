

        class Solution {
    public int maxProfit(int[] prices) {

        // Stores the total profit we can make
        int profit = 0;

        // Start from the second day
        for (int i = 1; i < prices.length; i++) {

            // If today's price is greater than yesterday's price,
            // we can make a profit by buying yesterday
            // and selling today.
            if (prices[i] > prices[i - 1]) {

                // Add today's profit to the total profit
                profit += prices[i] - prices[i - 1];
            }
        }

        // Return the maximum total profit
        return profit;
    }
}
    
