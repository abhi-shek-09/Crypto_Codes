import java.util.HashMap;
import java.util.Scanner;
public class PC1{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] pctable = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27, 
            19, 11, 3, 60, 52, 44, 36, 
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 21, 4
        };
        HashMap<String, String> map = new HashMap<>();
        map.put("0", "0000");
        map.put("1", "0001");
        map.put("2", "0010");
        map.put("3", "0011");
        map.put("4", "0100");
        map.put("5", "0101");
        map.put("6", "0110");
        map.put("7", "0111");
        map.put("8", "1000");
        map.put("9", "1001");
        map.put("A", "1010");
        map.put("B", "1011");
        map.put("C", "1100");
        map.put("D", "1101");
        map.put("E", "1110");
        map.put("F", "1111");
        String inp = sc.nextLine();
        sc.close();
        String sec_inp = "";
        for(char c: inp.toCharArray()){
            String temp = "" + c;
            sec_inp += map.get(temp);
        }
        System.out.println(sec_inp);

        String final_op = "";
        for(int i=0; i<pctable.length; i++){
            final_op += sec_inp.charAt(pctable[i]-1);
        }
        System.out.println(final_op);
    }
}