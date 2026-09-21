class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head.next == null) return null;
        ListNode slow;
        ListNode fast = head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        slow = dummy;
        int jump = n;
        while(jump != 0 && fast != null){
            fast = fast.next;
            jump--;
        }
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}