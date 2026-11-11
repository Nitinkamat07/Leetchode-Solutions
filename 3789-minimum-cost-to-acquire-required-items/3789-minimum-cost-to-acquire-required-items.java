
class Solution {
    public long minimumCost(int cost1, int cost2, int costBoth, int need1, int need2) {

        // Option 1: Buy individual items only
        long costA = (long) need1 * cost1 + (long) need2 * cost2;

        // Option 2: Use type 3 items for overlapping requirements
        int common = Math.min(need1, need2);

        long costB = (long) common * costBoth
                   + (long) (need1 - common) * cost1
                   + (long) (need2 - common) * cost2;

        // Option 3: Buy type 3 items for both requirements
        long costC = (long) Math.max(need1, need2) * costBoth;

        return Math.min(costA, Math.min(costB, costC));
    }
}
