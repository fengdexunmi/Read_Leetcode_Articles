# Longest Common Prefix

LeetCode-14 Easy

Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:
```
Input: ["flower","flow","flight"]
Output: "fl"
```
Example 2:
```
Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
```

**Note:**

All given inputs are in lowercase letters a-z.

找出公共最长前缀，不是找出公共最长子串。

采用横向匹配，把数组的第一个元素作为起始的最长前缀去匹配，遇到匹配不上的，就裁剪当前的最长前缀（去掉当前最长前缀的最后一个字符）再去匹配。

代码如下：

```Java
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0) return "";

        String prefix = strs[0];
        for (int i = 1 ;i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix;
    }
}

```

当然，默认的最长前缀可以是数组第一个元素，也可以从第一个元素的一半（有点二分查找的感觉）。匹配不上的时候裁剪的方法也可以不一样（也可以是二分裁剪）。