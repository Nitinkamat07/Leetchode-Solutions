import java.math.BigInteger;

class Solution {
    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
            half[i] = freq[i] / 2;
            halfLen += half[i];
        }

        // Compute total number of distinct permutations
        BigInteger ways = BigInteger.ONE;
        int rem = 0;
        for (int i = 0; i < 26; i++) {
            if (half[i] > 0) {
                ways = ways.multiply(comb(rem + half[i], half[i]));
                rem += half[i];
            }
        }

        if (ways.compareTo(BigInteger.valueOf(k)) < 0) {
            return "";
        }

        StringBuilder first = new StringBuilder();
        rem = halfLen;
        BigInteger K = BigInteger.valueOf(k);

        while (rem > 0) {
            for (int i = 0; i < 26; i++) {
                if (half[i] == 0) continue;

                BigInteger nextWays = ways
                        .multiply(BigInteger.valueOf(half[i]))
                        .divide(BigInteger.valueOf(rem));

                if (nextWays.compareTo(K) >= 0) {
                    first.append((char) ('a' + i));
                    half[i]--;
                    ways = nextWays;
                    rem--;
                    break;
                } else {
                    K = K.subtract(nextWays);
                }
            }
        }

        String left = first.toString();
        String right = new StringBuilder(left).reverse().toString();

        if (mid == 0)
            return left + right;
        return left + mid + right;
    }

    private BigInteger comb(int n, int r) {
        if (r > n - r) r = n - r;
        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= r; i++) {
            res = res.multiply(BigInteger.valueOf(n - r + i));
            res = res.divide(BigInteger.valueOf(i));
        }
        return res;
    }
}