class Solution {
    public int minOperations(String s) {
        int n = s.length();

        char[] ch = s.toCharArray();
        Arrays.sort(ch);

        if(s.equals(new String(ch))) return 0;
        if(n == 2) return -1;

        int max = ch[n-1];
        int min = ch[0];

        if(s.charAt(0) == min || s.charAt(n-1) == max) return 1;

        for(int i = 1; i<n-1; i++){
            if(s.charAt(i) == max || s.charAt(i) == min) return 2;
        }
        return 3;
    }
}