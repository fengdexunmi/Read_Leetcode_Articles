import java.util.HashMap;

/**
 * LongestSubStringWithoutRepeating
 * 
 * Given a string, find the length of the longest substring without repeating
 * characters.
 */
public class LongestSubStringWithoutRepeating {

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }

        return ans;
    }

    public static String longestSubstring(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int end = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            if (map.containsKey(s.charAt(j))) {
                if (map.get(s.charAt(j)) > i) {
                    i = map.get(s.charAt(j));
                }
            }
            if ((j - i + 1) > ans) {
                ans = j - i + 1;
                end = j;
            }
            map.put(s.charAt(j), j + 1);
        }
        System.out.println("end = " + end);
        return s.substring(end + 1 - ans, end + 1);
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "abcabcdb";
        String s3 = "bbbbbb";

        System.out.println(s1 + " " + lengthOfLongestSubstring(s1) + " " + longestSubstring(s1));
        System.out.println(s2 + " " + lengthOfLongestSubstring(s2) + " " + longestSubstring(s2));
        System.out.println(s3 + " " + lengthOfLongestSubstring(s3) + " " + longestSubstring(s3));
    }

}