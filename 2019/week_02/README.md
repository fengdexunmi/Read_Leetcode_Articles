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

