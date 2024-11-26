import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Scanner;

public class CreatingDigitalSignature {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some text:");
        String msg = sc.nextLine();
        sc.close();

        // Generate a key pair
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048); // Set key size
        KeyPair pair = keyPairGen.generateKeyPair();
        PrivateKey privKey = pair.getPrivate();

        // Initialize the signature object
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privKey);

        // Generate the digital signature
        byte[] bytes = msg.getBytes(); // Convert message to bytes
        sign.update(bytes);           // Add data to the signature object
        byte[] signature = sign.sign(); // Generate the signature

        System.out.println("Digital signature for the given text:");
        System.out.println(new String(signature, "UTF-8")); // Display signature in UTF-8 format
    }
}
