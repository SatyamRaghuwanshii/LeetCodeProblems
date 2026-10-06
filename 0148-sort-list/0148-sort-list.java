class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode right = slow.next;
        slow.next = null;
        right = sortList(right);
        ListNode left = sortList(head);
        return merge(left,right);
    }
    private ListNode merge(ListNode left, ListNode right){
        if(left == null) return right;
        if(right == null) return left;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while(left != null && right != null){
            if(left.val < right.val){
                curr.next = left;
                left = left.next;
            }else{
                curr.next = right;
                right = right.next;
            }
            curr = curr.next;
        }
        curr.next = left == null ? right : left;
        return dummy.next;
    }
}