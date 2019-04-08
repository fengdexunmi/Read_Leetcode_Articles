/**
 * 最大回文子串
 * LongestPalindromicSubstring
 */
public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (null == s || s.length() <= 0) {
            return "";
        }

        int endIndex = 0;
        int substringLength = 0;
        int length = s.length();
        int tempLen = 0;
        int j = length - 1;
        for (int i = 0; i < s.length(); i++) {
            for (; j >= 0; j--) {
                if (s.charAt(i) == s.charAt(j)) {
                    System.out.println("1 i = " + i + ", j = " + j);
                    substringLength++;
                    break;
                }
                if (substringLength > tempLen) {
                    System.out.println("2 tempLen = " + tempLen);
                    endIndex = i;
                    tempLen = substringLength;
                    substringLength = 0;
                }
            }
        }

        if (substringLength == length) {
            // all s is palindromic
            return s;
        }

        System.out.println("endIndex = " + endIndex
         + ", sublen = " + substringLength
          + ", preLen = " + tempLen);
        return s.substring(endIndex + 1 - tempLen, endIndex + 1);
    }

    public static void main(String[] args) {
        String s = "cababad";
        String result = longestPalindrome(s);
        System.out.println("result = " + result);
    }
}