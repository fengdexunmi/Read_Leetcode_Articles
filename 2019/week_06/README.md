# Container With Most Water

LeetCode-11

Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

**Note: You may not slant the container and n is at least 2.**

![question_11](quesion_11.jpg)

The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.

Example:
```
Input: [1,8,6,2,5,4,8,3,7]
Output: 49
```

## 双指针解决方法

定义两个指针，一个指针从数组的左边开始遍历，另一个指针从右边开始遍历。遍历过程中不断比较大小。

代码如下：

```Java

public class Solution {
    public int maxArea(int[] height) {
        int maxarea = 0, l = 0, r = height.length - 1;
        while (l < r) {
            maxarea = Math.max(maxarea, Math.min(height[l], height[r]) * (r - l));
            if (height[l] < height[r]) // 1 这个地方可以优化
                l++;
            else // 2 这个地方可以优化
                r--;
        }
        return maxarea;
    }
}

```

对于上述代码，仍然有优化的地方。对于`注释1`处，我们可以发现如果height[l] < height[r]时，height[l+1] < height[l]，那么有没有必要比较[l+1]的maxarea值，因为[l+1]的的maxarea值必然小于[l]的。同理`注释2`处也是一样。优化后的代码如下：

```Java

class Solution {
    public int maxArea(int[] height) {
        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            int maxHeight = height[l] < height[r] ? height[l] : height[r];
            while (l < r && height[l] <= maxHeight) {
                l++;
            }
            while (r > l && height[r] <= maxHeight) {
                r--;
            }
        }
        
        return max;
    }
}

```
