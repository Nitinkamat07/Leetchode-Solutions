class Solution {
    public long countCommas(long n){
        long ans = 0;

        // First number that contains a comma:
        // 1,000
        long threshold = 1000;

        while (threshold <= n) {
            ans += n - threshold + 1;

            // Move to the next comma position:
            // 1,000 -> 1,000,000 -> 1,000,000,000 ...
            threshold *= 1000;
        }

        return ans;
    }
}
