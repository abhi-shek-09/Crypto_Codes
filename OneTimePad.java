import java.util.*;
public class OneTimePad{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String key = sc.nextLine();
        sc.close();
        String encrypted_string = "";
        for(int i=0; i<s.length(); i++){
            char c1 = s.charAt(i);
            char c2 = key.charAt(i);
            if(c1 == ' ' && c2 == ' '){
                encrypted_string += ' ';
            }
            else{
                encrypted_string += (char) ((((c1 - 'A') + (c2 - 'A'))%26) + 65);
            }
        }

        System.out.println(encrypted_string);


        String decrypted_string = "";
        for(int i=0; i<encrypted_string.length(); i++){
            char c1 = encrypted_string.charAt(i);
            char c2 = key.charAt(i);
            if(c1 == ' ' && c2 == ' '){
                decrypted_string += ' ';
            }
            else{
                int a = ((c1 - 'A') - (c2 - 'A'));
                a = (a>0)? a: 26+a;
                decrypted_string += (char) ((a%26) + 65);
            }
        }
        System.out.println(decrypted_string);

    }
}