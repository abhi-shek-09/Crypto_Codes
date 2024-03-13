import java.nio.ByteBuffer;
import java.util.Scanner;

public class sha256 {
    
    private static final int[] IV = {
        0x6A09E667, 0xBB67AE85, 0x3C6EF372, 0xA54FF53A,
        0x510E527F, 0x9B05688C, 0x1F83D9AB, 0x5BE0CD19
    };
    
    private static final int[] ROUND_CONSTANTS = {
        0x428A2F98, 0x71374491, 0xB5C0FBCF, 0xE9B5DBA5, 0x3956C25B, 0x59F111F1, 0x923F82A4, 0xAB1C5ED5,
        0xD807AA98, 0x12835B01, 0x243185BE, 0x550C7DC3, 0x72BE5D74, 0x80DEB1FE, 0x9BDC06A7, 0xC19BF174,
        0xE49B69C1, 0xEFBE4786, 0x0FC19DC6, 0x240CA1CC, 0x2DE92C6F, 0x4A7484AA, 0x5CB0A9DC, 0x76F988DA,
        0x983E5152, 0xA831C66D, 0xB00327C8, 0xBF597FC7, 0xC6E00BF3, 0xD5A79147, 0x06CA6351, 0x14292967,
        0x27B70A85, 0x2E1B2138, 0x4D2C6DFC, 0x53380D13, 0x650A7354, 0x766A0ABB, 0x81C2C92E, 0x92722C85,
        0xA2BFE8A1, 0xA81A664B, 0xC24B8B70, 0xC76C51A3, 0xD192E819, 0xD6990624, 0xF40E3585, 0x106AA070,
        0x19A4C116, 0x1E376C08, 0x2748774C, 0x34B0BCB5, 0x391C0CB3, 0x4ED8AA4A, 0x5B9CCA4F, 0x682E6FF3,
        0x748F82EE, 0x78A5636F, 0x84C87814, 0x8CC70208, 0x90BEFFFA, 0xA4506CEB, 0xBEF9A3F7, 0xC67178F2
    };
    
    private static int add32(int... args) {
        long sum = 0;
        for (int arg : args) {
            sum += (long) arg & 0xFFFFFFFFL;
        }
        return (int) (sum % (1L << 32));
    }

    private static int rightRotate32(int x, int n) {
        return (x >>> n) | (x << (32 - n));
    }

    private static int littleSigma0(int word) {
        return rightRotate32(word, 7) ^ rightRotate32(word, 18) ^ (word >>> 3);
    }

    private static int littleSigma1(int word) {
        return rightRotate32(word, 17) ^ rightRotate32(word, 19) ^ (word >>> 10);
    }

    private static int[] messageScheduleArray(byte[] block) {
        int[] w = new int[64];
        ByteBuffer byteBuffer = ByteBuffer.wrap(block);
        for (int i = 0; i < 16; i++) {
            w[i] = byteBuffer.getInt();
        }
        for (int i = 16; i < 64; i++) {
            w[i] = add32(littleSigma1(w[i - 2]), w[i - 7], littleSigma0(w[i - 15]), w[i - 16]);
        }
        return w;
    }

    private static int bigSigma1(int word) {
        return rightRotate32(word, 6) ^ rightRotate32(word, 11) ^ rightRotate32(word, 25);
    }

    private static int choice(int x, int y, int z) {
        return (x & y) ^ (~x & z);
    }

    private static int[] round(int[] state, int roundConstant, int scheduleWord) {
        int[] newState = new int[state.length];
        newState[0] = add32(state[0], bigSigma1(state[4]), choice(state[4], state[5], state[6]), roundConstant, scheduleWord);
        newState[1] = state[0];
        newState[2] = state[1];
        newState[3] = state[2];
        newState[4] = add32(state[3], newState[0]);
        newState[5] = state[4];
        newState[6] = state[5];
        newState[7] = state[6];
        return newState;
    }

    private static int[] compressBlock(int[] inputStateWords, byte[] block) {
        int[] w = messageScheduleArray(block);
        int[] stateWords = inputStateWords.clone();
        for (int roundNumber = 0; roundNumber < 64; roundNumber++) {
            stateWords = round(stateWords, ROUND_CONSTANTS[roundNumber], w[roundNumber]);
        }
        for (int i = 0; i < stateWords.length; i++) {
            stateWords[i] = add32(inputStateWords[i], stateWords[i]);
        }
        return stateWords;
    }

    private static byte[] paddingBytes(int inputLen) {
        int remainderBytes = (inputLen + 8) % 64;
        int fillerBytes = 64 - remainderBytes;
        int zeroBytes = fillerBytes - 1;
        ByteBuffer byteBuffer = ByteBuffer.allocate(fillerBytes + 8);
        byteBuffer.put((byte) 0x80);
        byteBuffer.put(new byte[zeroBytes]);
        byteBuffer.putLong(inputLen * 8L);
        return byteBuffer.array();
    }

    public static byte[] sha_256(byte[] message) {
        byte[] padded = ByteBuffer.allocate(message.length + paddingBytes(message.length).length)
                                   .put(message)
                                   .put(paddingBytes(message.length))
                                   .array();
        int[] stateWords = IV.clone();
        for (int i = 0; i < padded.length; i += 64) {
            byte[] block = new byte[64];
            System.arraycopy(padded, i, block, 0, 64);
            stateWords = compressBlock(stateWords, block);
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(stateWords.length * 4);
        for (int stateWord : stateWords) {
            byteBuffer.putInt(stateWord);
        }
        return byteBuffer.array();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        sc.close();
        byte[] message = s.getBytes();
        byte[] output = sha_256(message);
        StringBuilder hexString = new StringBuilder();
        for (byte b : output) {
            hexString.append(String.format("%02X", b));
        }
        System.out.println("Plaintext: " + s);
        System.out.println("Result: " + hexString.toString());
    }
}
