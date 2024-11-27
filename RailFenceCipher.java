public class RailFenceCipher {
    
    // Method to encode a message using Rail Fence Cipher
    public static String encode(String msg, int depth) {
        char[] rail = new char[msg.length()];
        int row = 0;
        boolean goingDown = false;

        // Fill the message in zigzag pattern
        for (int i = 0; i < msg.length(); i++) {
            rail[row] = msg.charAt(i);
            if (row == 0 || row == depth - 1) goingDown = !goingDown;
            row += goingDown ? 1 : -1;
        }

        // Read rail and build encoded message
        StringBuilder enc = new StringBuilder();
        for (char c : rail) enc.append(c);
        return enc.toString();
    }

    // Method to decode a message using Rail Fence Cipher
    public static String decode(String encMsg, int depth) {
        char[] rail = new char[encMsg.length()];
        int row = 0;
        boolean goingDown = false;

        // Mark the positions in zigzag pattern
        for (int i = 0; i < encMsg.length(); i++) {
            rail[row] = '*';
            if (row == 0 || row == depth - 1) goingDown = !goingDown;
            row += goingDown ? 1 : -1;
        }

        // Fill the rail with encrypted message
        int k = 0;
        for (int i = 0; i < encMsg.length(); i++) {
            if (rail[i] == '*') rail[i] = encMsg.charAt(k++);
        }

        // Rebuild the decoded message from rail
        StringBuilder dec = new StringBuilder();
        row = 0;
        goingDown = false;
        for (int i = 0; i < encMsg.length(); i++) {
            dec.append(rail[i]);
            if (row == 0 || row == depth - 1) goingDown = !goingDown;
            row += goingDown ? 1 : -1;
        }
        return dec.toString();
    }

    public static void main(String[] args) {
        String msg = "HELLO";
        int depth = 3;

        // Encrypt and decrypt the message
        String enc = encode(msg, depth);
        String dec = decode(enc, depth);

        System.out.println("Encrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}
