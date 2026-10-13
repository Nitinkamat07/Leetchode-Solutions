class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);

        long sum = 0;
        int n = nums.length;

        // Sort the squares conceptually by their values.
        int[] squares = new int[n];

        for (int i = 0; i < n; i++) {
            squares[i] = nums[i] * nums[i];
        }

        Arrays.sort(squares);

        for (int i = 0; i < n; i++) {
            long square = squares[i];

            if (i < n / 2) {
                sum -= square;
            } else {
                sum += square;
            }
        }

        return sum;
    }
}