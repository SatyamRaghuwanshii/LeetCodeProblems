class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;
        ListNode curr1 = l1;
        ListNode curr2 = l2;
        while(curr1 != null || curr2 != null || carry != 0){
            int val1 = curr1 != null? curr1.val : 0;
            int val2 = curr2 != null? curr2.val : 0;
            int currSum = val1 + val2 + carry;
            carry = currSum >= 10? currSum/10 : 0;
            curr.next = new ListNode(currSum%10);
            curr = curr.next;
            if(curr1 != null) curr1 = curr1.next;
            if(curr2 != null) curr2 = curr2.next;
        }
        return dummy.next;
    }
}