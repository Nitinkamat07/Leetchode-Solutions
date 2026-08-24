

        class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // Convert stones into prefix sums
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }

        // Initially, if only the last two stones are considered
        int best = stones[n - 1];

        // Work backwards
        for (int i = n - 2; i >= 1; i--) {
            best = Math.max(best, stones[i] - best);
        }

        return best;
    }
}
    
