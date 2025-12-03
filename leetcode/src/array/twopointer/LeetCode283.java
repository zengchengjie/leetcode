
package array.twopointer;

/**
 * LeetCode 283. 移动零
 * 
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意，必须在不复制数组的情况下原地对数组进行操作。
 */
public class LeetCode283 {

    /**
     * 方法一：双指针法
     * 时间复杂度：O(n)，其中n是数组nums的长度
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 使用双指针，一个指向非零元素应该放置的位置，一个遍历数组
        int nonZeroIndex = 0;

        // 第一次遍历：将所有非零元素移到数组前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex] = nums[i];
                nonZeroIndex++;
            }
        }

        // 第二次遍历：将剩余位置填充为0
        for (int i = nonZeroIndex; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 方法二：优化的双指针法
     * 时间复杂度：O(n)，其中n是数组nums的长度
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     */
    public void moveZeroesOptimized(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 使用双指针，一个指向非零元素应该放置的位置，一个遍历数组
        int nonZeroIndex = 0;

        // 遍历数组，将非零元素移到前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 只有当i和nonZeroIndex不相等时才进行交换，减少不必要的操作
                if (i != nonZeroIndex) {
                    int temp = nums[i];
                    nums[i] = nums[nonZeroIndex];
                    nums[nonZeroIndex] = temp;
                }
                nonZeroIndex++;
            }
        }
    }

    /**
     * 方法三：单次遍历法
     * 时间复杂度：O(n)，其中n是数组nums的长度
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     */
    public void moveZeroesSinglePass(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 使用双指针，一个指向非零元素应该放置的位置，一个遍历数组
        int nonZeroIndex = 0;

        // 遍历数组，遇到非零元素就交换
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 交换当前元素和非零指针指向的元素
                int temp = nums[i];
                nums[i] = nums[nonZeroIndex];
                nums[nonZeroIndex] = temp;
                nonZeroIndex++;
            }
        }
    }

    /**
     * 方法四：使用标记法
     * 时间复杂度：O(n)，其中n是数组nums的长度
     * 空间复杂度：O(1)
     * 
     * @param nums 输入数组
     */
    public void moveZeroesWithFlag(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        // 使用双指针，一个指向非零元素应该放置的位置，一个遍历数组
        int nonZeroIndex = 0;

        // 第一次遍历：将所有非零元素移到数组前面
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[nonZeroIndex++] = nums[i];
            }
        }

        // 第二次遍历：将剩余位置填充为0
        while (nonZeroIndex < nums.length) {
            nums[nonZeroIndex++] = 0;
        }
    }
}
