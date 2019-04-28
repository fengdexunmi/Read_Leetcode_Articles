## Reverse Integer
LeetCode-7

Given a 32-bit signed integer, reverse digits of an integer.

```
Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
```

Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

直接上代码吧~代码每一步可以很快理解思路

```Java
public int reverse(int x) {
    int rev = 0;
    while (x != 0) {
        int pop = x % 10;
        x /= 10;
        if (rev > Integer.MAX_VALUE/10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
        if (rev < Integer.MIN_VALUE/10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
        rev = rev * 10 + pop;
    }
    return rev;
}

或者 声明的时候用long代替int

public int reverse(int x) {
    int k = 0;
    long r = 0;
    while (x != 0) {
        k = x % 10;
        x = x / 10;
        r = r * 10 + k;
    }
    
    if (r <= Integer.MIN_VALUE || r >= Integer.MAX_VALUE) 
        return 0;
    
    return (int) r;
}

```

## String to Integer (atoi)
LeetCode-8

Implement atoi which converts a string to an integer.

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned.

Note:

Only the space character ' ' is considered as whitespace character.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, INT_MAX (231 − 1) or INT_MIN (−231) is returned.

```
Example 1:

Input: "42"
Output: 42
Example 2:

Input: "   -42"
Output: -42
Explanation: The first non-whitespace character is '-', which is the minus sign.
             Then take as many numerical digits as possible, which gets 42.
Example 3:

Input: "4193 with words"
Output: 4193
Explanation: Conversion stops at digit '3' as the next character is not a numerical digit.
Example 4:

Input: "words and 987"
Output: 0
Explanation: The first non-whitespace character is 'w', which is not a numerical 
             digit or a +/- sign. Therefore no valid conversion could be performed.
Example 5:

Input: "-91283472332"
Output: -2147483648
Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer.
             Thefore INT_MIN (−231) is returned.
```

判断过程使用了LeetCode-7一样的思路，show me the code~~

```Java
public int myAtoi(String str) {
    if (str == null || str.length() <= 0) {
        return 0;
    }
    
    int start = 0;
    int end = 0;
    int len = str.length();
    char[] charArray = str.toCharArray();
    while (start < len && charArray[start] <= ' ') {
      start++;
    }
    
    if (start >= len) {
        return 0;
    }

    int dynamicStart = end = (charArray[start] == '-' || charArray[start] == '+') ? start + 1 : start;

    while (end < len && charArray[end] >= '0' && charArray[end] <= '9') {
      end++;
    }

    if (end == dynamicStart) {
      return 0;
    }

    int num = 0;
    for (int i = dynamicStart; i < end; i++) {
        int next = charArray[start] == '-' ? '0' - charArray[i] : charArray[i] - '0';
        if (num > Integer.MAX_VALUE / 10 || (num == Integer.MAX_VALUE / 10 && next > 7)) {
            return Integer.MAX_VALUE;
        }
        if (num < Integer.MIN_VALUE / 10 || (num == Integer.MIN_VALUE / 10 && next < -8)) {
            return Integer.MIN_VALUE;
        }
        num = num * 10 + next;
    }

    return num;
}

或者更清晰的代码

public int myAtoi(String str) {
    int index = 0, sign = 1, total = 0;
    //1. Empty string
    if(str.length() == 0) return 0;

    //2. Remove Spaces
    while(str.charAt(index) == ' ' && index < str.length())
        index ++;

    //3. Handle signs
    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
        sign = str.charAt(index) == '+' ? 1 : -1;
        index ++;
    }
    
    //4. Convert number and avoid overflow
    while(index < str.length()){
        int digit = str.charAt(index) - '0';
        if(digit < 0 || digit > 9) break;

        //check if total will be overflow after 10 times and add digit
        if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

        total = 10 * total + digit;
        index ++;
    }
    return total * sign;
}

```

LeetCode-7和LeetCode-8都不算难，按部就班地去解决，中间也没有很绕的处理逻辑。
