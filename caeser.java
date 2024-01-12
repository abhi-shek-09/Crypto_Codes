import java.util.*;
class caeser{
    public static String encrypt(String s, int key){
        String new_s = "";
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                int res = c + key;
                if (res > 122) {
                    res = (res % 122) + 96; 
                }
                new_s += (char) (res);
            } else {
                new_s += c; 
            }
        }
        return new_s;
    }

    public static String decrypt(String s, int key){
        String new_s = "";
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) {
                int res = c - key;
                if (res < 97) {
                    res = 122 - (97-res)+1; 
                }
                new_s += (char) (res);
            } else {
                new_s += c; 
            }
        }
        return new_s;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter shift or key: ");
        int key = sc.nextInt();
        System.out.println("Enter plain text: ");
        String s = sc.next().toLowerCase();
        String encrypted_str = encrypt(s, key);
        System.out.println("Cipher text is: " + encrypted_str);
        String decrypted_str = decrypt(encrypted_str, key);
        System.out.println("Decrypted Cipher text: " + decrypted_str);
        sc.close();
    }
}