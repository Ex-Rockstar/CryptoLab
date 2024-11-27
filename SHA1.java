import java.security.MessageDigest;

public class SHA1 {

    public static void main(String[] args) {
        try {
            // Create MessageDigest object for SHA-1
            MessageDigest md = MessageDigest.getInstance("SHA1");

            // Print the algorithm and provider details (optional)
            System.out.println("Algorithm: " + md.getAlgorithm());
            System.out.println("Provider: " + md.getProvider());

   

            // Example 2: Hash of string "abc"
            String input = "abc";
            byte[] output = md.digest(input.getBytes());
            System.out.println("SHA1(\"abc\") = " + bytesToHex(output));


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Helper method to convert byte array to hex string
    private static String bytesToHex(byte[] bytes) {
        char[] hexDigits = "0123456789ABCDEF".toCharArray();
        StringBuilder hexString = new StringBuilder();
        
        // Convert each byte to hex and append to the hex string
        for (byte b : bytes) {
            hexString.append(hexDigits[(b >> 4) & 0x0F]);
            hexString.append(hexDigits[b & 0x0F]);
        }
        
        return hexString.toString();
    }
}
