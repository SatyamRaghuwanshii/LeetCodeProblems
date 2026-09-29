class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for(int i = 0; i < operations.length; i++){

            if(!stack.isEmpty() && operations[i].equals("C")){
                stack.pop();
            }else if(!stack.isEmpty() && operations[i].equals("D")){
                stack.push(stack.peek()*2);
            }else if(operations[i].equals("+")){
                int first = stack.pop();
                int second = stack.peek();
                stack.push(first);
                stack.push(first + second);
            }else{
                stack.push(Integer.parseInt(operations[i]));
            }
        }
        while(!stack.isEmpty()){
            res += stack.peek();
            stack.pop();
        }
        return res;
    }
}