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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
        ListNode next1 = curr.next;
        ListNode next2;
        while(curr.next != null){
            next2 = next1.next;
            next1.next = curr;
            curr.next = next2;
            prev.next = next1;
            prev = curr;
            if(prev.next != null){
                curr = next2;
                next1 = curr.next;
            }
        }
        return dummy.next;
    }
}