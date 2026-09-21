class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode second = slow.next;
        slow.next = null;

        ListNode prev = null;
        ListNode next;
        while(second != null){
            next = second.next;
            second.next = prev;
            prev = second;
            second = next;
        }
        ListNode curr = head;
        ListNode next1;
        while(prev != null){
            next = curr.next;
            next1 = prev.next;
            curr.next = prev;
            curr = next;
            prev.next = curr;
            prev = next1;
        }
    }
}