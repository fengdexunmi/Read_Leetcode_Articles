# 3Sum
LeetCode 15 Medium

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:
```
Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
```
首先想到的是暴力解法，三层循环，找到所有情况，再把重复的去掉。这种做法报了Time Limit Exceeded了。所以怎么优化呢？

将3sum转为2sum，先取一个元素nums[i]，再从剩余元素中取出两个元素nums[j]和nums[k]，满足nums[i] + nums[j] + nums[k] = 0即可。
明显第一次取出的nums[i]和第二次取出的nums[i+1]不应该是一样的，不然就重复求2sum了。同理取出两个元素nums[j]和nums[k]也不应该重复。

首先，我们对nums排序，再求2sum的时候，使用头尾指针去遍历数组，这样复杂度从 O(n²)降到O(n)。

当移动头指针时，发现和后面的元素相同的时候就再向后移动头指针；

当移动尾指针时，发现和前面的元素相同的时候就再向前移动尾指针。

代码如下: 

```Java
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // 首先对数组进行排序
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int lo = i + 1, hi = nums.length - 1, sum = 0 - nums[i];
            while (lo < hi) {
                if (nums[lo] + nums[hi] == sum) {
                    result.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                    while (lo < hi && nums[lo] == nums[lo + 1]) {
                        lo++;
                    }
                    while (lo < hi && nums[hi] == nums[hi - 1]) {
                        hi--;
                    }
                    lo++;
                    hi--;
                } else if (nums[lo] + nums[hi] < sum) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }

        return  result;
    }
}
```



