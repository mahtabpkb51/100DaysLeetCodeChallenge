class Solution {
    public boolean sumGame(String num) {
        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        int n = num.length();

        for (int i = 0; i < n / 2; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                leftQ++;
            } else {
                leftSum += ch - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char ch = num.charAt(i);

            if (ch == '?') {
                rightQ++;
            } else {
                rightSum += ch - '0';
            }
        }

        // Odd number of '?' -> Alice wins
        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        // Check whether Bob can make both sums equal
        return 2 * (leftSum - rightSum) != 9 * (rightQ - leftQ);
    }
}