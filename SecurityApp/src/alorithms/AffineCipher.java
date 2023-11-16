package alorithms;

import model.ReadFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class AffineCipher {

//    private int a;
//    private int b;

    public int[] generateKey(String nameType){
        int[] result = new int[2];
        int a=0;
        int b=0;
        if (nameType.equals("UK")){
            a = ThreadLocalRandom.current().nextInt(1, 27);
            b = ThreadLocalRandom.current().nextInt(1, 27);
            if (checkKey(a,b,26) && a!=0 || b!=0){
                result[0] = a;
                result[1] = b;
            }else generateKey(nameType);
        }else if(nameType.equals("VN")){
            a = ThreadLocalRandom.current().nextInt(1, 95);
            b = ThreadLocalRandom.current().nextInt(1, 95);
            if (checkKey(a,b,26) && a!=0 || b!=0){
                result[0] = a;
                result[1] = b;
            }else generateKey(nameType);
        }
        return result;
    }
    public String encryption(String data, int x, int y){
        String result="";
        if(checkKey(x,y, 26)){
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
        if (checkKey(x,y, 26)){
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

    public String encryption_vie(String data, int x, int y){
        Map<Integer, String> p = new HashMap<>();
        new ReadFile().readFile(p, "Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        String result="";
        if(checkKey(x,y, 94)){
            for (int i=0; i< data.length(); i++ ){
                if (Character.isLetter(data.charAt(i))){
                    Set<Integer> set = p.keySet();
                    for (Integer k : set) {
                        String kytu = String.valueOf(data.charAt(i));
                        if (kytu.toUpperCase().equals(p.get(k))) {
                            result += Character.isLowerCase(data.charAt(i))? p.get(Math.floorMod((x*k)+ y, 94)).toLowerCase() : p.get(Math.floorMod((x*k)+ y, 94));
                        }
                    }
//                    result +=  Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(x*(data.charAt(i) - 97) + y, 26)+97)) : ((char) (Math.floorMod(x*(data.charAt(i) - 65) + y, 26)+65));
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

    public String decryption_vie(String data, int x, int y){
        Map<Integer, String> p = new HashMap<>();
        new ReadFile().readFile(p, "Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        String result="";
        if (checkKey(x,y, 94)){
            int inverseOfX = findModularInverse(x, 94);
            for (int i=0; i< data.length(); i++ ){
                if (Character.isLetter(data.charAt(i))){
                    Set<Integer> set = p.keySet();
                    for (Integer k : set) {
                        String kytu = String.valueOf(data.charAt(i));
                        if (kytu.toUpperCase().equals(p.get(k))) {
                            result += Character.isLowerCase(data.charAt(i))? p.get(Math.floorMod(inverseOfX*(k - y), 94)).toLowerCase() : p.get(Math.floorMod(inverseOfX*(k- y), 94));
                        }
                    }
//                    result +=  Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(inverseOfX*(data.charAt(i) - 97 - y), 26)+97)) : ((char) (Math.floorMod(inverseOfX*(data.charAt(i) - 65 - y), 26)+65));
                } else if (Character.isDigit(data.charAt(i))) {
                    result += (char) (Math.floorMod(inverseOfX*(Math.abs(data.charAt(i) - 48 - y)), 10)+48);
                }else {
                    result += data.charAt(i);
                }
            }
        }else{
            return "Invalid key";
        }
        return result;
    }

    public void encryFile(String sourceFile, String desFile, int a, int b) throws Exception {
        if (checkKey(a,b, 26)){
            File file = new File(sourceFile);
            if(file.isFile()){
                BufferedReader fis = new BufferedReader(new FileReader(file));
                BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));
                String data ="";
                String byteRead;
                while ((byteRead = fis.readLine())!=null){
                    data+=byteRead;
                }
                fos.write(encryption(data, a, b));
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

    public void decryFile(String sourceFile, String desFile, int a, int b) throws Exception {
        if (checkKey(a,b, 26)){
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

    public boolean checkKey(int x, int y, int size){
        if(x > size || x < 1 || y > size || y<1){
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

//    public int getA() {
//        return a;
//    }
//
//    public void setA(int a) {
//        this.a = a;
//    }
//
//    public int getB() {
//        return b;
//    }
//
//    public void setB(int b) {
//        this.b = b;
//    }


}
