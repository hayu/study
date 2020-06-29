package ex1;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] data = {1, 3, 3 ,4}; //{2, 7, 11, 15};
        System.out.println(Arrays.toString(twoSum(data, 6)));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, len = nums.length; i < len; i++) {
            Integer diffIdx = map.get(target - nums[i]);
            if (diffIdx != null) {
                return new int[] {diffIdx, i};
            }
            map.put(nums[i], i);
        }
        return new int[] {}; // throwing exception costs more memory than return empty solution
    }
}