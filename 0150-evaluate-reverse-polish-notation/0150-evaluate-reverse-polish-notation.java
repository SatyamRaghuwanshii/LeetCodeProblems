class Solution {
    public int evalRPN(String[] tokens) {
        int[] num = new int[tokens.length];
        int top = -1;
        for(String ch: tokens){
            if(ch.equals("/") || ch.equals("-") || ch.equals("+") || ch.equals("*")){
                int num2 = num[top--];
                int num1 = num[top];
                int cal = 0;
                switch(ch){
                    case "+" :
                        cal = num1 + num2;
                        break;
                    case "-" :
                        cal = num1 - num2;
                        break;
                    case "*" :
                        cal = num1 * num2;
                        break;
                    default:
                        cal = num1 / num2;
                }
                num[top] = cal;
            }else{
                num[++top] = Integer.parseInt(ch);
            }
        }
        return num[0];
    }
}