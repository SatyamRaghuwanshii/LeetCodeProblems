class MinStack {
    public class Node{
        int val;
        int min;
        Node next;
        Node(int val, int min){
            this.val = val;
            this.min = min;
            this.next = null;
        }
    }
    private Node top;
    private Node min;
    public MinStack() {
        this.top = null;
        this.min = null;
    }
    
    public void push(int value) {
        int currMin = top == null ? value: Math.min(value,top.min);
        Node newNode = new Node(value, currMin);
        newNode.next = top;
        top = newNode;
    }
    
    public void pop() {
        top = top.next;
    }
    
    public int top() {
        return top.val;
    }
    
    public int getMin() {
        return top.min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */