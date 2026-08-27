class Solution {


    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try to match target from left to right.
        // matched = number of characters matched exactly.
        int matched = 0;

        while (matched < n) {
            int idx = target.charAt(matched) - 'a';

            if (count[idx] == 0) {
                break;
            }

            count[idx]--;
            matched++;
        }

        /*
         * Now count[] contains characters remaining after matching
         * target[0 ... matched-1].
         *
         * We try to make the answer larger at the rightmost possible position.
         */
        for (int i = matched; i >= 0; i--) {

            // If i < matched, restore target[i] because we are
            // backtracking from the matched prefix.
            if (i < matched) {
                count[target.charAt(i) - 'a']++;
            }

            // Find the smallest available character > target[i]
            if (i < n) {
                int targetChar = target.charAt(i) - 'a';

                for (int c = targetChar + 1; c < 26; c++) {
                    if (count[c] > 0) {
                        StringBuilder ans = new StringBuilder();

                        // Prefix remains equal to target
                        ans.append(target, 0, i);

                        // Put the smallest character greater than target[i]
                        ans.append((char) ('a' + c));
                        count[c]--;

                        // Append all remaining characters in sorted order
                        for (int ch = 0; ch < 26; ch++) {
                            while (count[ch]-- > 0) {
                                ans.append((char) ('a' + ch));
                            }
                        }

                        return ans.toString();
                    }
                }
            }
        }

        return "";
    }
}
    
