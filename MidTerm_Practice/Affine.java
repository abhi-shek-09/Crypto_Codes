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
            enc += (char) (((a*(c-'A') + b)%26) + 65);
        }
        System.out.println(enc);

        int inv = -1;
        if(inverse(a) == -1){
            System.out.println("No inverse");
        }
        else{
            inv = inverse(a);
        }
        String dec = "";
        for(int i=0; i<enc.length(); i++){
            char c = enc.charAt(i);
            int l = ((inv * ( (c-'A')-b ))%26);
            l = (l<0)?(26+l):l;
            dec += (char) (l+65);
        }
        System.out.println(dec);
    }
}