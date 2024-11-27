import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class DES {
    public static void main(String[] argv) {
        try {
            System.out.println("Message Encryption Using DES Algorithm\n----------------");

            // Key generation
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            SecretKey desKey = keyGenerator.generateKey();

            // Cipher instance for encryption
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Encrypting the message
            desCipher.init(Cipher.ENCRYPT_MODE, desKey);
            byte[] text = "Secret Information".getBytes(); // Message to encrypt
            System.out.println("Original Message: " + new String(text));

            byte[] textEncrypted = desCipher.doFinal(text); // Perform encryption
            System.out.println("Encrypted Message: " + new String(textEncrypted));

            // Decrypting the message
            desCipher.init(Cipher.DECRYPT_MODE, desKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted); // Perform decryption
            System.out.println("Decrypted Message: " + new String(textDecrypted));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
