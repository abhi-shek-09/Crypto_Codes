import java.util.*;
public class DESPermutationBothKeys {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        sc.close();
        String bin_key = "";
        for(char c: key.toCharArray()){
            bin_key += '0';
            bin_key += Integer.toBinaryString(c);
        }
        System.out.println("Key: " + bin_key);
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
        String pc_key = "";
        for(int i=0; i<pctable.length; i++){
            pc_key += bin_key.charAt(pctable[i]-1);
        }

        System.out.println("PC key: " + pc_key);
        String left_String = pc_key.substring(0, 28);
        String right_String = pc_key.substring(28, pc_key.length());
        left_String = left_String.substring(1, left_String.length()) + left_String.charAt(0);
        right_String = right_String.substring(1, right_String.length()) + right_String.charAt(0);
        String shifted_key = left_String + right_String;
        
        int[] pc2_table = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 39, 32
        };
        String pc2_key = "";
        for(int i=0; i<pc2_table.length; i++){
            pc2_key += shifted_key.charAt(pc2_table[i]-1);
        }

        System.out.println("PC2 key: " + pc2_key);
        System.out.println(bin_key.length() + " " + pc_key.length() + " " + pc2_key.length());

    }
}