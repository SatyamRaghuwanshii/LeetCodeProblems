class Solution {
    public boolean wordPattern(String pattern, String s) {
        HashMap<Character,String> map = new HashMap<>();
        HashMap<String, Character> revMap = new HashMap<>();
        String[] arr = s.split(" ");
        if(pattern.length() != arr.length) return false;
        for(int i = 0; i<arr.length; i++){
            char ch = pattern.charAt(i);
            String st = arr[i];
            if(revMap.containsKey(st) && !revMap.get(st).equals(ch)) return false;
            if(map.containsKey(ch) && !map.get(ch).equals(st)) return false;
            map.put(ch,st);
            revMap.put(st,ch);
        }
        return true;
    }
}