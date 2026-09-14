class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null) return list2;
        if(list2 == null) return list1;
        ListNode dummy = new ListNode(0);
        ListNode curr1 = list1;
        ListNode curr2 = list2;
        ListNode curr = dummy;

        while(curr1 != null && curr2 != null){
            if(curr1.val < curr2.val){
                curr.next = curr1;
                curr1 = curr1.next;
            }else{
                curr.next = curr2;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        curr.next = curr1 != null? curr1 : curr2;
        return dummy.next;
    }
}