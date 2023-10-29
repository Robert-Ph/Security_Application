package algorithm;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CaesarCipher {

    private int key;
    private Map<String,Integer> p = new HashMap<>();

    public CaesarCipher(){
//       new ReadFile().readFile(p);
    }
    public String encryption(String data, int key){
        String result="";
        if(key < 0){return "Invalid key";}
        for(int i=0; i< data.length(); i++){
            char originalChar = data.charAt(i);
            if(Character.isLetter(originalChar)){
                result += Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(data.charAt(i) - 97 + key, 26)+97)) : ((char)(Math.floorMod(data.charAt(i) - 65 + key, 26)+65));
                
            } else if (Character.isDigit(originalChar)) {
                result += (char) (Math.floorMod(data.charAt(i) -48 + key, 10)+48);
            }else{
                result+=data.charAt(i);
            }
        }
        return result;
    }

    public String decryption(String data, Integer key){
        String result="";
        if(key < 0){return "Invalid key";}
        for(int i=0; i< data.length(); i++){
            char originalChar = data.charAt(i);

            if(Character.isLetter(originalChar)){
                result += Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(data.charAt(i) - 97 - key, 26)+97)) : ((char) (Math.floorMod(data.charAt(i) - 65 - key, 26)+65));

            } else if (Character.isDigit(originalChar)) {
                result += (char) (Math.floorMod(data.charAt(i) - 48 - key, 10)+48);
            }else{
                result+=data.charAt(i);
            }
        }
        return result;
    }


    public void encryFile(String sourceFile, String desFile) throws Exception {
        File file = new File(sourceFile);
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                fos.write(encryption(byteRead, key));
            }
            fis.close();
            fos.flush();
            fos.close();
        }else {
            System.out.println("This is not file!");
        }
    }

    public void decryFile(String sourceFile, String desFile) throws Exception {
        File file = new File(sourceFile);
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                fos.write(decryption(byteRead, key));
            }
            fis.close();
            fos.flush();
            fos.close();
        }else {
            System.out.println("This is not file!");
        }
    }


    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        String text = "The quick brown, fox jumps over the lazy dog. 123";
        String y = c.encryption(text, 2);
        System.out.println(y);
        String x = c.decryption(y, 2);
        System.out.println(x);

    }


}
