import java.util.HashMap;
import java.util.Map;
//因为题目说，每种输入只会对应一个答案，因此最简单的方法是求差，去数组里找
class TowSum {
    // 1. 两数之和

    /**
     * 示例 1：
     *
     * 输入：nums = [2,7,11,15], target = 9
     * 输出：[0,1]
     * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];

        // 建立k-v ，一一对应的哈希表
        //目标：找到当前数的下标和当前数的补数的下标，就是目标的下标值
        HashMap<Integer,Integer> hash = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(hash.containsKey(nums[i])){
                indexs[0] = i;
                indexs[1] = hash.get(nums[i]);
                return indexs;
            }
            // 将数据存入 key为补数 ，value为下标
            hash.put(target-nums[i],i);
        }
        // // 双重循环 循环极限为(n^2-n)/2 
        // for(int i = 0; i < nums.length; i++){
        //     for(int j = nums.length - 1; j > i; j --){
        //         if(nums[i]+nums[j] == target){
        //            indexs[0] = i;
        //            indexs[1] = j; 
        //            return indexs;
        //         }
        //     }
        // }
        return indexs;
    }
}