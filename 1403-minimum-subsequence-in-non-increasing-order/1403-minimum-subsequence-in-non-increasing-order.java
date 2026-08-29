class Solution {
    public List<Integer> minSubsequence(int[] nums) {
        Arrays.sort(nums);

        List<Integer> ans = new ArrayList<>();

        int total = 0;
        for (int num : nums) {
            total += num;
        }

        int sum = 0;

        // Largest elements se start karo
        for (int i = nums.length - 1; i >= 0; i--) {
            sum += nums[i];
            ans.add(nums[i]);

            // Selected sum > remaining sum
            if (sum > total - sum) {
                break;
            }
        }

        return ans;
    }
}