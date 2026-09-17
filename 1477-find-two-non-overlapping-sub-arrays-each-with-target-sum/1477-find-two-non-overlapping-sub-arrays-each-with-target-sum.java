class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;

        // best[i] = minimum length of a valid subarray
        // completely inside arr[0...i]
        int[] best = new int[n];

        int INF = n + 1;
        for (int i = 0; i < n; i++) {
            best[i] = INF;
        }

        int left = 0;
        int sum = 0;
        int answer = INF;
        int minLength = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // Shrink window while sum is too large
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // Found a subarray with sum = target
            if (sum == target) {
                int length = right - left + 1;

                // Check if there was a valid subarray
                // completely before this one
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, length + best[left - 1]);
                }

                // Keep the shortest valid subarray seen so far
                minLength = Math.min(minLength, length);
            }

            // Carry forward the best answer
            // up to this index
            best[right] = minLength;
        }

        return answer == INF ? -1 : answer;
    }
}