

        class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int leftSum = 0, rightSum = 0;
        int leftQ = 0, rightQ = 0;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);

            if (i < n / 2) {
                if (ch == '?') {
                    leftQ++;
                } else {
                    leftSum += ch - '0';
                }
            } else {
                if (ch == '?') {
                    rightQ++;
                } else {
                    rightSum += ch - '0';
                }
            }
        }

        int totalQ = leftQ + rightQ;

        // Alice gets the last move if number of '?' is odd
        if (totalQ % 2 == 1) {
            return true;
        }

        // Check whether Bob can perfectly balance the game
        return 2 * (rightSum - leftSum) != 9 * (leftQ - rightQ);
    }
}
    
