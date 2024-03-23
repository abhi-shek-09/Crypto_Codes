import java.util.*;
public class Vernam {
    public static int xorString(String s1, String s2){
        
        return 0;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pt = sc.nextLine();
        String key = sc.nextLine();
        sc.close();
        String encrypted_string = "";
        for(int i=0; i<key.length(); i++){
            char cpt = pt.charAt(i);
            char ckey = key.charAt(i);
            int val = ((cpt - 'A') ^ (ckey - 'A'));
            val = val>26? val-26:val;
            encrypted_string += (char)(val%26 + 'A');
        }
        System.out.println(encrypted_string);

        String decrypted_string = "";
        for(int i = 0; i < encrypted_string.length(); i++){
            char ces = encrypted_string.charAt(i);
            char ckey = key.charAt(i);
            int val = ((ces - 'A') ^ (ckey - 'A'));
            val = val<0? val+26:val;
            decrypted_string += (char)(val%26+ 'A');
        }

        System.out.println(decrypted_string);
    }
}
