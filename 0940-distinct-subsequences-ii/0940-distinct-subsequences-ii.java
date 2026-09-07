class Solution {
    public int distinctSubseqII(String s) {


        int MOD = 1_000_000_007;
        
        // last[c] = number of distinct subsequences
        // that were created when character c was processed last
        long[] last = new long[26];

        long total = 0;

        for (char ch : s.toCharArray()) {
            int c = ch - 'a';

            // Every existing subsequence can either:
            // 1. stay as it is
            // 2. append current character
            //
            // Also, the character itself creates one new subsequence.
            long newSubsequences = (total + 1) % MOD;

            // If this character appeared before, those subsequences
            // were already counted when this character appeared last time.
            total = (total + newSubsequences - last[c] + MOD) % MOD;

            // Remember the contribution produced by this occurrence.
            last[c] = newSubsequences;
        }

        return (int) total;
    }
}
    
