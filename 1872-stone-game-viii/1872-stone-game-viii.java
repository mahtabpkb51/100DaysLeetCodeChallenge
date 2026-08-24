class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
 
        int dp = sum;

        for (int i = n - 2; i >= 1; i--) {
            sum -= stones[i + 1];
            dp = Math.max(dp, sum - dp);
        }

        return dp;
    }
}