import java.util.*;
class caeser{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter key");
        int key = sc.nextInt();
        System.out.println("Enter plain text");
        String s = sc.next().toLowerCase();
        String new_s = "";
        for(char c: s.toCharArray()){
            int res = c+key;
            if(res>122){
                res = res%122+97;
            }
            new_s += (char)(res);
            
        }
        System.out.println(new_s);
        sc.close();
    }
}