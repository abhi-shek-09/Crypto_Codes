import java.util.*;
import java.util.HashMap;
public class AESKey {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        String binkey = "";
        for(int i=0; i<s.length(); i++){
            binkey += '0' + Integer.toBinaryString(s.charAt(i));
        }
        System.out.println(binkey);

        HashMap<Integer, String> map = new HashMap<>();
        map.put(10, "A");
        map.put(11, "B");
        map.put(12, "C");
        map.put(13, "D");
        map.put(14, "E");
        map.put(15, "F");
        String[] st_list = new String[16];
        for(int i=0; i<binkey.length(); i+=8){
            String p1 = binkey.substring(i, i+4);
            String p2 = binkey.substring(i+4, i+8);

            String num = "";
            int p1_ = Integer.parseInt(p1, 2);
            int p2_ = Integer.parseInt(p2, 2);
            System.out.println(p1_ + " " + p2_);
            if(p1_ >= 10)
            num += map.get(p1_);
            else
            num+= Integer.toString(p1_);

            if(p2_ >= 10)
            num += map.get(p2_);
            else
            num+= Integer.toString(p2_);

            st_list[i/8] = num;
        }
        String[][] st_lists = new String[4][4];
        int index = 0;
        for(int i=0; i<4; i++){
            for(int j =0; j<4; j++){
                st_lists[j][i] = st_list[index++];
            }
        }

        for(int i=0; i<4; i++){
            for(int j =0; j<4; j++){
                System.out.print(st_lists[i][j] + " ");
            }
            System.out.println();
        }
    }
}
