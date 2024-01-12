import java.util.*;
public class Playfair {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String key = sc.nextLine();
        String plaintext = sc.nextLine();
        Character[][] keytable = new Character[5][5];
        HashSet<Character> keyset = new HashSet<>();
        

        int index = 0;
        int i, j;
        int copy_i = 0, copy_j=0;
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                if (index < key.length() && !keyset.contains(key.charAt(index))) {
                    keytable[i][j] = key.charAt(index);
                    keyset.add(key.charAt(index));
                    index++;
                    copy_i = i;
                    copy_j = j;
                }
                else if(index < key.length() && keyset.contains(key.charAt(index))){
                    copy_i = i;
                    copy_j = j;
                    continue;
                }
                else{
                    break;
                } 
            }
        }
        int a = 97;
        for (i = copy_i; i < 5; i++) {
            for (j = (i == copy_i) ? copy_j : 0; j < 5; j++) {
                while (keyset.contains((char) (a))) {
                    a += 1;
                }
                keytable[i][j] = (char) (a);
                keyset.add((char) (a));
                a += 1;
            }
            // Reset copy_j to 0 for the next row
            copy_j = 0;
        }       
        
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 5; j++) {
                System.out.print(keytable[i][j]);
            }
            System.out.println();
        } 


        System.out.println(plaintext);
        sc.close();
    }
}
