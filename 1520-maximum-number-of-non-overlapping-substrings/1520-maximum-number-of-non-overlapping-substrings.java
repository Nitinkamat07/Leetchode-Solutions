import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, -1);

        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';

            if (first[c] == -1) {
                first[c] = i;
            }

            last[c] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Try to create a valid interval from every character
        for (int c = 0; c < 26; c++) {

            if (first[c] == -1)
                continue;

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            for (int i = l; i <= r; i++) {

                int current = s.charAt(i) - 'a';

                // This character appeared before l,
                // so substring cannot contain all occurrences.
                if (first[current] < l) {
                    valid = false;
                    break;
                }

                // Expand the interval to include
                // all occurrences of this character.
                r = Math.max(r, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending position
        intervals.sort((a, b) -> a[1] - b[1]);

        List<String> answer = new ArrayList<>();

        int end = -1;

        // Greedily choose intervals with earliest ending position
        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > end) {
                answer.add(s.substring(l, r + 1));
                end = r;
            }
        }

        return answer;
    }
}