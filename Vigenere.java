import java.util.*;
public class Vigenere{
    public static String extend_key(String s, String key){
        if(s.length() == key.length()) return s;

        String new_key = "";
        int i = 0;
        while(i < s.length()){
            new_key += key.charAt(i%key.length());
            i+=1;
        }
        return new_key;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String key = sc.nextLine();
        sc.close();
        String new_key = extend_key(s, key);
        
        String enc_string = "";
        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = new_key.charAt(i);
            int a = (c1 - 'A') + (c2 - 'A');
            a = (a > 26)? (a%26): a;
            enc_string += (char) (a + 65);
        }
        System.out.println(enc_string);

        String dec_string = "";
        for(int i=0; i<enc_string.length(); i++){
            char c1 = enc_string.charAt(i);
            char c2 = new_key.charAt(i);
            int a = (c1 - 'A') - (c2 - 'A');
            a = (a<0)?a+26:a;
            dec_string += (char) (a + 65);
        }
        System.out.println(dec_string);
    }
}