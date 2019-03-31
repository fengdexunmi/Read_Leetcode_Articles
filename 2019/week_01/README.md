## LeetCode-4
[Median of Two Sorted Arrays](https://leetcode.com/problems/median-of-two-sorted-arrays/)

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:
```
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
```

Example 2:
```
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
```
求两个有序数组的中位数，第一个想到的可能是遍历合并两个数组，然后得出中位数。但是这种方法的时间复杂度为O(m+n)。如何在将复杂度降到O(log(m+n))？我认为这篇文章给出了最好的思路 https://blog.csdn.net/hk2291976/article/details/51107778

#### Cut
对有序数组进行cut操作，将数组分割为左右两个部分。cut左右两边对应的元素则分别是左边的最大值和右边的最小值。定义L=Max(LeftPart), R=Min(RightPart)。
对于单数组而言，在数组中间进行cut操作就可以得出中位数。  
比如序列[2, 5, 9, 11]，在5和9之间进行cut，中位数 = (5 + 9) / 2 = 7  
序列[3, 8, 12]，在数字8上进行cut，中位数 = (8 + 8) / 2 = 8。

#### 双数组（多数组）
设：  
- Ci为第i个数组的cut
- Li为第i个数组cut后的左边元素
- Ri为第i个数组cut后的右边元素

LeftPart | Ci | RightPart | Len
:---: | :---: | :---:| :---:
a<sub>1</sub>, a<sub>2</sub>, a<sub>3</sub>,...,a<sub>i</sub> | / | a<sub>i+1</sub>, a<sub>i+2</sub>,...,a<sub>m</sub> | m
b<sub>1</sub>, b<sub>2</sub>, b<sub>3</sub>,...,b<sub>j</sub> | / | b<sub>j+1</sub>, b<sub>j+2</sub>,...,b<sub>n</sub> | n

L<sub>i</sub>为a<sub>i</sub>、b<sub>j</sub>  
R<sub>i</sub>为a<sub>i+1</sub>、b<sub>j+1</sub>

a<sub>i</sub> <= a<sub>i+1</sub>以及b<sub>j</sub> <= b<sub>j+1</sub>是一定的，如果a<sub>i</sub> <= b<sub>j+1</sub> && b<sub>j</sub> <= a<sub>i+1</sub>，则序列a和序列b合并之后，cut左边的部分为a<sub>1</sub>...a<sub>i</sub>和b<sub>1</sub>...b<sub>j</sub>组成的一个整体，右边部分为a<sub>i+1</sub>...a<sub>m</sub>和b<sub>j+1</sub>...b<sub>n</sub>组成的一个整体，如果左边的整体的长度和右边整体的长度相等。则可以得出中位数 = (Max(a<sub>i</sub>, b<sub>j</sub>) + Min(a<sub>i+1</sub>, b<sub>j+1</sub>))/2.0  
逆向思维，合并之后序列总长度为m+n，先达成第一个相等条件（左边整体和右边整体长度相等），以二分原则对序列a进行cut操作取出k个数，则序列b需要取出(m+n)/2-k个数从而计算出b的cut位置，不断调整序列a的cut位置，最终达成ai <= bj+1 && bj <= ai+1条件则完成。

#### 避免奇偶数
为了避免处理奇偶数，我们虚拟添加'#'到序列元素两边，让序列长度恒为奇数(为原来的2倍+1)。  
Before|Len|After|Len
:---:|:---:|:---:|:---:
[2, 5, 9, 11] | 4 | [#, 2, #, 5, #, 9, #, 11, #] | 9 
[3, 8, 12] | 3 | [#, 3, #, 8, #, 12, #] | 7
这样处理之后，我们能得出：  
Li = (Ci-1)/2   
Ri = Ci/2
对于序列[#, 2, #, 5, #, 9, #, 11, #]，C=4(下标)，L=1, R=2,分别对应了5和9在原数组的下标。  
对于序列[#, 3, #, 8, #, 12, #]，C=3(下标)，L=1, R=1,对应了8在原数组的下标。  
所以，才有虚拟添加#的说法，不是真的添加#到序列中。

把2个序列看做一个虚拟的序列S，目前有2m+2n+2个元素，割在m+n+1处，所以我们只需找到m+n+1位置的元素和m+n+2位置的元素就行了。 
左边：S<sub>left</sub> = Max(L1, L2) 
右边：S<sub>right</sub> = Min(R1, R2)
中位数 = (Max(L1, L2) + Min(R1, R2))/2.0

#### 代码实现
参见[MedianOfTwoSortedArrays.java](./MedianOfTwoSortedArrays.java)
