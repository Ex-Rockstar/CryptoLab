public class VigenereCipher {
    // Method to encode the text using the Vigenère Cipher
    static String encode(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        String keyUpper = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') {
                res += c; // Preserve non-alphabet characters
                continue;
            }
            res += (char) ((c + keyUpper.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % keyUpper.length();
        }
        return res;
    }

    // Method to decode the text using the Vigenère Cipher
    static String decode(String text, final String key) {
        String res = "";
        text = text.toUpperCase();
        String keyUpper = key.toUpperCase();

        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') {
                res += c; // Preserve non-alphabet characters
                continue;
            }
            res += (char) ((c - keyUpper.charAt(j) + 26) % 26 + 'A');
            j = ++j % keyUpper.length();
        }
        return res;
    }

    public static void main(String[] args) throws java.lang.Exception {
        String key = "VIGENERECIPHER";
        String msg = "Security Laboratory";

        System.out.println("Simulating Vigenère Cipher");
        System.out.println("--------------------------");
        System.out.println("Input Message : " + msg);

        // Remove spaces and encode the message
        String msgNoSpaces = msg.replaceAll("\\s", "");
        String enc = encode(msgNoSpaces, key);
        System.out.println("Encrypted Message : " + enc);

        // Decode the encrypted message
        String dec = decode(enc, key);
        System.out.println("Decrypted Message : " + dec);
    }
}
