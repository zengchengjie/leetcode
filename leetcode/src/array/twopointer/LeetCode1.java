
package array.twopointer;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 1. 两数之和
 * 
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值 target 的那两个整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 * 你可以按任意顺序返回答案。
 */
public class LeetCode1 {

    /**
     * 方法一：哈希表
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     * 
     * @param nums 整数数组
     * @param target 目标值
     * @return 两个整数的数组下标
     */
    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];

        // 建立k-v，一一对应的哈希表
        // 目标：找到当前数的下标和当前数的补数的下标，就是目标的下标值
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                indices[0] = i;
                indices[1] = hash.get(nums[i]);
                return indices;
            }
            // 将数据存入 key为补数，value为下标
            hash.put(target - nums[i], i);
        }

        return indices;
    }

    /**
     * 方法二：暴力枚举
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     * 
     * @param nums 整数数组
     * @param target 目标值
     * @return 两个整数的数组下标
     */
    public int[] twoSumBruteForce(int[] nums, int target) {
        int[] indices = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] + nums[j] == target) {
                    indices[0] = i;
                    indices[1] = j;
                    return indices;
                }
            }
        }

        return indices;
    }
}
