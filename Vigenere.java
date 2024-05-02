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
        
        String enc = "";
        for(int i=0; i<new_key.length(); i++){
            char c1 = new_key.charAt(i);
            char c2 = s.charAt(i);
            enc += (char)(((c1-'A') + (c2-'A'))%26 + 65);
        }
        System.out.println(enc);

        String dec = "";
        for(int i=0; i<new_key.length(); i++){
            char c1 = new_key.charAt(i);
            char c2 = enc.charAt(i);
            int a = ((c2-'A') - (c1-'A'));
            a = (a>0)?a:(26+a);
            dec += (char)(a+65);
            // dec += 
        }
        System.out.println(dec);
    }
}