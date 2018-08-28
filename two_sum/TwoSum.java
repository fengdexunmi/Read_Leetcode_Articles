import java.util.HashMap;
import java.util.Map;

/**
 * Two Sum
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 2, 7, 11, 15 };
        int target = 9;
        TwoSum twoSum = new TwoSum();
        try {
            int[] result = twoSum.twoSum(nums, target);
            for (int i : result) {
                System.out.print(i + " ");
            }
        } catch (Exception e) {
            //TODO: handle exception
            System.out.print(e.getMessage());
        }

        HashMap map = new HashMap<>();
    }
}