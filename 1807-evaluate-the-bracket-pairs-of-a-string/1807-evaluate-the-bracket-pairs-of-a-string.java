class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String,String> map = new HashMap<>();
        StringBuilder res = new StringBuilder();
        for(int  i = 0; i < knowledge.size(); i++){
            map.put(knowledge.get(i).get(0), knowledge.get(i).get(1));
        }
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(ch == '('){
                StringBuilder sb = new StringBuilder();
                i++;
                while(s.charAt(i) != ')'){
                    sb.append(s.charAt(i));
                    i++;
                }
                String st = sb.toString();
                if(map.containsKey(st)){
                    res.append(map.get(st));
                }else{
                    res.append("?");
                }
            }else{
               res.append(ch); 
            }
            
        }
        return res.toString();
    }
}