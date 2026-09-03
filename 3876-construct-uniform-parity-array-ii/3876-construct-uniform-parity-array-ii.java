class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        // Sabse chhota odd number
        for (int num : nums1) {
            if (num % 2 == 1) {
                minOdd = Math.min(minOdd, num);
            }
        }

        // Agar odd number hai hi nahi,
        // to saare numbers even hain
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }

        // Koi even number minOdd se chhota hua
        // to valid uniform parity array nahi ban sakta
        for (int num : nums1) {
            if (num % 2 == 0 && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}