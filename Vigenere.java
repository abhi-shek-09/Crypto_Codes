import java.util.*;
public class Vigenere {
    public static String extendKeyword(String plaintext,String keyword){
        if(plaintext.length() == keyword.length()) return keyword;

        String key = "";
        for(int i=0; i<plaintext.length(); i++){
            key += keyword.charAt(i%keyword.length());
        }
        return key;
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String plaintext = sc.nextLine();
        String keyword = sc.nextLine();
        sc.close();
        String key = extendKeyword(plaintext, keyword);
        System.out.println(key);

        String ciphertext = "";
        for(int i=0; i<plaintext.length(); i++){
            int total = (int)(plaintext.charAt(i) + key.charAt(i));
            ciphertext += (char)(total%26 + 65);
        }
        System.out.print(ciphertext);
    }
}
