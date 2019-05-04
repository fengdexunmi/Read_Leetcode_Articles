# Palindrome Number
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
