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
    public ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode temp = head;
        ListNode next;
        while(temp.next != null){
            int val = GCD(temp.val,temp.next.val);
            ListNode middle = new ListNode(val);
            next = temp.next;
            temp.next = middle;
            middle.next = next;
            temp = next;
        }
        return head;
    }
    private int GCD(int a, int b){
        int temp = a/b;
        int diff = a - temp*b;
        if(diff == 0) return b;
        return GCD(b,diff);
    }
}