import java.util.*;
public class PC2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        sc.close();
        String inp_1 = inp.substring(0, inp.length()/2);
        String inp_2 = inp.substring(inp.length()/2, inp.length());
        
        String s1 = inp_1.substring(1, inp_1.length()) + inp_1.charAt(0);
        String s2 = inp_2.substring(1, inp_2.length()) + inp_2.charAt(0);
        s1 += s2;

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
        String op_string = "";
        for(int i=0; i<pc2_table.length; i++){
            op_string += s1.charAt(pc2_table[i]-1);
        }
        System.out.println(op_string);
    }
}
