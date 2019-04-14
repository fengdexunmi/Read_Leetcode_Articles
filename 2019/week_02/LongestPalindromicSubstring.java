/**
 * Longest Palindromic Substring
 */
public class LongestPalindromicSubstring {
    /**
     * 动态规划
     * 
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 0)
            return "";
        
        int len = s.length();
        boolean[][] lps = new boolean[len][len];
        int maxPalindromeLen = 0;
        int startPalindrome = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (i - j <= 2) {
                    lps[j][i] = s.charAt(j) == s.charAt(i);
                } else {
                    lps[j][i] = lps[j+1][i-1] && s.charAt(j) == s.charAt(i);
                }

                if (lps[j][i] && i - j + 1 > maxPalindromeLen) {
                    maxPalindromeLen = i - j + 1;
                    startPalindrome = j;
                }
            }
        }

        return s.substring(startPalindrome, startPalindrome + maxPalindromeLen);
    }

    /**
     * Manacher算法
     * 
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() <= 0)
            return "";
        
        StringBuilder sBuilder = new StringBuilder();
        sBuilder.append("$#");
        for (int k = 0; k < s.length(); k++) {
            sBuilder.append(s.charAt(k)).append('#');
        }
        sBuilder.append("^");
        String newS = sBuilder.toString();
        int[] p = new int[newS.length()];
        int id = 0;
        int mx = 0;
        // 原串的长度
        int originLen = 0;
        // 原串的中心位置
        int originCenter = 0;
        for (int i = 1; i < newS.length() - 1; i++) {
            p[i] = mx - i > 0 ? Math.min(p[2 * id - i], mx - i) : 1;
            while (newS.charAt(i + p[i]) == newS.charAt(i - p[i])) {
                p[i]++;
            }
            if (mx < i + p[i]) {
                mx = i + p[i];
                id = i;
            }
            if (originLen < p[i]) {
                originLen = p[i];
                originCenter = i;
            }
        }

        return s.substring((originCenter - originLen) / 2, (originCenter - originLen) / 2 + originLen - 1);
    }
}