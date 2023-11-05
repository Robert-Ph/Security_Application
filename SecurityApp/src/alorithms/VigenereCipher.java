package alorithms;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class VigenereCipher {

    private String key;
    public String encryption(String data, String key){
        String result="";
        for (int i=0; i< data.length(); i++){
            if(Character.isLetter(data.charAt(i))){

                if(i < key.length()){
                    int k = Character.isLowerCase(key.charAt(i)) ? (key.charAt(i) - 97) : (key.charAt(i) - 65);
                    result += Character.isLowerCase(data.charAt(i)) ? (char) (Math.floorMod((data.charAt(i) - 97 + k), 26) + 97): ((char) (Math.floorMod((data.charAt(i) - 65) + k, 26) + 65));

                }else{
                    int k = Character.isLowerCase(key.charAt(Math.floorMod(i, key.length()))) ? (key.charAt(Math.floorMod(i, key.length())) - 97) : (key.charAt(Math.floorMod(i, key.length())) - 65);
                    result += Character.isLowerCase(data.charAt(i)) ? (char) (Math.floorMod(data.charAt(i) - 97 + k, 26) + 97): ((char) (Math.floorMod((data.charAt(i) - 65) + k, 26) + 65));
                }

            }else {
                result += data.charAt(i);
            }
        }
        return result;
    }

    public String decryption(String data, String key){
        String result="";
        for (int i=0; i< data.length(); i++){
            if(Character.isLetter(data.charAt(i))){
                if(i < key.length()){
                    int k = Character.isLowerCase(key.charAt(i)) ? (key.charAt(i) - 97) : (key.charAt(i) - 65);
                    result += Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(data.charAt(i) - 97 - k, 26) + 97)): ((char) (Math.floorMod(data.charAt(i) - 65 - k, 26) + 65));

                }else{
                    int k = Character.isLowerCase(key.charAt(Math.floorMod(i, key.length()))) ? (key.charAt(Math.floorMod(i, key.length())) - 97) : (key.charAt(Math.floorMod(i, key.length())) - 65);
                    result += Character.isLowerCase(data.charAt(i)) ? ((char) (Math.floorMod(data.charAt(i) - 97 - k, 26) + 97)): ((char) (Math.floorMod(data.charAt(i) - 65 - k, 26) + 65));
                }

            }else {
                result += data.charAt(i);
            }
        }
        return result;
    }


    public String encryption_vie(String data, String key){
        Map<Integer,String> p = new HashMap<>();
        new ReadFile().readFile(p,"Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        ArrayList<Integer> listKey = sumChar(data, key);
        int j=0;
        String result="";
        for (int i=0; i< data.length(); i++){
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer ky : set) {
                    String kytu = String.valueOf(data.charAt(i));
                    if (kytu.toUpperCase().equals(p.get(ky))) {
                        result += Character.isLowerCase(data.charAt(i))? p.get(Math.floorMod(ky + listKey.get(j),94)).toLowerCase() : p.get(Math.floorMod(ky+listKey.get(j), 94));
                        j++;
                    }
                }


            }else {
                result += data.charAt(i);
            }
        }
        return result;
    }

    public String decryption_vie(String data, String key){
        Map<Integer,String> p = new HashMap<>();
        new ReadFile().readFile(p,"Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        ArrayList<Integer> listKey = sumChar(data, key);
        int j=0;
        String result="";
        for (int i=0; i< data.length(); i++){
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer ky : set) {
                    String kytu = String.valueOf(data.charAt(i));
                    if (kytu.toUpperCase().equals(p.get(ky))) {
                        result += Character.isLowerCase(data.charAt(i))? p.get(Math.floorMod(ky - listKey.get(j), 94)).toLowerCase() : p.get(Math.floorMod(ky-listKey.get(j), 94));
                        j++;
                    }
                }
            }else {
                result += data.charAt(i);
            }
        }
        return result;
    }

    public void encryFile(String sourceFile, String desFile, String key) throws Exception {
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

    public void decryFile(String sourceFile, String desFile, String key) throws Exception {
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

    public ArrayList<Integer> sumChar(String data, String text){
        Map<Integer,String> p = new HashMap<>();
        new ReadFile().readFile(p,"Plaintext and Ciphertext\\Vietnames_Alphabet_sound" );
        int result=0;
        for (int i=0; i<data.length(); i++){
            if (Character.isLetter(data.charAt(i))){
                result++;
            }
        }
        ArrayList<String> listText = new ArrayList<>();
        for (int i=0; i<result; i++){
            listText.add(String.valueOf(text.charAt(Math.floorMod(i, text.length()))));
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i=0; i<listText.size(); i++){

            Set<Integer> set = p.keySet();
            for (Integer ky : set) {
                if (listText.get(i).toUpperCase().equals(p.get(ky))) {
                        list.add(ky);
                }
            }
        }
        return  list;
    }

    public static void main(String[] args) {
        VigenereCipher vigenereCipher = new VigenereCipher();
        String text = "thịnh";
        String key = "cip";

        String y = vigenereCipher.encryption_vie(text, key);

        System.out.println(y);
        System.out.println(vigenereCipher.decryption_vie(y,key));

//        try {
//            vigenereCipher.encryFile("src\\example.txt","src\\e.txt", "cip");
//            vigenereCipher.decryFile("src\\e.txt","src\\e1.txt", "cip");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }
}
