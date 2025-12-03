
package backtrack.permutation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LeetCode 49. 字母异位词分组
 * 
 * 给你一个字符串数组，请你将字母异位词组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
 */
public class LeetCode49 {

    /**
     * 方法一：排序 + 哈希表
     * 时间复杂度：O(n * k * log k)，其中n是字符串数量，k是字符串的平均长度
     * 空间复杂度：O(n * k)
     * 
     * @param strs 字符串数组
     * @return 字母异位词分组结果
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> groups = new HashMap<>();

        for (String str : strs) {
            // 将字符串转换为字符数组并排序
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortedStr = new String(chars);

            // 使用排序后的字符串作为键
            groups.putIfAbsent(sortedStr, new ArrayList<>());
            groups.get(sortedStr).add(str);
        }

        return new ArrayList<>(groups.values());
    }

    /**
     * 方法二：计数 + 哈希表
     * 时间复杂度：O(n * k)，其中n是字符串数量，k是字符串的平均长度
     * 空间复杂度：O(n * k)
     * 
     * @param strs 字符串数组
     * @return 字母异位词分组结果
     */
    public List<List<String>> groupAnagramsByCount(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> groups = new HashMap<>();

        for (String str : strs) {
            // 统计每个字符出现的次数
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // 将字符计数转换为字符串作为键
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                keyBuilder.append('#').append(count[i]);
            }
            String key = keyBuilder.toString();

            // 使用字符计数作为键
            groups.putIfAbsent(key, new ArrayList<>());
            groups.get(key).add(str);
        }

        return new ArrayList<>(groups.values());
    }

    /**
     * 方法三：质数乘积 + 哈希表
     * 时间复杂度：O(n * k)，其中n是字符串数量，k是字符串的平均长度
     * 空间复杂度：O(n * k)
     * 注意：此方法在字符串长度较大时可能导致整数溢出
     * 
     * @param strs 字符串数组
     * @return 字母异位词分组结果
     */
    public List<List<String>> groupAnagramsByPrime(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        // 26个字母对应的质数
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};

        Map<Long, List<String>> groups = new HashMap<>();

        for (String str : strs) {
            long product = 1;
            for (char c : str.toCharArray()) {
                product *= primes[c - 'a'];
            }

            // 使用质数乘积作为键
            groups.putIfAbsent(product, new ArrayList<>());
            groups.get(product).add(str);
        }

        return new ArrayList<>(groups.values());
    }

    /**
     * 方法四：自定义哈希函数 + 哈希表
     * 时间复杂度：O(n * k)，其中n是字符串数量，k是字符串的平均长度
     * 空间复杂度：O(n * k)
     * 
     * @param strs 字符串数组
     * @return 字母异位词分组结果
     */
    public List<List<String>> groupAnagramsCustomHash(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> groups = new HashMap<>();

        for (String str : strs) {
            // 计算自定义哈希值
            int[] count = new int[26];
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            // 使用字符计数作为键，但使用更紧凑的表示方式
            StringBuilder keyBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    keyBuilder.append((char)('a' + i)).append(count[i]);
                }
            }
            String key = keyBuilder.toString();

            // 使用自定义哈希作为键
            groups.putIfAbsent(key, new ArrayList<>());
            groups.get(key).add(str);
        }

        return new ArrayList<>(groups.values());
    }
}
