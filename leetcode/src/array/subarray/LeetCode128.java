
package array.subarray;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * LeetCode 128. 最长连续序列
 * 
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 */
public class LeetCode128 {

    /**
     * 方法一：使用哈希表
     * 时间复杂度：O(n)，其中n是数组nums的长度
     * 空间复杂度：O(n)
     * 
     * @param nums 未排序的整数数组
     * @return 最长连续序列的长度
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 将所有数字存入哈希表
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }

        int longestStreak = 0;

        // 遍历所有数字
        for (int num : numSet) {
            // 只有当num-1不在集合中时，才开始计算连续序列
            // 这样可以避免重复计算，确保每个序列只被计算一次
            if (!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                // 计算连续序列的长度
                while (numSet.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                // 更新最长序列长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }

    /**
     * 方法二：使用并查集
     * 时间复杂度：O(n * α(n))，其中α是阿克曼函数的反函数，可以近似为常数
     * 空间复杂度：O(n)
     * 
     * @param nums 未排序的整数数组
     * @return 最长连续序列的长度
     */
    public int longestConsecutiveUnionFind(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        UnionFind uf = new UnionFind(n);

        // 使用哈希表记录每个数字在数组中的索引
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < n; i++) {
            numToIndex.put(nums[i], i);
        }

        // 遍历所有数字
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            // 如果num-1存在，则将num和num-1合并
            if (numToIndex.containsKey(num - 1)) {
                uf.union(i, numToIndex.get(num - 1));
            }
            // 如果num+1存在，则将num和num+1合并
            if (numToIndex.containsKey(num + 1)) {
                uf.union(i, numToIndex.get(num + 1));
            }
        }

        return uf.getMaxSize();
    }

    /**
     * 并查集实现
     */
    private class UnionFind {
        private int[] parent;  // 父节点数组
        private int[] size;     // 大小数组
        private int maxSize;    // 最大集合大小

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            maxSize = 1;

            // 初始化：每个元素的父节点是自己，大小为1
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /**
         * 查找元素的根节点
         */
        public int find(int x) {
            // 路径压缩
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        /**
         * 合并两个元素所在的集合
         */
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return;
            }

            // 按大小合并
            if (size[rootX] < size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
                maxSize = Math.max(maxSize, size[rootY]);
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY];
                maxSize = Math.max(maxSize, size[rootX]);
            }
        }

        /**
         * 获取最大集合大小
         */
        public int getMaxSize() {
            return maxSize;
        }
    }
}
