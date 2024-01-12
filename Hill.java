import java.util.*;
public class Hill {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Integer, Character> revMap = new HashMap<>();
        for(int i=0; i<26; i+=1){
            map.put((char)('A'+i), i);
            revMap.put(i, (char)('A'+i));
        }
        String plaintext = sc.nextLine();
        String key = sc.nextLine();
        sc.close();
        ArrayList<Integer> plaintext_numbers = new ArrayList<>();
        ArrayList<Integer> key_numbers = new ArrayList<>();

        for(char c: plaintext.toCharArray()){
            plaintext_numbers.add(map.get(c));
        }

        for(char c: key.toCharArray()){
            key_numbers.add(map.get(c));
        }

        int rows = plaintext_numbers.size(); 
        int cols = rows; 
        int[][] keyArray = new int[rows][cols];
        int index = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                keyArray[i][j] = key_numbers.get(index++);
            }
        }

        int[][] plaintextArray = new int[plaintext_numbers.size()][1];
        for(int i = 0; i < plaintext_numbers.size(); i++) {
            plaintextArray[i][0] = plaintext_numbers.get(i);
        }

        int C[][] = new int[rows][1]; 
        for (int i = 0; i < rows; i++) { 
            for (int j = 0; j < 1; j++) { 
                for (int k = 0; k < plaintext_numbers.size(); k++) 
                    C[i][j] += keyArray[i][k] * plaintextArray[k][j]; 
            } 
        } 
        String ciphertext = "";
        for(int i = 0; i < C.length; i++) { 
            for(int j = 0; j < C[i].length; j++) 
                ciphertext += revMap.get(C[i][j]%26);
        } 
        System.out.println(ciphertext);
    }
}
