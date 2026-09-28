class Solution {
    public Node copyRandomList(Node head) {
        Map<Node,Node> map = new HashMap<>();
        Node oldNode = head;
        while(oldNode != null){
            Node newNode = new Node(oldNode.val);
            map.put(oldNode,newNode);
            oldNode = oldNode.next; 
        }
        Node newHead = map.get(head);
        Node copy = map.get(head);
        while(head != null){
            copy.next = map.get(head.next);
            copy.random = map.get(head.random);
            copy = copy.next;
            head = head.next;
        }
        return newHead;
    }
}