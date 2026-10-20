/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int pairSum(ListNode head) {
        Deque<Integer> st = new ArrayDeque<>();
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        while(slow != null){
            st.push(slow.val);
            slow = slow.next;
        }
        int maxSum = 0;
        while(!st.isEmpty()){
            int sum = head.val + st.pop();
            if(sum > maxSum) maxSum = sum;
            head = head.next;
        }
        return maxSum;
    }
}