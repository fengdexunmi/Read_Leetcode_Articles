# Integer to Roman
LeetCode-12

整型转化为罗马数字，难度中等。

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol| Value
--|--
I|1
V|5
X|10
L|50
C|100
D|500
M|1000

For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

- I can be placed before V (5) and X (10) to make 4 and 9. 
- X can be placed before L (50) and C (100) to make 40 and 90. 
- C can be placed before D (500) and M (1000) to make 400 and 900.

Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:
```
Input: 3
Output: "III"
```

Example 2:
```
Input: 4
Output: "IV"
```

Example 3:
```
Input: 9
Output: "IX"
```

Example 4:
```
Input: 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
```

Example 5:
```
Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
```
**Show me the code**

```Java
class IntToRoman {
    public String intToRoman(int num) {
        String roman = "";
        if (num < 1 || num > 3999) {
            return roman;
        }
        
        String[][] allRoman = new String[4][9];
        allRoman[0] = new String[] { "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        allRoman[1] = new String[] { "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        allRoman[2] = new String[] { "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        allRoman[3] = new String[] { "M", "MM", "MMM", "", "", "", "", "", "" };
        int r = 0;
        int i = 0;
        while (num != 0) {
            r = num % 10;
            if (r != 0) {
                roman = allRoman[i][r-1] + roman;
            }
            i++;
            num = num / 10;
        }
        
        return roman;
    }
}
```
