class Solution {


    public String shortestBeautifulSubstring(String s, int k) {
        int left = 0;
        int ones = 0;
        String answer = "";

        for (int right = 0; right < s.length(); right++) {
            if (s.charAt(right) == '1') {
                ones++;
            }

            // Too many 1s, shrink the window
            while (ones > k) {
                if (s.charAt(left) == '1') {
                    ones--;
                }
                left++;
            }

            // Exactly k 1s: remove unnecessary leading 0s
            while (ones == k && s.charAt(left) == '0') {
                left++;
            }

            // Current window is the shortest window ending at right
            // with exactly k ones
            if (ones == k) {
                String candidate = s.substring(left, right + 1);

                if (answer.isEmpty()
                        || candidate.length() < answer.length()
                        || (candidate.length() == answer.length()
                        && candidate.compareTo(answer) < 0)) {
                    answer = candidate;
                }
            }
        }

        return answer;
    }
}
    
