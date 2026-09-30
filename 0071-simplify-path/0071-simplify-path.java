class Solution {
    public String simplifyPath(String path) {
        Stack<String> st = new Stack<>();
        for(String dic : path.split("/")){
           if(dic.equals("..")){
            if(!st.isEmpty()){
                st.pop();
            }
           }else if(dic.equals(".") || dic.equals("")){
            continue;
           }else{
            st.push(dic);
           }
        }
        return "/" + String.join("/",st);
    }
}