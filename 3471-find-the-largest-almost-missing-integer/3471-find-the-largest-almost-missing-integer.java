class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int ans = -1;

        for (int i = 0; i < n; i++) {
            int count = 0;

            // Check how many subarrays of length k
            // contain nums[i]
            for (int start = 0; start <= n - k; start++) {
                for (int j = start; j < start + k; j++) {
                    if (nums[j] == nums[i]) {
                        count++;
                        break;
                    }
                }
            }

            // Appears in exactly one subarray
            if (count == 1) {
                ans = Math.max(ans, nums[i]);
            }
        }

        return ans;
    }
}