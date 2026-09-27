class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0);
        ListNode head1 = small;
        ListNode large = new ListNode(0);
        ListNode head2 = large;
        while(head != null){
            ListNode next = head.next;
            head.next = null;

            if(head.val < x){
                small.next = head;
                small = small.next;
            }else{
                large.next = head;
                large = large.next;
            }
            head = next;
        }
        small.next = head2.next;
        return head1.next;
    }
}