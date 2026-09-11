class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        // Count occurrences of every digit
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // First digit: 1-9
        for (int a = 1; a <= 9; a++) {
            if (freq[a] == 0) continue;

            freq[a]--;

            // Second digit: 0-9
            for (int b = 0; b <= 9; b++) {
                if (freq[b] == 0) continue;

                freq[b]--;

                // Last digit must be even
                for (int c = 0; c <= 8; c += 2) {
                    if (freq[c] > 0) {
                        count++;
                    }
                }

                freq[b]++;
            }

            freq[a]++;
        }

        return count;
    }
}