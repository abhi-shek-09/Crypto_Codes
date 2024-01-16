import java.util.*;
public class OneTimePad {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter message: ");
        String message = sc.nextLine();
        System.out.println("Enter key: ");
        String key = sc.nextLine();
        String cipherString = "";
        sc.close();
        HashMap<Character, Integer> map = new HashMap<>();
        HashMap<Integer, Character> revmap = new HashMap<>();
        for(int i=0; i<26; i++){
            map.put((char)('A'+i), i);
            revmap.put(i, (char)('A'+i));
        }

        ArrayList<Integer> message_list = new ArrayList<>();
        for(char c: message.toCharArray()){
            message_list.add(map.get(c));
        }

        ArrayList<Integer> key_list = new ArrayList<>();
        for(char c: key.toCharArray()){
            key_list.add(map.get(c));
        }

        for(int i=0; i<message_list.size(); i++){
            message_list.set(i, (message_list.get(i) + key_list.get(i))%26);
            cipherString += revmap.get(message_list.get(i));
        }

        System.out.println("Cipher text: " + cipherString);

        String plaintext = "";
        for(int i=0; i<message_list.size(); i++){
            int n = message_list.get(i) - key_list.get(i);
            n = (n<0)? 26+n: n;
            message_list.set(i, n);
            plaintext += revmap.get(message_list.get(i));
        }
        System.out.println("Plaintext: " + plaintext);

    }
}
