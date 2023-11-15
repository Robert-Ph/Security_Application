package alorithms;

import model.Matrix;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class HillCipher {

    private Matrix matrix = new Matrix();
    private static final int MODULUS = 26;

    public Matrix getMatrix() {
        return matrix;
    }

    public static void main(String[] args) {
        HillCipher hillCipher = new HillCipher();

        int[][] keyMatrix = {{3, 3}, {2, 5}};
        String plaintext = "help 123";

        String encryptedText = hillCipher.hillCipherEncrypt(plaintext, keyMatrix);
        String g =hillCipher.stringData(plaintext, encryptedText,2, true);
        System.out.println("Văn bản đã mã hóa: " + encryptedText);
        System.out.println(hillCipher.stringData(plaintext, encryptedText,2,true));

        int num = hillCipher.numsize(encryptedText, keyMatrix);
        System.out.println("so: "+num);

        String decryptedText =hillCipher.hillCipherDecrypt(encryptedText, keyMatrix);
        System.out.println("Văn bản đã giải mã: " + decryptedText);
        System.out.println(hillCipher.stringData(g.substring(0,g.length()-keyMatrix.length), decryptedText,num,false));
//        int[][] result=hillCipher.createKey(2);
//        int[] r = hillCipher.matrix.multiplyMatrices(result[0],keyMatrix);
//        System.out.println(r[0]);


//        for (int i = 0; i < result.length; i++) {
//            for (int j = 0; j < result[i].length; j++) {
//                System.out.print(result[i][j] + " ");
//            }
//            System.out.println();
//        }
//        String data ="DPLE 12";
//
//        String le = data.substring(data.length()-keyMatrix[0].length);
//        int[] arr = new int[le.length()];
//        for (int i=0; i<le.length();i++){
//            arr[i] =(int) le.charAt(i)-65;
//        }
//        int[][] key = hillCipher.matrix.calculateInverseUsingDeterminant(keyMatrix);
//        int[] numsize = hillCipher.matrix.multiplyMatrices(arr, key);
//
//        String in ="HELP l 12";
//        String input ="";
//        for (int i=0; i<in.length();i++){
//            input += Character.isLetter(in.charAt(i)) ? in.charAt(i) : "";
//        }
//        System.out.println(input);
    }

    public int[][] createKey(int size){
        int[][] result = matrix.generateRandomTwoDArray(size);
        return matrix.checkGenerateMatrix(result) ? result : createKey(size);

    }

    public String splitDataIsLetter(String data){
        String result="";
        for (int i=0; i<data.length();i++){
            if (Character.isLetter(data.charAt(i))){
                result +=data.charAt(i);
            }
        }
        return result;
    }

    public  String hillCipherEncrypt(String data, int[][] keyMatrix) {
        data = splitDataIsLetter(data);
        int n = keyMatrix.length;
        int[][] m = splitString(data,n);
        String encryptedText = "";

        if (matrix.calculateDeterminant(keyMatrix)>0 && matrix.checkKey(matrix.calculateDeterminant(keyMatrix),26)){
            for (int i=0; i<m.length; i++){
                int[] input = m[i];
                int[] out = matrix.multiplyMatrices(input,keyMatrix);
                for (int j=0; j<out.length;j++){
                    char text = (char) (Math.floorMod(out[j], 26) + 65);
                    encryptedText += text;
                }
            }
        }


        return encryptedText;
    }
    public  String hillCipherDecrypt(String data, int[][] keyMatrix) {
        int n = keyMatrix.length;

        int[][] key = matrix.calculateInverseUsingDeterminant(keyMatrix);
        StringBuilder decryptedText = new StringBuilder();

        String le = data.substring(data.length()-n);
        int[] arr = new int[le.length()];
        for (int i=0; i<le.length();i++){
            arr[i] =(int) le.charAt(i)-65;
        }

        int[] numsize = matrix.multiplyMatrices(arr, key);
        int num =Math.floorMod(numsize[0],26);
        int[][] m = splitStringDecr(data.substring(0,data.length()-n),n);
        if (num==0){

            if (matrix.calculateDeterminant(keyMatrix)>0 && matrix.checkKey(matrix.calculateDeterminant(keyMatrix),26)){
                for (int i=0; i<m.length; i++){
                    int[] input = m[i];
                    int[] out = matrix.multiplyMatrices(input,key);
                    for (int j=0; j<out.length;j++){
                        char text = (char) (Math.floorMod(out[j], 26) + 65);
                        decryptedText.append(text);
                    }

                }
            }
        }else {
//            m =splitString(data.substring(0,data.length()-num),n);
            if (matrix.calculateDeterminant(keyMatrix)>0 && matrix.checkKey(matrix.calculateDeterminant(keyMatrix),26)){
                for (int i=0; i<m.length; i++){
                    int[] input = m[i];
                    int[] out = matrix.multiplyMatrices(input,key);
                    for (int j=0; j<out.length;j++){
                        char text = (char) (Math.floorMod(out[j], 26) + 65);
                        decryptedText.append(text);
                    }

                }
            }
        }
        return decryptedText.toString();
    }

//    public  String hillCipherDecrypt(String data, int[][] keyMatrix) {
//        String decryptedText ="";
//        int[] numsize=null;
//        if (matrix.calculateDeterminant(keyMatrix)>0 && matrix.checkKey(matrix.calculateDeterminant(keyMatrix),26)){
//            int n = keyMatrix.length;
//
//            int[][] key = matrix.calculateInverseUsingDeterminant(keyMatrix);
//
//
//            String le = data.substring(data.length()-n).toUpperCase();
//            int[] arr = new int[le.length()];
//            for (int i=0; i<le.length();i++){
//                arr[i] =(int) le.charAt(i)-65;
//            }
//
//            numsize = matrix.multiplyMatrices(arr, key);
//            int num =Math.floorMod(numsize[0],26);
//            int[][] m = splitStringDecr(data.substring(0,data.length()-n),n);
//            if (num==0){
//                    for (int i=0; i<m.length; i++){
//                        int[] input = m[i];
//                        int[] out = matrix.multiplyMatrices(input,key);
//                        for (int j=0; j<out.length;j++){
//                            char text = (char) (Math.floorMod(out[j], 26) + 65);
//                            decryptedText +=text;
//                        }
//
//                    }
//
//            }else {
//                    for (int i=0; i<m.length; i++){
//                        int[] input = m[i];
//                        int[] out = matrix.multiplyMatrices(input,key);
//                        for (int j=0; j<out.length;j++){
//                            char text = (char) (Math.floorMod(out[j], 26) + 65);
//                            decryptedText +=text;
//                        }
//                    }
//            }
//            decryptedText = decryptedText.substring(0, decryptedText.length()-num);
//
//        }
////        String result = stringData(data.substring(0, data.length()-keyMatrix.length), decryptedText, numsize[0], false);
//
//
//        return decryptedText;
//    }



    public  int[][] splitString(String data, int numberOfParts) {

        String input = data.replaceAll(" ", "").toUpperCase();
//        String input ="";
//        for (int i=0; i<data.length();i++){
//            input += Character.isLetter(data.charAt(i)) ? data.charAt(i) : "";
//        }
//        input.toUpperCase();
        int length = input.length();
        int partLength = length / numberOfParts;
        int padding = length % numberOfParts;
        int num=0;
        String[] result;
        if (padding==0){
            result = new String[partLength];
        }else {
            result = new String[partLength+1];
            num = numberOfParts-padding;
        }
        int[][] matr = new  int[result.length+1][numberOfParts];
        int start =0;
        int end=numberOfParts;
        for (int j =0; j<result.length; j++){
            String h = end > input.length() ? input.substring(start) + "a".repeat(end-start) : input.substring(start,end);
            result[j] = h;
            for (int i =0; i< matr[0].length;i++){
                matr[j][i] = Math.floorMod(h.charAt(i)-65,26) ;
            }
            start +=numberOfParts;
            end+=numberOfParts;
        }

        matr[matr.length-1][0] = num;
        return matr;
    }

    public  int[][] splitStringDecr(String data, int numberOfParts) {

        String input = data.replaceAll(" ", "").toUpperCase();

        int[][] matr = new  int[data.length()/numberOfParts][numberOfParts];
        int start =0;
        int end=numberOfParts;
        for (int j =0; j<matr.length; j++){
            String h =data.substring(start,end);

            for (int i =0; i< numberOfParts;i++){
                matr[j][i] = Math.floorMod(h.charAt(i)-65,26) ;
            }
            start +=numberOfParts;
            end+=numberOfParts;
        }
        return matr;
    }

    public String toStringKeyArray(int[][] arrKey){
        String result="";
        for (int i=0; i<arrKey.length;i++){
            for (int j=0; j<arrKey[0].length;j++){
                result += arrKey[i][j] + "\t";
            }
            result+="\n";
        }
        return result;
    }

    public int[][] readMatrix(String path){
        File file = new File(path);
        ArrayList<int[]> list = new ArrayList<>();
        int[][] result;
        if (file.isFile() && file.exists()){
            try {
                BufferedReader fis = new BufferedReader(new FileReader(file));
                String byteRead;
                while ((byteRead = fis.readLine())!=null){
                    String re = byteRead;
                    String[] st = re.split(",");
                    int[] t = new int[st.length];
                    for (int i=0; i<t.length; i++){
                        t[i] = Integer.parseInt(st[i]);
                    }
                    list.add(t);
                }
                result = new int[list.size()][list.get(0).length];
                for (int i=0; i< result.length;i++){
                   result[i] = list.get(i);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return  result;
        }
        return  null;
    }
    public String getLetter(String data){
        String result ="";
        for (int i=0; i<data.length(); i++){
            if (Character.isLetter(data.charAt(i))) result += data.charAt(i);
        }
        return result;
    }

    public String stringData(String data, String encr, int numaize, boolean bool){
//        String[] input = data.split("\\s");
        String y ="";
        int num=0;
        int size=0;
        for (int i=0; i<data.length();i++){
            if(Character.isLetter(data.charAt(i)) && size<= encr.length()){
                y+=encr.charAt(i-num);
                size++;
            }else {
                y+= data.charAt(i);
                num++;
            }
        }
        String result ="";

        if (bool){
            for (int i=0; i<data.length(); i++){
                if (Character.isLetter(data.charAt(i))){
                    result += Character.isLowerCase(data.charAt(i)) ? Character.toLowerCase(y.charAt(i)) : y.charAt(i);
                }else {
                    result += y.charAt(i);
                }
            }
            result += encr.substring(size);
        }else {
            data = data.substring(0, data.length()-numaize);
            for (int i=0; i<data.length(); i++){
                if (Character.isLetter(data.charAt(i))){
                    result += Character.isLowerCase(data.charAt(i)) ? Character.toLowerCase(y.charAt(i)) : y.charAt(i);
                }else {
                    result += y.charAt(i);
                }
            }
            result += encr.substring(size);
//        result = numaize >0? result.substring(0, result.length()-numaize-1) : result;
        }

        return result;
    }

    public int numsize(String data, int[][] key){
        String le = data.substring(data.length()-key.length).toUpperCase();
        int[] arr = new int[le.length()];
        for (int i=0; i<le.length();i++){
            arr[i] =(int) le.charAt(i)-65;
        }

        int[] numsize = matrix.multiplyMatrices(arr, matrix.calculateInverseUsingDeterminant(key));
        int num =Math.floorMod(numsize[0],26);

        return num;
    }
}
