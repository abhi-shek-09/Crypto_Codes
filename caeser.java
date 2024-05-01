import java.util.*;
public class caeser{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int key = sc.nextInt();
        sc.close();

        String encrypted_string = "";
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            char ch = (c + key)>90? (char)((c + key)%91 + 65) : (char)((c + key));
            encrypted_string += ch;
        }
        System.out.println(encrypted_string);

        String decrypted_string = "";
        for(int i=0; i<encrypted_string.length(); i++){
            char c = encrypted_string.charAt(i);
            char ch = (c - key)<65? (char)((90 - (64 - (c - key)))) : (char)(c - key);
            decrypted_string += ch;
        }
        System.out.println(decrypted_string);

    }
}