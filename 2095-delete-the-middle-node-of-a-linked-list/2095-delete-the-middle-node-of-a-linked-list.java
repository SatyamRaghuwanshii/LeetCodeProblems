class Solution {
    public ListNode deleteMiddle(ListNode head) {
        if(head.next == null){
            head = null;
            return head;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next.next;
        slow.next = temp;
        return head;
    }
}