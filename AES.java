import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

public class AES {
    private static SecretKeySpec secretKey;

    // Set the key for encryption and decryption
    public static void setKey(String myKey) {
        try {
            byte[] key = myKey.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = java.util.Arrays.copyOf(key, 16); // Use only the first 16 bytes (128 bits)
            secretKey = new SecretKeySpec(key, "AES");
        } catch (Exception e) {
            throw new RuntimeException("Error setting up the key: " + e.getMessage());
        }
    }

    // Encrypt a string
    public static byte[] encrypt(String data, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data.getBytes("UTF-8")); // Return the encrypted byte array
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting: " + e.getMessage());
        }
    }

    // Decrypt a byte array
    public static String decrypt(byte[] data, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(data); // Decrypt the byte array
            return new String(decryptedBytes, "UTF-8"); // Convert decrypted bytes back to a string
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        final String secretKey = "annaUniversity";
        String original = "www.annauniv.edu";

        // Encrypt the message
        byte[] encrypted = encrypt(original, secretKey);

        // Decrypt the message
        String decrypted = decrypt(encrypted, secretKey);

        System.out.println("URL Encryption Using AES Algorithm\n-------------");
        System.out.println("Original URL: " + original);
        System.out.println("Encrypted (in byte format): " + java.util.Arrays.toString(encrypted)); // Print byte array
        System.out.println("Decrypted URL: " + decrypted);
    }
}
