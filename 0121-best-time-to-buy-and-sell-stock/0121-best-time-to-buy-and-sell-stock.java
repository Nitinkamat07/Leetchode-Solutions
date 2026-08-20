

        class Solution {
    public int maxProfit(int[] prices) {

        // Assume the first day's price is the
        // cheapest price we have seen so far
        int minPrice = prices[0];

        // Stores the maximum profit found so far
        int maxProfit = 0;

        // Start from the second day
        for (int i = 1; i < prices.length; i++) {

            // If today's price is cheaper than
            // the minimum price seen before,
            // update the buying price
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }

            // Otherwise, calculate the profit
            // if we buy at minPrice and sell today
            else {
                int profit = prices[i] - minPrice;

                // Update maxProfit if today's profit
                // is better than the previous maximum
                if (profit > maxProfit) {
                    maxProfit = profit;
                }
            }
        }

        // Return the maximum profit
        // If no profit is possible, it remains 0
        return maxProfit;
    }
}
    
