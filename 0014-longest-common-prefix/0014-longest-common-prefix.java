class Solution {
    public String longestCommonPrefix(String[] strs) {
        String common  = strs[0];
        for(int i = 1; i<strs.length; i++){
            int j = 0;
            while(j<common.length() &&
                j<strs[i].length() &&
                common.charAt(j) == strs[i].charAt(j)){
                j++;
            }
            common = common.substring(0,j);
            if(common.isEmpty()){
                break;
            }
        }
        return common;
    }
}