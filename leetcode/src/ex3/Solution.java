package ex3;

import java.util.List;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        List<String> data = Arrays.asList("abcabcbb", "bbbbb", "pwwkew");
        for (String str: data) {
            System.out.println(str + ": " + s.lengthOfLongestSubstring(str));
        }
    }

    public int lengthOfLongestSubstring(String s) {
        String output = "";
//        int max = 0;
        for (int start = 0, len = s.length(); start < len; start++) {
            Set<Character> visited = new HashSet<>();
            int end = start;
            for (; end < len; end++) {
                Character curChar = Character.valueOf(s.charAt(end));
                if (visited.contains(curChar)) {
                    break;
                }
                visited.add(curChar);
            }
            if (output.length() < end - start) {
                // allows output to keep growing
                output = s.substring(start, end);
            }
//            max = Math.max(max, end - start);
        }

        return output.length();
//        return max;
    }
}
