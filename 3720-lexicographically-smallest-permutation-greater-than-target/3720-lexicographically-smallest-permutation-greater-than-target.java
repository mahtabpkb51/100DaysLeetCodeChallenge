class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int n = s.length();
        char[] ans = new char[n];

        int i = 0;

        // Target ke equal prefix ko match karo
        while (i < n && freq[target.charAt(i) - 'a'] > 0) {
            char c = target.charAt(i);
            ans[i] = c;
            freq[c - 'a']--;
            i++;
        }

        // Current position par target se bada character try karo
        if (i < n) {
            int greater = target.charAt(i) - 'a' + 1;

            while (greater < 26 && freq[greater] == 0) {
                greater++;
            }

            if (greater < 26) {
                ans[i] = (char) ('a' + greater);
                freq[greater]--;

                return build(ans, i + 1, freq);
            }
        }

        // Backtrack karke kisi previous position ko bada karo
        for (i = i - 1; i >= 0; i--) {
            freq[ans[i] - 'a']++;

            int greater = target.charAt(i) - 'a' + 1;

            while (greater < 26 && freq[greater] == 0) {
                greater++;
            }

            if (greater < 26) {
                ans[i] = (char) ('a' + greater);
                freq[greater]--;

                return build(ans, i + 1, freq);
            }
        }

        return "";
    }

    private String build(char[] ans, int start, int[] freq) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < start; i++) {
            sb.append(ans[i]);
        }

        // Remaining characters sorted order me
        for (int c = 0; c < 26; c++) {
            while (freq[c] > 0) {
                sb.append((char) ('a' + c));
                freq[c]--;
            }
        }

        return sb.toString();
    }
}