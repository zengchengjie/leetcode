
package array.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * LeetCode 15.     三数之和
 * 
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？
 * 请你找出所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class LeetCode15 {

    /**
     * 方法：排序 + 双指针
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 
     * @param nums 整数数组
     * @return 所有不重复的三元组
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        if (nums == null || nums.length < 3) {
            return result;
        }

        // 排序数组，为双指针做准备
        Arrays.sort(nums);

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            // 跳过重复元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 由于排过序了，如果当前元素大于0，后面不可能有三个数相加等于0
            if (nums[i] > 0) {
                break;
            }

            int left = i + 1;
            int right = n - 1;
            int target = -nums[i]; // 目标和

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == target) {
                    // 找到一个解
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < target) {
                    // 和小于目标，左指针右移
                    left++;
                } else {
                    // 和大于目标，右指针左移
                    right--;
                }
            }
        }

        return result;
    }
}
