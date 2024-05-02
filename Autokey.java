import java.util.*;
public class Autokey {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        Character c = sc.next().charAt(0);
        sc.close();

        String enc = "";
        String key = c + s.substring(0, s.length()-1);
        for(int i=0; i<s.length(); i++){
            char ch = s.charAt(i);
            char ch2 = key.charAt(i);
            enc += (char) ( ((ch-'A')+(ch2-'A'))%26 + 65);
        }
        System.out.println(enc);

        String dec = "";
        for(int i=0; i<enc.length(); i++){
            char ch = enc.charAt(i);
            char ch2 = key.charAt(i);
            int a = ((ch-'A') - (ch2 - 'A'));
            a = (a>0)?a:(26-a);
            dec += (char) (a+65);
        }
        System.out.println(dec);
    }
}
