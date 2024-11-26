class RailFenceCipherHelper {
    // Method to encode a message using Rail Fence Cipher
    String encode(String msg, int depth) throws Exception {
        int r = depth;
        int l = msg.length();
        int c = (int) Math.ceil((double) l / depth); // Calculate columns dynamically
        int k = 0;

        // Initialize a character matrix
        char[][] mat = new char[r][c];
        String enc = "";

        // Fill the matrix in row-major order
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                if (k < l) {
                    mat[j][i] = msg.charAt(k++);
                } else {
                    mat[j][i] = 'X'; // Padding if needed
                }
            }
        }

        // Read the matrix in column-major order for encoding
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                enc += mat[i][j];
            }
        }

        return enc;
    }

    // Method to decode a message using Rail Fence Cipher
    String decode(String encMsg, int depth) throws Exception {
        int r = depth;
        int l = encMsg.length();
        int c = (int) Math.ceil((double) l / depth); // Calculate columns dynamically
        int k = 0;

        // Initialize a character matrix
        char[][] mat = new char[r][c];
        String dec = "";

        // Fill the matrix in row-major order with the encrypted message
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                mat[i][j] = encMsg.charAt(k++);
            }
        }

        // Read the matrix in column-major order for decoding
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < r; j++) {
                dec += mat[j][i];
            }
        }

        return dec;
    }
}

public class RailFenceCipher {
    public static void main(String[] args) throws Exception {
        RailFenceCipherHelper rf = new RailFenceCipherHelper();
        String msg = "AnnaUniversity,Chennai";
        int depth = 3;

        // Simulate Rail Fence Cipher
        System.out.println("Simulating Rail Fence Cipher");
        System.out.println("----------------------------");
        System.out.println("Input Message : " + msg);

        // Encrypt the message
        String enc = rf.encode(msg, depth);
        System.out.println("Encrypted Message : " + enc);

        // Decrypt the message
        String dec = rf.decode(enc, depth);
        System.out.println("Decrypted Message : " + dec);
    }
}
