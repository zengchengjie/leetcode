
package array.twopointer;

/**
 * LeetCode 11. 盛最多水的容器
 * 
 * 给定一个长度为 n 的整数数组 height。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i])。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器。
 */
public class LeetCode11 {

    /**
     * 方法一：双指针法
     * 时间复杂度：O(n)，其中n是数组height的长度
     * 空间复杂度：O(1)
     * 
     * @param height 高度数组
     * @return 最大水量
     */
    public int maxArea(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        // 使用双指针，一个指向数组开头，一个指向数组末尾
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        // 移动指针，直到指针相遇
        while (left < right) {
            // 计算当前容器的面积
            int currentArea = (right - left) * Math.min(height[left], height[right]);
            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短的指针，因为移动较长的指针只会减少宽度，而不会增加高度
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * 方法二：优化双指针法
     * 时间复杂度：O(n)，其中n是数组height的长度
     * 空间复杂度：O(1)
     * 
     * @param height 高度数组
     * @return 最大水量
     */
    public int maxAreaOptimized(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        // 使用双指针，一个指向数组开头，一个指向数组末尾
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        // 记录左右指针的最大高度
        int leftMax = height[left];
        int rightMax = height[right];

        // 移动指针，直到指针相遇
        while (left < right) {
            // 计算当前容器的面积
            int currentArea = (right - left) * Math.min(leftMax, rightMax);
            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 移动较短的指针，并更新最大高度
            if (leftMax < rightMax) {
                left++;
                leftMax = Math.max(leftMax, height[left]);
            } else {
                right--;
                rightMax = Math.max(rightMax, height[right]);
            }
        }

        return maxArea;
    }

    /**
     * 方法三：暴力解法（不推荐，仅用于对比）
     * 时间复杂度：O(n^2)，其中n是数组height的长度
     * 空间复杂度：O(1)
     * 
     * @param height 高度数组
     * @return 最大水量
     */
    public int maxAreaBruteForce(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        int maxArea = 0;

        // 遍历所有可能的两条线组合
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // 计算当前容器的面积
                int currentArea = (j - i) * Math.min(height[i], height[j]);
                // 更新最大面积
                maxArea = Math.max(maxArea, currentArea);
            }
        }

        return maxArea;
    }

    /**
     * 方法四：双指针法（带提前终止优化）
     * 时间复杂度：O(n)，其中n是数组height的长度
     * 空间复杂度：O(1)
     * 
     * @param height 高度数组
     * @return 最大水量
     */
    public int maxAreaWithEarlyTermination(int[] height) {
        if (height == null || height.length < 2) {
            return 0;
        }

        // 使用双指针，一个指向数组开头，一个指向数组末尾
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;

        // 记录已经遇到的最大高度
        int maxHeight = 0;

        // 移动指针，直到指针相遇
        while (left < right) {
            // 更新最大高度
            maxHeight = Math.max(maxHeight, Math.max(height[left], height[right]));

            // 计算当前容器的面积
            int currentArea = (right - left) * Math.min(height[left], height[right]);
            // 更新最大面积
            maxArea = Math.max(maxArea, currentArea);

            // 提前终止条件：如果当前宽度乘以最大高度已经小于等于最大面积，则可以提前终止
            if ((right - left) * maxHeight <= maxArea) {
                break;
            }

            // 移动较短的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}
