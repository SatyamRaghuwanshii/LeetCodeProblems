public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> map = new HashSet<>();
        while(headA != null){
            map.add(headA);
            headA = headA.next;
        }
        while(headB != null){
            if(map.contains(headB)){
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}