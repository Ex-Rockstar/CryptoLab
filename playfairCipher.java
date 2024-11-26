
import java.awt.Point;

class PlayfairCipher {
    private static char[][] charTable;
    private static Point[] positions;

    // Prepares the text by removing unwanted characters and handling 'J'/'Q'
    private static String prepareText(String s, boolean chgJtoI) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return chgJtoI ? s.replace("J", "I") : s.replace("Q", "");
    }

    // Creates the Playfair cipher table
    private static void createTbl(String key, boolean chgJtoI) {
        charTable = new char[5][5];
        positions = new Point[26];
        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ", chgJtoI);

        int len = s.length();
        for (int i = 0, k = 0; i < len; i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) { // Only insert unique characters
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }

    // Encodes/decodes the text based on the direction (1 for encode, -1 for decode)
    private static String codec(StringBuilder txt, int dir) {
        int len = txt.length();
        for (int i = 0; i < len; i += 2) {
            char a = txt.charAt(i);
            char b = txt.charAt(i + 1);
            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;

            if (row1 == row2) { // Same row
                col1 = (col1 + dir + 5) % 5;
                col2 = (col2 + dir + 5) % 5;
            } else if (col1 == col2) { // Same column
                row1 = (row1 + dir + 5) % 5;
                row2 = (row2 + dir + 5) % 5;
            } else { // Rectangle swap
                int temp = col1;
                col1 = col2;
                col2 = temp;
            }

            txt.setCharAt(i, charTable[row1][col1]);
            txt.setCharAt(i + 1, charTable[row2][col2]);
        }
        return txt.toString();
    }

    // Encode the text
    private static String encode(String s) {
        StringBuilder sb = new StringBuilder(s);
        for (int i = 0; i < sb.length(); i += 2) {
            if (i == sb.length() - 1) { // Odd length, add padding
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
            } else if (sb.charAt(i) == sb.charAt(i + 1)) { // Same character, add padding
                sb.insert(i + 1, 'X');
            }
        }
        return codec(sb, 1);
    }

    // Decode the text
    private static String decode(String s) {
        return codec(new StringBuilder(s), -1);
    }

    public static void main(String[] args) throws java.lang.Exception {
        String key = "CSE";
        String txt = "SecurityLab";
        boolean chgJtoI = true;

        createTbl(key, chgJtoI);

        String preparedText = prepareText(txt, chgJtoI);
        String enc = encode(preparedText);

        System.out.println("Simulating Playfair Cipher");
        System.out.println("--------------------------");
        System.out.println("Input Message: " + txt);
        System.out.println("Encrypted Message: " + enc);
        System.out.println("Decrypted Message: " + decode(enc));
    }
}
