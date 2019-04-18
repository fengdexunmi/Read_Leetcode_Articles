/**
 * ZigZagConversion
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        String s = "ABCDE";
        System.out.println(convert2(s, 4));
    }

    public static String convert(String s, int numRows) {
        if (s == null || s.length() <= 0) {
            return "";
        }
        
        if (numRows <= 0) {
            return "";
        }
        
        if (numRows == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int max = len / (2 * (numRows - 1)) + 1;
        if (max * numRows < len) {
            max = max + 1;
        }
        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i < max; i++) {
                int up = 2 * i * (numRows - 1) + j;
                int down = 2 * i * (numRows - 1) - j;
                if (j == 0 || j == numRows - 1) {
                    if (up < len) {
                        sb.append(s.charAt(up));
                    }
                } else {
                    if (down >= 0 && down < len) {
                        sb.append(s.charAt(down));
                    }
                    if (up < len) {
                        sb.append(s.charAt(up));
                    }
                }

                if (sb.length() == len) {
                    return sb.toString();
                }
            }
        }

        return "";
    }

    public static String convert2(String s, int numRows) {
        if (s == null || s.length() <= 0) {
            return "";
        }

        if (numRows <= 0) {
            return "";
        }

        if (numRows == 1) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int cycleLen = 2 * numRows - 2;
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < len; j += cycleLen) {
                sb.append(s.charAt(j));

                if (i > 0 && i < numRows - 1) {
                    int next = j + cycleLen - 2 * i;
                    if (next < len) {
                        sb.append(s.charAt(next));
                    }
                }
            }
        }

        return sb.toString();
    }
}

