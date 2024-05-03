public class DESsbox {
    public static String Sbox(String ip, int[][] sbox){
        String final_string = "";
        for(int i=0; i<ip.length(); i+=6){
            String sub = ip.substring(i, i+6);
            String rowString = sub.substring(0, 1) + sub.substring(5, 6);
            String colString = sub.substring(1, 5);
            int row = Integer.parseInt(rowString, 2);
            int col = Integer.parseInt(colString, 2);
            int num = sbox[row][col];
            if(num == 0){
                final_string += "0000";
            }
            else if(num == 1){
                final_string += "000" + Integer.toBinaryString(num);
            }
            else if(num>1 && num<=3){
                final_string += "00" + Integer.toBinaryString(num);
            }
            else if(num>=4 && num<=7){
                final_string += "0" + Integer.toBinaryString(num);
            }
            else{
                final_string += Integer.toBinaryString(num);
            }
        }
        return final_string;
    }
    public static void main(String[] args) {
        String ip_string = "111000001011111001101110101001000001100101100000";
        int[][] s1 = {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        };
        String s2 = Sbox(ip_string, s1);
        System.out.println(s2.length());
        // like this make 7 more s'i' tables and pass it through a function
    }   
}





