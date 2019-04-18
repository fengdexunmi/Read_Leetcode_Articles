# ZigZag Conversion
**LeetCode-6**

The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
```
P   A   H   N
A P L S I I G
Y   I   R
```
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
```
Example 1:
Input: s = "PAYPALISHIRING", 
numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING",  
numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I
```

题目的难度是中等。“ZigZag Conversion”锯齿形转换。

可以通过穷举法，推算出规则。比如：
```
从下标来看

0     6       12
1   5 7    11 13
2 4   8 10    14 
3     9       15
```
假设总行数为r，每一行都是包含了一个等差数列，公差为2*r-2。

上面的数据，r=4

第0行（首行）为0、6、12，公差为6，

第1行可以先看：1、7、13，公差也为6，
然后在7前面补上5（值差为2），13前面补上11（值差为2）

第2行可以先看：2、8、14，公差也为6，
然后在8前面补上4（值差为4），14前面补上4（值差为4）

...

第i行，先看等差数列：i、i+(2r-2)、i+2(2r-2)，然后i+(2r-2)补上i+(2r-2)-2i（差值为2i），i+2(2r-2)补上i+2(2r-2)-2i（差值为2i）

尾行为3、9、15，公差为6。

所以，有两层循环，第一层维度是行数，第二层是具体的数列。看代码更容易理解。

关键代码如下：
```Java
public String convert(String s, int rows) {
    if (rows == 1) {
        return s;
    }
    StringBuidler sb = new StringBuilder();
    // 公差
    int dif = 2 * rows - 2;
    // 第一层循环行数
    for (int i = 0; i < rows; i++) {
        for (int j = i; j < len; j += dif) {
            sb.append(s.charAt(j));
            if (i > 0 && i < rows - 1) {
                // 找到需要补上的数的下标（比如上面所说的5、11）
                int next = j + dif - 2 * i;
                if (next < len) {
                    sb.append(next);
                }
            }
        }
    }

    return sb.toString();
}
```
