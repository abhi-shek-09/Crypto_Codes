import java.util.*;
public class Playfair{
    private char[][] keyTable;
    private static final int dimension_of_grid = 5;
    private static final char append = 'X';
    
    public Playfair(String key) {
       keyTable = generateKeyTable(key);
    }
    
    private char[][] generateKeyTable(String key) {
       char[][] table = new char[dimension_of_grid][dimension_of_grid];
       for (int i = 0; i < dimension_of_grid; i++) {
          for (int j = 0; j < dimension_of_grid; j++) {
             table[i][j] = ' ';
          }
       }
       int row = 0;
       int col = 0;
       boolean[] previouslyUsed = new boolean[26];
       for (int i = 0; i < key.length(); i++) {
          char ch = Character.toUpperCase(key.charAt(i));
          if (ch == 'J') {
             ch = 'I';
          }
          if (!previouslyUsed[ch - 'A']) {
             table[row][col] = ch;
             previouslyUsed[ch - 'A'] = true;
             col++;
             if (col == dimension_of_grid) {
                row++;
                col = 0;
             }
          }
       }
       for (int i = 0; i < 26; i++) {
          char ch = (char) ('A' + i);
          if (ch == 'J') {
             continue;
          }
          if (!previouslyUsed[i]) {
             table[row][col] = ch;
             col++;
             if (col == dimension_of_grid) {
                row++;
                col = 0;
             }
          }
       }
    
       return table;
    }
    public String encrypt(String plaintext) {
       plaintext = preProcess(plaintext);
       StringBuilder ciphertext = new StringBuilder();
       for (int i = 0; i < plaintext.length(); i += 2) {
          char ch1 = plaintext.charAt(i);
          char ch2 = plaintext.charAt(i + 1);
          int[] position1 = findPos(ch1);
          int[] position2 = findPos(ch2);
          
          if (position1[0] == position2[0]) {
             int newCol1 = (position1[1] + 1) % dimension_of_grid;
             int newCol2 = (position2[1] + 1) % dimension_of_grid;
             ciphertext.append(keyTable[position1[0]][newCol1]);
             ciphertext.append(keyTable[position2[0]][newCol2]);
          } else if (position1[1] == position2[1]) {
             int newRow1 = (position1[0] + 1) % dimension_of_grid;
             int newRow2 = (position2[0] + 1) % dimension_of_grid;
             ciphertext.append(keyTable[newRow1][position1[1]]);
             ciphertext.append(keyTable[newRow2][position2[1]]);
          } else {
             ciphertext.append(keyTable[position1[0]][position2[1]]);
             ciphertext.append(keyTable[position2[0]][position1[1]]);
          }
       }
       return ciphertext.toString();
    }
    public String decrypt(String ciphertext) {
       StringBuilder plaintext = new StringBuilder();
       for (int i = 0; i < ciphertext.length(); i += 2) {
          char ch1 = ciphertext.charAt(i);
          char ch2 = ciphertext.charAt(i + 1);
          int[] position1 = findPos(ch1);
          int[] position2 = findPos(ch2);
          if (position1[0] == position2[0]) {
    
             int newCol1 = (position1[1] + dimension_of_grid - 1) % dimension_of_grid;
             int newCol2 = (position2[1] + dimension_of_grid - 1) % dimension_of_grid;
             plaintext.append(keyTable[position1[0]][newCol1]);
             plaintext.append(keyTable[position2[0]][newCol2]);
          } else if (position1[1] == position2[1]) {
    
             int newRow1 = (position1[0] + dimension_of_grid - 1) % dimension_of_grid;
             int newRow2 = (position2[0] + dimension_of_grid - 1) % dimension_of_grid;
             plaintext.append(keyTable[newRow1][position1[1]]);
             plaintext.append(keyTable[newRow2][position2[1]]);
          } else {
    
             plaintext.append(keyTable[position1[0]][position2[1]]);
             plaintext.append(keyTable[position2[0]][position1[1]]);
          }
       }
       return postProcess(plaintext.toString());
    }
    private String preProcess(String text) {
       StringBuilder sb = new StringBuilder(text.toUpperCase().replaceAll("[^A-Z]", ""));
       for (int i = 1; i < sb.length(); i += 2) {
          if (sb.charAt(i) == sb.charAt(i - 1)) {
             sb.insert(i, append);
          }
       }
       if (sb.length() % 2 != 0) {
          sb.append(append);
       }
       return sb.toString();
    }
    private String postProcess(String text) {
       StringBuilder sb = new StringBuilder(text);
       for (int i = 1; i < sb.length(); i += 2) {
          if (sb.charAt(i) == append) {
             sb.deleteCharAt(i);
          }
       }
       return sb.toString().replace(append, ' ');
    }
    private int[] findPos(char ch) {
       int[] pos = new int[2];
       for (int i = 0; i < dimension_of_grid; i++) {
          for (int j = 0; j < dimension_of_grid; j++) {
             if (keyTable[i][j] == ch) {
                pos[0] = i;
                pos[1] = j;
                return pos;
             }
          }
       }
       return null;
    }
    
    public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      String plaintext = sc.nextLine();
      String key = sc.nextLine();
      sc.close();
      Playfair cipher = new Playfair(key);
      String ciphertext = cipher.encrypt(plaintext);
      System.out.println("Plaintext: " + plaintext);
      System.out.println("Ciphertext: " + ciphertext);
      System.out.println("Decrypted text: " + cipher.decrypt(ciphertext));
    }
 }