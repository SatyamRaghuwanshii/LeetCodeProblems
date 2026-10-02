class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Map<Character,Integer> map = new HashMap<>();
        int res = 0;
        for(int i = 0; i < stones.length(); i++){
            char ch = stones.charAt(i);
            map.put(ch,map.getOrDefault(ch,0)+1);
        }
        for(int i = 0; i < jewels.length(); i++){
            res += map.getOrDefault(jewels.charAt(i),0);
        }
        return res;
    }
}