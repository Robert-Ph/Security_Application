package alorithms;

public class HillCipher {
    private static final int MODULUS = 26;

    public static void main(String[] args) {
        int[][] keyMatrix = {{6, 24, 1}, {13, 16, 10}, {20, 17, 15}};
        String plaintext = "HELLOHILL";

        String encryptedText = hillCipherEncrypt(plaintext, keyMatrix);
        System.out.println("Văn bản đã mã hóa: " + encryptedText);

        String decryptedText = hillCipherDecrypt(encryptedText, keyMatrix);
        System.out.println("Văn bản đã giải mã: " + decryptedText);

        if (plaintext.equals(decryptedText)) {
            System.out.println("Kiểm tra thành công! Văn bản gốc và văn bản giải mã giống nhau.");
        } else {
            System.out.println("Kiểm tra thất bại! Văn bản gốc và văn bản giải mã không giống nhau.");
        }
    }

    public static String hillCipherEncrypt(String plaintext, int[][] keyMatrix) {
        plaintext = plaintext.replaceAll("\\s", "");
        int n = keyMatrix.length;
        int[] plaintextVector = new int[n];

        StringBuilder encryptedText = new StringBuilder();

        for (int i = 0; i < plaintext.length(); i += n) {
            for (int j = 0; j < n; j++) {
                plaintextVector[j] = (int) (plaintext.charAt(i + j) - 'A');
            }

            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += keyMatrix[j][k] * plaintextVector[k];
                }
                encryptedText.append((char) (sum % MODULUS + 'A'));
            }
        }

        return encryptedText.toString();
    }

    public static String hillCipherDecrypt(String encryptedText, int[][] keyMatrix) {
        int n = keyMatrix.length;
        int[] encryptedVector = new int[n];

        StringBuilder decryptedText = new StringBuilder();

        int determinant = calculateDeterminant(keyMatrix, n);
        int detInverse = findMultiplicativeInverse(determinant, MODULUS);

        for (int i = 0; i < encryptedText.length(); i += n) {
            for (int j = 0; j < n; j++) {
                encryptedVector[j] = (int) (encryptedText.charAt(i + j) - 'A');
            }

            for (int j = 0; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += keyMatrix[k][j] * encryptedVector[k];
                }
                int result = (detInverse * (sum % MODULUS)) % MODULUS;
                decryptedText.append((char) ((result + MODULUS) % MODULUS + 'A'));
            }
        }

        return decryptedText.toString();
    }

    public static int calculateDeterminant(int[][] matrix, int n) {
        if (n == 2) {
            return (matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]);
        } else {
            int determinant = 0;
            for (int i = 0; i < n; i++) {
                determinant += matrix[0][i] * calculateDeterminant(getSubmatrix(matrix, n, i), n - 1) * ((i % 2 == 0) ? 1 : -1);
            }
            return determinant;
        }
    }


    public static int[][] getSubmatrix(int[][] matrix, int n, int col) {
        int[][] submatrix = new int[n - 1][n - 1];
        int subrow = 0;
        for (int row = 1; row < n; row++) {
            int subcol = 0;
            for (int j = 0; j < n; j++) {
                if (j != col) {
                    submatrix[subrow][subcol] = matrix[row][j];
                    subcol++;
                }
            }
            subrow++;
        }
        return submatrix;
    }

    public static int findMultiplicativeInverse(int a, int modulus) {
        for (int x = 1; x < modulus; x++) {
            if ((a * x) % modulus == 1) {
                return x;
            }
        }
        return -1;
    }
}
