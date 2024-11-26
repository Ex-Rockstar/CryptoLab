import java.util.Scanner;

class TransCipher {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Input plaintext
        System.out.println("Enter the plaintext:");
        String pl = sc.nextLine();
        sc.close();

        // Remove spaces from the plaintext
        String s = pl.replaceAll("\\s", "");
        System.out.println("After removing spaces: " + s);

        // Determine matrix dimensions
        int col = 4; // Fixed number of columns (can be adjusted)
        int k = s.length();
        int row = (int) Math.ceil((double) k / col); // Calculate rows dynamically

        // Fill the matrix row-wise
        char[][] ch = new char[row][col];
        int l = 0; // Index for plaintext characters
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (l < k) {
                    ch[i][j] = s.charAt(l++);
                } else {
                    ch[i][j] = '#'; // Padding character
                }
            }
        }

        // Transpose the matrix (column-major order)
        char[][] trans = new char[col][row];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                trans[j][i] = ch[i][j];
            }
        }

        // Display the transposed (encrypted) message
        System.out.println("Encrypted message:");
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                System.out.print(trans[i][j]);
            }
        }
        System.out.println();
    }
}
