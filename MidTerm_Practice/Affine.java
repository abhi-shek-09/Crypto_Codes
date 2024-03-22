import java.util.*;
public class Affine{
    public static int gcd(int a, int b){
        if(b == 0){
            return a;
        }
        return gcd(b, a%b);
    }
    public static int inverse(int a){
        int i = 0;
        for(i=1; i<26; i++){
            if((a*i)%26 == 1){
                break;
            }
        }
        return i;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();
        String encrypted_string = "";
        if(gcd(a, b) == 1){
            for(char c: s.toCharArray()){
                if(c>=65 && c<=90){
                    int new_val = (a*(c-'A') + b)%26;
                    char new_char = (char)(new_val + 'A');
                    encrypted_string += new_char;
                }
                else{
                    encrypted_string += c;
                }
            }

            System.out.println("Encrypted: " + encrypted_string);
            int a_inverse = inverse(a);

            String decrypted_string = "";
            for(char c: encrypted_string.toCharArray()){
                if(c>=65 && c<=90){
                    int val = (a_inverse*((c-'A')-b))%26 + 26;
                    decrypted_string += (char)(val + 'A');
                }
                else{
                    decrypted_string += c;
                }
            }
            System.out.println("Decrypted: " + decrypted_string);
        }
        else{
            System.out.println("Invalid values of a and b");
        }
        
        

    }
}