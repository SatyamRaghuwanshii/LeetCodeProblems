class StockSpanner {
    class Pair{
        int price;
        int span;
        Pair(int price, int span){
            this.price = price;
            this.span = span;
        }
    }
    Deque<Pair> st;
    public StockSpanner() {
        st = new ArrayDeque<>();
    }
    
    public int next(int price) {
        int span = 1;
        while(!st.isEmpty() && st.peek().price <= price){
            span += st.pop().span;
        }
        st.push(new Pair(price,span));
        return span;
    }
}