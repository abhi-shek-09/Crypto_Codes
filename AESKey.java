import java.util.*;
import java.util.HashMap;
public class AESKey {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        sc.close();
        String binary_key = "";
        for(char c: key.toCharArray()){
            binary_key += '0';
            binary_key += Integer.toBinaryString(c);
        }
        HashMap<Integer, String> hex_map = new HashMap<Integer, String>();
        hex_map.put(10, "A");
        hex_map.put(11, "B");
        hex_map.put(12, "C");
        hex_map.put(13, "D");
        hex_map.put(14, "E");
        hex_map.put(15, "F");
        String[] bytes = new String[16];
        for(int i=0; i<binary_key.length(); i+=8){
            String firsthalf = binary_key.substring(i, i+4);
            String secondhalf = binary_key.substring(i+4, i+8);
            
            int a = Integer.parseInt(firsthalf, 2);
            int b = Integer.parseInt(secondhalf, 2);
            String int_a = Integer.toString(a);
            String int_b = Integer.toString(b);
            if(a>=10){
                int_a = hex_map.get(a);
            }
            if(b>=10){
                int_b = hex_map.get(b);
            }
            bytes[i/8] = int_a + int_b;
        }
        
        int index = 0;
        String[][] initial_key = new String[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                initial_key[j][i] = bytes[index++];
            }
        }

        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(initial_key[i][j]  + " ");
            }
            System.out.println();
        }
    }
}
