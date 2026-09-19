class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode head2 = head.next;
        ListNode odd = head;
        ListNode even = head2;
        while(even != null){
            odd.next = even.next;
            odd = odd.next;
            
            even.next = odd.next;
            even = even.next;

            if(odd.next == null || odd.next.next == null){
                odd.next = head2;
                break;
            }
        }
        return head;
    }
}