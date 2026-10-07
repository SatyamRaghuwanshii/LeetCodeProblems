class MyCircularQueue {
    int[] q;
    int front;
    int rear;
    public MyCircularQueue(int k) {
        q = new int[k];
        this.front = -1;
        this.rear = -1;
    }
    
    public boolean enQueue(int value) {
        int k = q.length;
        if(front == -1){
            front++;
            q[++rear] = value;
            return true;
        }
        if(front == (rear+1) % k){
            return false;
        }
        rear = (rear+1) % k;
        q[rear] = value;
        return true;
    }
    
    public boolean deQueue() {
        if(front == -1){
            return false;
        }
        if(front == rear){
            front = -1;
            rear = -1;
        }else{
            front = (front + 1) % q.length;
        }
        return true;
    }
    
    public int Front() {
        if(front == -1) return -1;
        return q[front];
    }
    
    public int Rear() {
        if(rear == -1) return -1;
        return q[rear];
    }
    
    public boolean isEmpty() {
        return front == -1;
    }
    
    public boolean isFull() {
        return front == (rear+1) % q.length ;
    }
}