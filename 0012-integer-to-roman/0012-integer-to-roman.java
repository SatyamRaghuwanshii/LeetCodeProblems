class Solution {
    public String intToRoman(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
        StringBuilder sb = new StringBuilder();
        int count = 1;
        int temp = num;
        while(temp >= 10) {
            temp /= 10;
            count *= 10;
        }
        while(count != 0){
            int digit = (num/count) % 10;
            if(digit == 9){
                sb.append(map.get(9*count));
            }else if(digit == 4){
                sb.append(map.get(4*count));
            }else if(digit >= 5){
                sb.append(map.get(5*count));
                for(int i = 0; i < digit-5; i++){
                    sb.append(map.get(count));
                }
            }else{
                for(int i = 0; i < digit; i++){
                    sb.append(map.get(count));
                }
            }
            count /= 10;
        }
        return sb.toString();
    }
}