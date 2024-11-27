import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static SecretKeySpec secretKey;

    // Set the key for encryption and decryption
    public static void setKey(String myKey) {
        try {
            byte[] key = myKey.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            secretKey = new SecretKeySpec(key, 0, 16, "AES"); // Use first 128 bits
        } catch (Exception e) {
            throw new RuntimeException("Error setting up the key: " + e.getMessage());
        }
    }

    // Encrypt a string
    public static String encrypt(String data, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting: " + e.getMessage());
        }
    }

    // Decrypt a string
    public static String decrypt(String data, String key) {
        try {
            setKey(key);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)));
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        final String secretKey = "annaUniversity";
        String original = "www.annauniv.edu";

        String encrypted = encrypt(original, secretKey);
        String decrypted = decrypt(encrypted, secretKey);

        System.out.println("URL Encryption Using AES Algorithm\n-------------");
        System.out.println("Original URL: " + original);
        System.out.println("Encrypted URL: " + encrypted);
        System.out.println("Decrypted URL: " + decrypted);
    }
}
