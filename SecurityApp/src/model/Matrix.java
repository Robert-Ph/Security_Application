package model;

public class Matrix {


    /*
    check ma trận có phải ma trận vuông không
    1. nếu là vuông trả về true
    2. nếu không vuông trả về false
     */
    public boolean checkMatrix(int[][] matrix){
        return matrix.length == matrix[0].length ? true: false;
    }

    /*
       tạo mảng 2 chiều với kích thước nhận được và với giá trị ngẫu nhiên từ 0 đến 26
        */
    public  int[][] generateRandomTwoDArray(int size) {
        int[][] twoDArray = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                twoDArray[i][j] = (int) (Math.random() * 27);
            }
        }
        return twoDArray;
    }

    /*
    phương thức kiểm tra mảng 2 chiều có họp lệ để làm key hay không
     */

    public boolean checkGenerateMatrix(int[][] matrix){
        return Math.abs(calculateDeterminant(matrix)) >0 && checkKey(Math.abs(calculateDeterminant(matrix)), 26)? true: false;
    }

    public boolean checkKey(int x, int size){
        if(x > size || x < 1 ){
            return false;
        }else{
            int m = size;
            while (m != 0) {
                int temp = m;
                m = x % m;
                x = temp;
            }
            if(x > 1){
                return false;
            }
        }
        return true;
    }


    // extended Euclidean aloriithms
    public  int[] extendedEuclidean(int a, int b) {
        if (b == 0) {
            int[] result = { a, 1, 0 };
            return result;
        } else {
            int[] prevResult = extendedEuclidean(b, a % b);
            int gcd = prevResult[0];
            int x = prevResult[2];
            int y = prevResult[1] - (a / b) * prevResult[2];
            int[] result = { gcd, x, y };
            return result;
        }
    }

    // Find the inverse of A with mod N
    public int findModularInverse(int a, int m) {
        int[] result = extendedEuclidean(a, m);
        int gcd = result[0];
        int x = result[1];

        if (gcd != 1) {
            // Nếu a và m không nguyên tố cùng nhau, nghịch đảo không tồn tại
            return -1;
        } else {
            // Đảm bảo nghịch đảo là một số dương
            x = (x % m + m) % m;
            return x;
        }
    }

    /*
    phương thức tìm ma trận nghịch đảo của 1 ma trận cho trước
     */
    public int[][] calculateInverseUsingDeterminant(int[][] matrix) {
        int size = matrix.length;
//        double determinant = calculateDeterminant(matrix);
        int determinant =Math.floorMod(calculateDeterminant(matrix),26) ;
        int t = findModularInverse(determinant,26);
        if (determinant == 0) {
            return null; // Matrix is not invertible
        }

        int[][] adjugate = calculateAdjugate(matrix);

        int[][] inverse = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                inverse[i][j] =Math.floorMod(adjugate[i][j] * t,26) ;
            }
        }

        return inverse;
    }

    public  int calculateDeterminant(int[][] matrix) {
        int size = matrix.length;

        if (size == 1) {
            return matrix[0][0];
        }

        int determinant = 0;

        for (int i = 0; i < size; i++) {
            determinant += Math.pow(-1, i) * matrix[0][i] * calculateDeterminant(getSubmatrix(matrix, size, 0, i));
        }

        return determinant;
    }

    public  int[][] calculateAdjugate(int[][] matrix) {
        int size = matrix.length;
        int[][] adjugate = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int cofactor = (int) (Math.pow(-1, i + j) * calculateDeterminant(getSubmatrix(matrix, size, i, j)));
                adjugate[j][i] = cofactor;
            }
        }

        return adjugate;
    }

    public  int[][] getSubmatrix(int[][] matrix, int size, int rowToRemove, int colToRemove) {
        int[][] submatrix = new int[size - 1][size - 1];
        int r = 0;
        int c = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i != rowToRemove && j != colToRemove) {
                    submatrix[r][c++] = matrix[i][j];
                    if (c == size - 1) {
                        c = 0;
                        r++;
                    }
                }
            }
        }

        return submatrix;
    }
    // Method to print a matrix
    public  void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    /*
    nhân 2 ma trận
     */
    // Method to multiply two matrices
    public int[] multiplyMatrices(int[] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int rows2 = matrix2.length;
        int cols2 = matrix2[0].length;

        // Check if matrices can be multiplied
        if (matrix1.length != rows2) {
            System.out.println("Matrices cannot be multiplied. Invalid dimensions.");
            return null;
        }
        int[] result = new int[rows1];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < cols2; j++) {
                result[i] += matrix2[j][i] * matrix1[j];
            }
        }

        int re =0;
        for (int i=0; i<result.length;i++){
            re += result[i];
        }

        return result;
    }
}
