package alorithms;

import model.ReadFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CaesarCipher {

//    private int key;
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

    public String encryption_vie(String data, int key){
        Map<Integer,String> p = new HashMap<>();
        new ReadFile().readFile(p,"Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        String result="";
        if(key < 0){return "Invalid key";}
        for(int i=0; i< data.length(); i++){
            String kytu = String.valueOf(data.charAt(i));
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer k : set) {
                    if (kytu.toUpperCase().equals(p.get(k))) {
                        result += Character.isLowerCase(data.charAt(i))? p.get(Math.floorMod(k+key, 94)).toLowerCase() : p.get(Math.floorMod(k+key, 94));
                    }
                }
            } else if (Character.isDigit(data.charAt(i))) {
                result += (char) (Math.floorMod(data.charAt(i) - 48 + key, 10)+48);
            }else{
                result += data.charAt(i);
            }
        }
        return result;
    }

    public String decryption_vie(String data, int key){
        Map<Integer,String> p = new HashMap<>();
        new ReadFile().readFile(p,"Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        String result="";
        if(key < 0){return "Invalid key";}
        for(int i=0; i< data.length(); i++){

            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer k : set) {
                    String kytu = String.valueOf(data.charAt(i));
                    if (kytu.toUpperCase().equals(p.get(k))) {
                        result += Character.isLowerCase(data.charAt(i))? p.get(Math.floorMod(k-key, p.size())).toLowerCase() : p.get(Math.floorMod(k-key, p.size()));
                    }
                }
            } else if (Character.isDigit(data.charAt(i))) {
                result += (char) (Math.floorMod(data.charAt(i) - 48 - key, 10)+48);
            } else{
                result += data.charAt(i);
            }
        }
        return result;
    }


    public void encryFile(String sourceFile, String desFile, int key) throws Exception {
        File file = new File(sourceFile);
        String data ="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data += byteRead;
            }
            fos.write(encryption(data, key));
            fis.close();
            fos.flush();
            fos.close();

        }else {
            System.out.println("This is not file!");
        }
    }

    public void decryFile(String sourceFile, String desFile, int key) throws Exception {
        File file = new File(sourceFile);
        String data="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data+=byteRead;

            }
            fos.write(decryption(data, key));
            fis.close();
            fos.flush();
            fos.close();
        }else {
            System.out.println("This is not file!");
        }
    }


    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        String text = "Thịnh 123";
        String y = c.encryption_vie(text, 2);
        System.out.println(y);
        String x = c.decryption_vie(y, 2);
        System.out.println(x);

        try {
            c.encryFile("src\\example.txt","src\\e.txt", 2);
            c.decryFile("src\\e.txt","src\\e1.txt", 2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }


}
