class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;
        int n = nums.length;
        boolean allZero = true;

        for (int x : nums) {
            xor ^= x;

            if (x != 0) {
                allZero = false;
            }
        }

        if (xor != 0) {
            return n;
        }

        if (allZero) {
            return 0;
        }

        return n - 1;
    }
}