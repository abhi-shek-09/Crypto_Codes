public class DESfFunction {
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

    public static String xorr(String s1, String s2){
        String xorString = "";
        for(int i=0; i<s1.length(); i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            if(c1 == c2) xorString+= '0';
            else xorString += '1';
        }
        return xorString;
    }
    public static void main(String[] args) {
        String pt = "satishcj";
        
        String binpt = "";
        for(char c:pt.toCharArray()){
            binpt += '0' + Integer.toBinaryString(c);
        }
        // System.out.println(binpt.length());

        String lpt = binpt.substring(0, binpt.length()/2);
        String rpt = binpt.substring(binpt.length()/2, binpt.length());

        int[] expansion_permutation = {
            32, 1, 2, 3, 4, 5,
            4, 5, 6, 7, 8, 9,
            8, 9, 10, 11, 12, 13,
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21,
            20, 21, 22, 23, 24, 25,
            24, 25, 26, 27, 28, 29,
            28, 29, 31, 31, 32, 1
        };

        String exp_str = "";
        for(int i=0; i<expansion_permutation.length; i++){
            exp_str += rpt.charAt(expansion_permutation[i]-1);
        }
        // System.out.println(exp_str);

        int[][] sbox1 = {
            {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
            {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
            {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
            {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
        };
        String sbox_str = Sbox(exp_str, sbox1);
        
        // Assuming the string coming out the permutation boxes is 111000001011111001101110001100000001110110100000
        String permuString = "111000001011111001101110001100000001110110100000";
        String xorr_1String = xorr(sbox_str, permuString);
        // System.out.println(xorr_1String.length());

        int[] final_permutation = {16,7,20,21,29,12,28,17,1,15,23,26,5,18,31,10,2,8,23,14,32,27,3,9,19,13,30,6,22,11,4,25};
        String final_permuString = "";
        for(int i=0; i<final_permutation.length; i++){
            final_permuString += xorr_1String.charAt(final_permutation[i]-1);
        }
        // System.out.println(final_permuString);
        // System.out.println(final_permuString.length());

        System.out.println(binpt);
        System.out.println("Left string: " + rpt);
        System.out.println("Right string: " + xorr(lpt, final_permuString));

    }
}
