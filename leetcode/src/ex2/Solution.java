package ex2;

//import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;

//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//        Output: 7 -> 0 -> 8
//        Explanation: 342 + 465 = 807.

public class Solution {

    public static void main(String[] args) {
        ListNode[][] pairs = {
                {new ListNode(2, new ListNode(4, new ListNode(3))),
                        new ListNode(5, new ListNode(6, new ListNode(4)))},
                {new ListNode(0), new ListNode(1)},
                {new ListNode(1, new ListNode(8)), new ListNode(0)},
                {new ListNode(1), new ListNode(9, new ListNode(9))}
        };
        Solution s = new Solution();
        for (ListNode[] pair: pairs) {
            System.out.println("##########");
            System.out.println("result=" + s.addTwoNumbers(pair[0], pair[1]).toString());
        }
    }

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode() {}
     * ListNode(int val) { this.val = val; }
     * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        System.out.println("l1="+l1+", l2="+l2);
        ListNode rslt = new ListNode(); // the next of this result captures the first node of the sum
        ListNode head = rslt;

        int sum = 0;
        int carry = 0;
        while (l1 != null || l2 != null) { // keep processing as long as one of them still has data
            sum = carry + (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            carry = sum / 10;
            head.next = new ListNode(sum % 10);
            head = head.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            head.next = new ListNode(carry);
        }

        return rslt.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            ListNode curr = this;
            StringBuilder sb = new StringBuilder("[");
            while (curr != null) {
                sb.append(curr == this ? "" : ",").append(curr.val);
                curr = curr.next;
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
