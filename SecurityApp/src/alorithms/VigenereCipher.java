package alorithms;

import model.ReadFile;

import java.io.*;
import java.util.*;


public class VigenereCipher {

    private String key;

    public  String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }

        return sb.toString();
    }
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

    public void encryFile(String sourceFile, String desFile, String key, String nameType) throws Exception {
        File file = new File(sourceFile);
        String data ="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data += byteRead;
            }
            if (nameType.equals("VN")){
                fos.write(encryption_vie(data, key));
            }else if (nameType.equals("UK")){
                fos.write(encryption(data, key));
            }
            fis.close();
            fos.flush();
            fos.close();

        }else {
            System.out.println("This is not file!");
        }
    }

    public void decryFile(String sourceFile, String desFile, String key, String nameType) throws Exception {
        File file = new File(sourceFile);
        String data="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data+=byteRead;

            }
            if (nameType.equals("VN")){
                fos.write(decryption_vie(data, key));
            }else if (nameType.equals("UK")){
                fos.write(decryption(data, key));
            }

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


}
