/**
 * Regular Expression Matching
 */
public class REMatching {

    public boolean isMatch(String s, String p) {
        boolean[][] map = new boolean[s.length() + 1][p.length() + 1];
        // match empty String
        map[0][0] = true;
        
        for (int i = 1; i < p.length() + 1; i++) {
            if (p.charAt(i - 1) == '*') {
                // "a*b*c*" matchs empty String
                map[0][i] = map[0][i-2];
            }
        }
        
        for (int i = 1; i < s.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
                    map[i][j] = map[i-1][j-1];
                } else if (p.charAt(j - 1) == '*') {
                    map[i][j] = map[i][j-2];
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
                        map[i][j] = map[i][j] || map[i-1][j];
                    }
                } else {
                    map[i][j] = false;
                }
            }
        }
        
        return map[s.length()][p.length()];
    }
}
