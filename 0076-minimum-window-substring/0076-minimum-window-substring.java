class Solution {
    public String minWindow(String s, String t) {
        Map<Character,Integer> map = new HashMap<>();
        int have = 0;
        String res = "";
        for(int i = 0; i<t.length(); i++){
            char ch = t.charAt(i);
            map.put(ch, map.getOrDefault(ch,0)+1);
        }
        int need = map.size();
        int i = 0;
        int j = 0;
        int len = Integer.MAX_VALUE;
        while(j<s.length()){
            char ch = s.charAt(j);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch)-1);
                if(map.get(ch) == 0){
                    have++;
                }
            }
            while(have == need){
                if(j-i+1 < len){
                    len = j-i+1;
                    res = s.substring(i,j+1);
                }
                char ch2 = s.charAt(i);
                if(map.containsKey(ch2)){
                    map.put(ch2, map.get(ch2)+1);
                    if(map.get(ch2) == 1){
                        have--;
                    }
                }
                i++;
            }
            j++;
        }
        return res;
    }
}