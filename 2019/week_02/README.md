# Longest Palindromic Sunstring
**LeetCode-5**

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:
```
Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
```

Example 2:
```
Input: "cbbd"
Output: "bb"
```
给一个字符串s，假设s的最大长度为1000，求出s的最长回文子串。

## 误区
回文串--从左向右遍历和从右向左遍历得到的结果是一致的。根据这一特性，我们再求解字符串s的最长回文子串，可能会陷入一个**误区**:将字符串s翻转得字符串ss，根据回文串的特性，s和ss有**最长的公共子串**，那这个最长的公共子串是否是最长的回文子串呢？大家先思考一样。

答案是否定的，给出反例。
```
s = "abgkba" => ss= "abkgba"
s和ss最长公共子串是"ab"或者"ba"，而最长的回文子串是单个字符串。
```

## 暴力求解
暴力求解，得到s**所有的**子串，遍历每一个子串，判断子串是否是回文字符串。很暴力，复杂度达到了O(n<sup>3</sup>)。

## 动态规划
假设字符串s的子串的起始坐标为j，终点坐标为i，用boolean lps[j][i]来标记从j到i的子串是否为回文串。根据回文串的特性，如果从j到i的子串是回文串，则从j+1到i-1的子串也是回文串。从长度为1的子串开始遍历，长度为1的子串一定是回文串，长度为2的子串，如果满足s.charAt(j)==s.charAt(i)，则该长度为2的子串也是回文串。可以推导出**lps[j][i] = lps[j+1][i-1] && s.charAt(j) == s.charAt(i)**。

空间复杂度为O(n<sup>2</sup>)，时间复杂度为O(n<sup>2</sup>)。

代码如下:
```Java
/**
 * Longest Palindromic Substring
 */
public class LongestPalindromicSubstring {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 0)
            return "";
        
        int len = s.length();
        boolean[][] lps = new boolean[len][len];
        // 记录最长回文子串的长度
        int maxPalindromeLen = 0;
        // 记录最长回文子串的起始坐标
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
}
```
## Manacher算法(马拉车算法)
Manacher算法相对于上面的动态规划算法，利用回文串对称特性优化了遍历的过程，将算法复杂度降低到O(n)。
为了避免奇偶处理，我们首先在字符串s首尾和字符间插入一个字符'#'（前提是这个字符未出现在串里），这样得到的新字符串的长度始终都是奇数。另外为了避免边界判断，我们再新字符首尾加不同的字符'$'和'^'得到新字符newS。对于新字符串，我们定义数组p，其中p[i]表示已newS[i]字符为中心的回文子串的半径。
<space>|<space>
---|---
s|abcdedcba
newS|$#a#b#c#d#e#d#c#b#a#^
p|[1,1,2,1,2,1,2,1,2,1,10,1,2,1,2,1,2,1,2,1,1]

我们可以证明p[i]-1就是以newS[i]为中心在原字符串s中的回文串的长度。

这样，确定了数组p，找到最大值和索引就可以求出最长回文子串。我们定义两个辅助变量id和mx，其中id为已知最长回文子串的中心位置，那么mx=id+p[id]，即是这个回文子串的右边界。

用变量i遍历字符串，假设j为i关于id的对称点，则j=2*id-i。

![pls_1](img/pls_1.png)
<small>(来自于[网络图片](https://my.oschina.net/hunglish/blog/747642))</small>

如上图所示，当mx-i>p[j]时，以newS[j]为中心的回文子串在以newS[id]为中心的回文子串中，由于i和j对称，以newS[i]为中心的回文子串必然包含在以newS[id]为中心的回文子串中，所以p[i]=p[j]。

![pls_2](img/pls_2.png)
<small>(来自于[网络图片](https://my.oschina.net/hunglish/blog/747642))</small>

如上图所示，当mx-i<=p[j]时，以newS[j]为中心的回文子串不完全在newS[id]为中心的回文串内，但图中所包含的绿色部分相同，也就是说p[i]>=mx-i。

数组p的初始值可以都赋值为1，因为单个字符的回文半径为1。

代码如下：
```Java
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
```







