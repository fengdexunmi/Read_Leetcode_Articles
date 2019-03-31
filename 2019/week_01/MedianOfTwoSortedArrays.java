/**
 * MedianOfTwoSortedArrays
 */
public class MedianOfTwoSortedArrays {

    public static double findMedian(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if (n > m) {
            // 保证第一个数组最短
            return findMedian(nums2, nums1);
        }
        int l1 = 0, l2 = 0, r1 = 0, r2 = 0, c1, c2;
        int lo = 0, hi = 2 *n; // 虚拟加了#,所以数组的长度为2*n
        while (lo <= hi) {
            c1 = (lo + hi) / 2;
            c2 = m + n - c1;
            l1 = (c1 == 0) ? Integer.MIN_VALUE : nums1[(c1 - 1) / 2];
            r1 = (c1 == 2 * n) ? Integer.MAX_VALUE : nums1[c1 / 2];
            l2 = (c2 == 0) ? Integer.MIN_VALUE : nums2[(c2 - 1) / 2];
            r2 = (c2 == 2 * m) ? Integer.MAX_VALUE : nums2[c2 / 2];

            if (l1 > r2) {
                hi = c1 - 1;
            } else if (l2 > r1) {
                lo = c1 + 1;
            } else {
                break;
            }
        }

        return (Integer.max(l1, l2) + Integer.min(r1, r2)) / 2.0;
    }

}