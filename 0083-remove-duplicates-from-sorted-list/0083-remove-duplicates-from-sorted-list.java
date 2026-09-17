class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode prev = head;
        while(prev != null ){
            while(prev.next != null && prev.val == prev.next.val){
                prev.next = prev.next.next;
            }
            prev = prev.next;
        }
        return head;
    }
}