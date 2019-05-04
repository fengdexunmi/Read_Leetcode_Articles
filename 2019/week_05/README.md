## Palindrome Number
LeetCode-9

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

**Example 1:**
```
Input: 121
Output: true
```

**Example 2:**
```
Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
```

**Example 3:**
```
Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
```

**Follow up:**

Coud you solve it without converting the integer to a string?

回文数字，这道题目比较简单。直接反转数字，然后比对反转前后的数字。

代码如下：

```Java
public boolean isPalindrome(int x) {
    if (x < 0) {
        return false;
    }

    if (x >= 0 && x < 10) {
        return true;
    }

    long rev = 0;
    long temp = x;
    while (x != 0) {
        rev = rev * 10 + (x % 10);
        x = x / 10;
    }

    if (rev == temp) {
        return true;
    }

    return false;
}
```

## Regular Expression Matching
LeetCode-10

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.

```
'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).
```
**Note:**

- s could be empty and contains only lowercase letters a-z.
- p could be empty and contains only lowercase letters a-z, and characters like . or *.

**Example 1:**
```
Input:
s = "aa"
p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
```

**Example 2:**
```
Input:
s = "aa"
p = "a*"
Output: true
Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
```

**Example 3:**
```
Input:
s = "ab"
p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".
```

**Example 4:**
```
Input:
s = "aab"
p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
```

**Example 5:**
```
Input:
s = "mississippi"
p = "mis*is*p*."
Output: false
```

此题目难度很大，我看了Solution，然后Google了很多解答，才明白动态规划的解法（将问题传递到其子问题求解）。

这里引用一篇外文解析，这一篇应该算是最能看得懂的。

http://hanslen.me/2017/08/28/Solving-Regular-Expression-Matching-with-Dynamic-programming/

首先我们在s和p前面加一个虚拟的空字符串""，并不是真正地加进去。

我们创建一个二维数组map，map[i][j]表示s[0:i]能够与p[0:j]匹配。

```
boolean[][] map = new boolean[s.length() + 1][p.length() + 1];
```

因为s和p前面一个是空字符串，两个空字符串肯定是匹配的，所以有map[0][0]=true。

我们再考虑以下情形：

```
1. 
s = "abc";
p = "abc" or "a.c";

if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.') {
    // 转换为子问题
    map[i][j] = map[i - 1][j - 1];
}

2. 
s = "abc";
p = "a*b*c*";


p.charAt(j - 1) == "*"
这种情况下，首先我们可以得到：map[i][j] = map[i][j - 2]，如果s.charAt(i - 1) == p.charAt(j - 2)或者p.charAt(j - 2) == '.'，转换为子问题map[i][j] = map[i][j - 2] || map[i - 1][j]。

```

代码如下：

```Java
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

```
