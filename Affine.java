import java.util.*;

public class Affine{
    public static int inverse(int a){
        // ax mod n = 1
        int i;
        for(i=1; i<26; i++){
            if(a*i%26 == 1) break;
        }
        return i;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int a = sc.nextInt();
        int b = sc.nextInt();
        sc.close();

        String enc = "";

        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            int n = (a*(c-'A') + b)%26;
            enc += (char)(n+65);
        }
        int inv = inverse(a);
        String dec = "";
        for(int i=0; i<enc.length(); i++){
            char c = enc.charAt(i);
            int n = (inv * ((c-'A') - b))%26;
            n = (n>0)?n: 26+n;
            dec += (char)(n+65);
        }
        System.out.println(dec);
    }
}