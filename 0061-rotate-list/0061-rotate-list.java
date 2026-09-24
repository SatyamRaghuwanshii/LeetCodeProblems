class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;
        int len = 0;
        ListNode curr = head;
        while(curr != null){
            len++;
            curr = curr.next;
        }
        k = k % len;
        if(k == 0) return head;
        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0; i<k; i++){
            fast = fast.next;
        }
        while(fast.next != null){
            slow = slow.next;
            fast = fast.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        fast.next = head;
        return temp;
    }
}