class Solution {
    public String reverseWords(String s) {
        String[] arr = s.split(" ");
        for(int i = 0; i< arr.length; i++){
            String current = arr[i];
            StringBuilder sb = new StringBuilder(current);
            arr[i] = sb.reverse().toString();
        }
        s = String.join(" ",arr);
        return s;
    }
}