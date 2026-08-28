class Solution {
    public List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            for (int j = Math.max(0, i - k);
                 j <= Math.min(nums.length - 1, i + k);
                 j++) {

                if (nums[j] == key) {
                    ans.add(i);
                    break;
                }
            }
        }

        return ans;
    }
}