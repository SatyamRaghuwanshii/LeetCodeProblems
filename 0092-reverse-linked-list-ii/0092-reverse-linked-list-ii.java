class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode L1 = dummy;
        ListNode R1 = dummy;
        for(int i = 0; i<left-1; i++){
            L1 = L1.next;
        }
        for(int i = 0; i<right; i++){
            R1 = R1.next;
        }
    
        ListNode prev = R1.next;
        ListNode curr = L1.next;
        ListNode next;
        for(int i = 1; i<= right-left+1; i++){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        L1.next = prev;

        return dummy.next;
    }
}
