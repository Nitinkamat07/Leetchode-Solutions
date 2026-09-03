class Solution {


    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];

        for (int num : nums1) {
            min = Math.min(min, num);
        }

        // Minimum is odd -> always possible
        if (min % 2 == 1) {
            return true;
        }

        // Minimum is even -> every number must be even
        for (int num : nums1) {
            if (num % 2 == 1) {
                return false;
            }
        }

        return true;
    }
}
    
