class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        Map<Character,Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            char ch = s1.charAt(i);
            map.put(ch, map.getOrDefault(ch,0) + 1);
        }
        Map<Character,Integer> map2 = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            char ch = s2.charAt(i);
            if(map.containsKey(ch)){
              map2.put(ch, map2.getOrDefault(ch,0) + 1);  
            }
        }
        if(map.equals(map2)){
            return true;
        }
        for(int i = 1; i < s2.length()-s1.length()+1; i++){
            char ch1 = s2.charAt(i-1);
            if(map2.containsKey(ch1)){
                map2.put(ch1, map2.get(ch1)-1);
                if(map2.get(ch1) == 0){
                    map2.remove(ch1);
                }
            }
            char ch = s2.charAt(i+s1.length()-1);
            if(map.containsKey(ch)){
                map2.put(ch, map2.getOrDefault(ch,0)+1);
            }
            if(map.equals(map2)) return true;
        }
        return false;
    }
}