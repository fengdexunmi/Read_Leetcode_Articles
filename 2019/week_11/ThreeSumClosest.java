import java.util.Arrays;

/**
 * ThreeSumClosest
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[nums.length - 1];

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int left = i + 1, right = nums.length - 1;
                while (left < right) {
                    int sum = nums[left] + nums[right] + nums[i];
                    if (sum < target) {
                        left++;
                    } else if (sum > target) {
                        right++;
                    } else {
                        return sum;
                    }
                    if (Math.abs(sum - target) < Math.abs(result - target)) {
                        result = sum;
                    }
                }
            }
        }

        return result;
    }
}