
    
        class Solution {
    public boolean checkStrings(String s1, String s2) {
        int[] count = new int[52];

        for (int i = 0; i < s1.length(); i++) {
            int c1 = s1.charAt(i) - 'a';
            int c2 = s2.charAt(i) - 'a';

            if (i % 2 == 0) {
                count[c1]++;
                count[c2]--;
            } else {
                count[c1 + 26]++;
                count[c2 + 26]--;
            }
        }

        for (int x : count) {
            if (x != 0) {
                return false;
            }
        }

        return true;
    }
}
    
