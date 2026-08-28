class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Palindrome possible hai ya nahi
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        int halfLen = s.length() / 2;
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        StringBuilder prefix = new StringBuilder();

        // Left half ko lexicographically smallest possible build karo
        for (int pos = 0; pos < halfLen; pos++) {
            boolean found = false;

            for (int c = 0; c < 26; c++) {
                if (halfCount[c] == 0) {
                    continue;
                }

                halfCount[c]--;
                prefix.append((char) ('a' + c));

                if (canMakeGreater(prefix, halfCount, middle, target)) {
                    found = true;
                    break;
                }

                prefix.deleteCharAt(prefix.length() - 1);
                halfCount[c]++;
            }

            if (!found) {
                return "";
            }
        }

        String left = prefix.toString();
        StringBuilder ans = new StringBuilder(left);

        if (s.length() % 2 == 1) {
            ans.append(middle);
        }

        ans.append(new StringBuilder(left).reverse());

        String result = ans.toString();

        return result.compareTo(target) > 0 ? result : "";
    }

    private boolean canMakeGreater(
            StringBuilder prefix,
            int[] halfCount,
            char middle,
            String target) {

        StringBuilder left = new StringBuilder(prefix);

        // Remaining characters descending order me
        // taaki maximum possible palindrome check ho
        for (int i = 25; i >= 0; i--) {
            for (int j = 0; j < halfCount[i]; j++) {
                left.append((char) ('a' + i));
            }
        }

        StringBuilder palindrome = new StringBuilder(left);

        if (middle != 0) {
            palindrome.append(middle);
        }

        palindrome.append(new StringBuilder(left).reverse());

        return palindrome.toString().compareTo(target) > 0;
    }
}