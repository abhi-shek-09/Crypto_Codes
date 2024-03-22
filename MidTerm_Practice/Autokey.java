import java.util.*;
public class Autokey {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pt = sc.nextLine();
        String key1 = sc.nextLine();
        sc.close();
        int diff_len = key1.length();
        String final_key = key1 + pt.substring(0, pt.length() - diff_len);
        String encrypted_String = "";
        for(int i=0; i<pt.length(); i++){
            char ch = pt.charAt(i);
            char c = final_key.charAt(i);
            encrypted_String += (char)(((c+ch)%26 + 'A'));
        }
        System.out.println(encrypted_String);
    }
}
