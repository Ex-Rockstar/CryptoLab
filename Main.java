public class Main {
    // 3x3 key matrix for 3 characters at a time
    public static int[][] keymat = {
        {1,  2, 1},
        {2, 3, 2},
        {2, 2, 1}
    };

    // Key inverse matrix
    public static int[][] invkeymat = {
        {-1, 0, 1},
        {2, -1, 0},
        {-2, 2, -1}
    };

    public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encode function for 3 characters at a time
    private static String encode(char a, char b, char c) {
        String ret = "";
        int x, y, z;

        // Convert chars to positions in the alphabet (0 for A, 1 for B, etc.)
        int posa = a - 'A';
        int posb = b - 'A';
        int posc = c - 'A';

        // Matrix multiplication to get new positions
        x = posa * keymat[0][0] + posb * keymat[0][1] + posc * keymat[0][2];
        y = posa * keymat[1][0] + posb * keymat[1][1] + posc * keymat[1][2];
        z = posa * keymat[2][0] + posb * keymat[2][1] + posc * keymat[2][2];

        // Apply modulo 26 to get the final encoded positions
        a = key.charAt(x % 26);
        b = key.charAt(y % 26);
        c = key.charAt(z % 26);

        ret = "" + a + b + c;
        return ret;
    }

    // Decode function for 3 characters at a time
    private static String decode(char a, char b, char c) {
        String ret = "";
        int x, y, z;

        // Convert chars to positions in the alphabet
        int posa = a - 'A';
        int posb = b - 'A';
        int posc = c - 'A';

        // Matrix multiplication with the inverse key matrix
        x = posa * invkeymat[0][0] + posb * invkeymat[0][1] + posc * invkeymat[0][2];
        y = posa * invkeymat[1][0] + posb * invkeymat[1][1] + posc * invkeymat[1][2];
        z = posa * invkeymat[2][0] + posb * invkeymat[2][1] + posc * invkeymat[2][2];

        // Apply modulo 26 and ensure positive values
        a = key.charAt((x % 26 + 26) % 26);
        b = key.charAt((y % 26 + 26) % 26);
        c = key.charAt((z % 26 + 26) % 26);

        ret = "" + a + b + c;
        return ret;
    }

    public static void main(String[] args) throws Exception {
        String msg = "catiscute";
        String enc = "";
        String dec = "";

        System.out.println("Simulation of Hill Cipher");
        System.out.println("--------------------------");
        System.out.println("Input message: " + msg);

        // Prepare the message: convert to uppercase and remove spaces
        msg = msg.toUpperCase().replaceAll("\\s", "");


        // Encode the message
        char[] pdchars = msg.toCharArray();
        for (int i = 0; i < msg.length(); i += 3) {
            enc += encode(pdchars[i], pdchars[i + 1], pdchars[i + 2]);
        }
        System.out.println("Encoded message: " + enc);

        // Decode the message
        char[] dechars = enc.toCharArray();
        for (int i = 0; i < enc.length(); i += 3) {
            dec += decode(dechars[i], dechars[i + 1], dechars[i + 2]);
        }
        System.out.println("Decoded message: " + dec);
    }
}
