public class Playfair{
    private char[][] keyTable;
    private static final int grid_dimension = 5;
    private static final char APPEND = 'X';
    
    public Playfair(String key) {
       keyTable = generateKeyTable(key);
    }
    
    private char[][] generateKeyTable(String key) {
       // Initialize the key table with all ' ' characters
       char[][] table = new char[grid_dimension][grid_dimension];
       for (int i = 0; i < grid_dimension; i++) {
          for (int j = 0; j < grid_dimension; j++) {
             table[i][j] = ' ';
          }
       }
    
       // Fill the key table with the letters of the key
       int row = 0;
       int col = 0;
       boolean[] used = new boolean[26];
       for (int i = 0; i < key.length(); i++) {
          char ch = Character.toUpperCase(key.charAt(i));
          if (ch == 'J') {
             ch = 'I';
          }
          if (!used[ch - 'A']) {
             table[row][col] = ch;
             used[ch - 'A'] = true;
             col++;
             if (col == grid_dimension) {
                row++;
                col = 0;
             }
          }
       }
       // Fill the remaining cells of the key table with the remaining letters of the alphabet
       for (int i = 0; i < 26; i++) {
          char ch = (char) ('A' + i);
          if (ch == 'J') {
             continue;
          }
          if (!used[i]) {
             table[row][col] = ch;
             col++;
             if (col == grid_dimension) {
                row++;
                col = 0;
             }
          }
       }
    
       return table;
    }
    public String encrypt(String plaintext) {
       plaintext = preprocess(plaintext);
       StringBuilder ciphertext = new StringBuilder();
       for (int i = 0; i < plaintext.length(); i += 2) {
          char ch1 = plaintext.charAt(i);
          char ch2 = plaintext.charAt(i + 1);
          int[] position1 = findPosition(ch1);
          int[] position2 = findPosition(ch2);
          
          if (position1[0] == position2[0]) {
             // When letters exist in same row
             int newCol1 = (position1[1] + 1) % grid_dimension;
             int newCol2 = (position2[1] + 1) % grid_dimension;
             ciphertext.append(keyTable[position1[0]][newCol1]);
             ciphertext.append(keyTable[position2[0]][newCol2]);
          } else if (position1[1] == position2[1]) {
             //  When letters exist in same column
             int newRow1 = (position1[0] + 1) % grid_dimension;
             int newRow2 = (position2[0] + 1) % grid_dimension;
             ciphertext.append(keyTable[newRow1][position1[1]]);
             ciphertext.append(keyTable[newRow2][position2[1]]);
          } else {
             //  When letters are not in the same column or in the same row
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
          int[] position1 = findPosition(ch1);
          int[] position2 = findPosition(ch2);
          if (position1[0] == position2[0]) {
    
             int newCol1 = (position1[1] + grid_dimension - 1) % grid_dimension;
             int newCol2 = (position2[1] + grid_dimension - 1) % grid_dimension;
             plaintext.append(keyTable[position1[0]][newCol1]);
             plaintext.append(keyTable[position2[0]][newCol2]);
          } else if (position1[1] == position2[1]) {
    
             int newRow1 = (position1[0] + grid_dimension - 1) % grid_dimension;
             int newRow2 = (position2[0] + grid_dimension - 1) % grid_dimension;
             plaintext.append(keyTable[newRow1][position1[1]]);
             plaintext.append(keyTable[newRow2][position2[1]]);
          } else {
    
             plaintext.append(keyTable[position1[0]][position2[1]]);
             plaintext.append(keyTable[position2[0]][position1[1]]);
          }
       }
       return postprocess(plaintext.toString());
    }
    private String preprocess(String text) {
       // Replace J with I and add padding if needed
       StringBuilder sb = new StringBuilder(text.toUpperCase().replaceAll("[^A-Z]", ""));
       for (int i = 1; i < sb.length(); i += 2) {
          if (sb.charAt(i) == sb.charAt(i - 1)) {
             sb.insert(i, APPEND);
          }
       }
       if (sb.length() % 2 != 0) {
          sb.append(APPEND);
       }
       return sb.toString();
    }
    private String postprocess(String text) {
       // Remove padding and replace X with the original character
       StringBuilder sb = new StringBuilder(text);
       for (int i = 1; i < sb.length(); i += 2) {
          if (sb.charAt(i) == APPEND) {
             sb.deleteCharAt(i);
          }
       }
       return sb.toString().replace(APPEND, ' ');
    }
    private int[] findPosition(char ch) {
       int[] pos = new int[2];
       for (int i = 0; i < grid_dimension; i++) {
          for (int j = 0; j < grid_dimension; j++) {
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
       String plaintext = "INSTRUMENTS";
       String key = "MONARCHY";
       Playfair cipher = new Playfair(key);
       String ciphertext = cipher.encrypt(plaintext);
       System.out.println("Plaintext: " + plaintext);
       System.out.println("Ciphertext: " + ciphertext);
       System.out.println("Decrypted text: " + cipher.decrypt(ciphertext));
    }
 }