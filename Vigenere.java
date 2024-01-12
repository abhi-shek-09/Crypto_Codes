import java.util.*;
public class Vigenere {
    public static String extendKeyword(String plaintext, String keyword) {
        if (plaintext.length() == keyword.length()) return keyword;

        StringBuilder key = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            key.append(keyword.charAt(i % keyword.length()));
        }
        return key.toString();
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Plaintext: ");
        String plaintext = sc.nextLine();
        System.out.println("Enter key: ");
        String keyword = sc.nextLine();
        sc.close();

        String key = extendKeyword(plaintext, keyword);
        System.out.println("Extended Key: " + key);

        String ciphertext = encrypt(plaintext, key);
        System.out.println("Cipher text: " + ciphertext);

        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);
    }

    public static String encrypt(String plaintext, String key) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            int total = (int) (plaintext.charAt(i) + key.charAt(i));
            ciphertext.append((char) (total % 26 + 'A'));
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i++) {
            int total = (int) (ciphertext.charAt(i) - key.charAt(i) + 26);
            decryptedText.append((char) (total % 26 + 'A'));
        }
        return decryptedText.toString();
    }
}
