package algorithm;

import java.io.*;

public class AffineCipher {

    private int a;
    private int b;
    public String encryption(String data, int x, int y){
        String result="";
        if(checkKey(x,y)){
            for (int i=0; i< data.length(); i++ ){
                if (Character.isLetter(data.charAt(i))){
                    result +=  Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(x*(data.charAt(i) - 97) + y, 26)+97)) : ((char) (Math.floorMod(x*(data.charAt(i) - 65) + y, 26)+65));
                } else if (Character.isDigit(data.charAt(i))) {
                    result += (char) (Math.floorMod(x*(data.charAt(i) - 48) + y, 10)+48);
                }else {
                    result += data.charAt(i);
                }
            }
        }else{
            return "Invalid key";
        }
        return result;
    }

    public String decryption(String data, int x, int y){
        String result="";
        if (checkKey(x,y)){
            int inverseOfX = findModularInverse(x, 26);
            for (int i=0; i< data.length(); i++ ){
                if (Character.isLetter(data.charAt(i))){
                    result +=  Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(inverseOfX*(data.charAt(i) - 97 - y), 26)+97)) : ((char) (Math.floorMod(inverseOfX*(data.charAt(i) - 65 - y), 26)+65));
                } else if (Character.isDigit(data.charAt(i))) {
                    result += (char) (Math.floorMod(inverseOfX*(data.charAt(i) - 48 - y), 10)+48);
                }else {
                    result += data.charAt(i);
                }
            }
        }else{
            return "Invalid key";
        }
        return result;
    }

    public void encryFile(String sourceFile, String desFile) throws Exception {
        if (checkKey(a,b)){
            File file = new File(sourceFile);
            if(file.isFile()){
                BufferedReader fis = new BufferedReader(new FileReader(file));
                BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

                String byteRead;
                while ((byteRead = fis.readLine())!=null){
                    fos.write(encryption(byteRead, a, b));
                }
                fis.close();
                fos.flush();
                fos.close();
            }else {
                System.out.println("This is not file!");
            }
        }else {
            System.out.println("Invalid key");
        }

    }

    public void decryFile(String sourceFile, String desFile) throws Exception {
        if (checkKey(a,b)){
            File file = new File(sourceFile);
            if(file.isFile()){
                BufferedReader fis = new BufferedReader(new FileReader(file));
                BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

                String byteRead;
                while ((byteRead = fis.readLine())!=null){
                    fos.write(decryption(byteRead, a, b));
                }
                fis.close();
                fos.flush();
                fos.close();
            }else {
                System.out.println("This is not file!");
            }
        }else {
            System.out.println("Invalid key");
        }

    }

    public boolean checkKey(int x, int y){
        if(x > 26 || x < 1 || y > 26 || y<1){
            return false;
        }else{
            int m = 26;
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

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public static void main(String[] args) {
        AffineCipher a = new AffineCipher();
        String text ="Vieetjnam 123";
        int x=27;
        int y=11;
        String Y =a.encryption(text,x,y);
        System.out.println(Y);
        System.out.println(a.decryption(Y, x, y));
    }
}
