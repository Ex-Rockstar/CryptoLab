import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DES {
    public static void main(String[] argv) {
        try {
            System.out.println("Message Encryption Using DES Algorithm\n----------------");

            // Key generation
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();

            // Cipher instance for encryption
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            
            // Encrypting the message
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] text = "Secret Information".getBytes(); // Message to encrypt
            System.out.println("Original Message [Byte Format]: " + new String(text));
            System.out.println("Original Message: " + new String(text));

            byte[] textEncrypted = desCipher.doFinal(text); // Perform encryption
            System.out.println("Encrypted Message (Byte Format): " + textEncrypted);

            // Decrypting the message
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted); // Perform decryption
            System.out.println("Decrypted Message: " + new String(textDecrypted));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
    }
}
