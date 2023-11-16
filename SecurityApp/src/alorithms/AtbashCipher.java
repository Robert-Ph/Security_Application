package alorithms;

import model.ReadFile;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AtbashCipher {
    /*
   A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z
   Z,Y,X,W,Y,U,T,S,R,Q,P,O,N,M,L,K,J,I,H,G,F,E,D,C,B,A
     */
    private Map<Integer, String> p = new HashMap<>();
    public AtbashCipher(){
        new ReadFile().readFile(p, "Plaintext and Ciphertext\\English_Alphabet.txt");
    }

    public String encryption(String data){
        read( "Plaintext and Ciphertext\\English_Alphabet.txt");
        String result="";
        for (int i=0; i < data.length();i++) {
            String kytu = String.valueOf(data.charAt(i));
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer key : set) {
                    if (kytu.toUpperCase().equals(p.get(key))) {
                        result += Character.isLowerCase(data.charAt(i)) ? p.get(25 - key).toLowerCase() : p.get(25 - key) ;
                    }
                }
            }else{
                result += data.charAt(i);
            }
        }
        return result;
    }

    public String decryption(String data){
        read( "Plaintext and Ciphertext\\English_Alphabet.txt");
        String result="";
        for (int i=0; i < data.length();i++) {
            String kytu = String.valueOf(data.charAt(i));
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer key : set) {
                    if (kytu.toUpperCase().equals(p.get(key))) {
                        result += Character.isLowerCase(data.charAt(i)) ? p.get(25 - key).toLowerCase() : p.get(25 - key) ;
                    }
                }
            }else{
                result += data.charAt(i);
            }
        }
        return result;
    }


    public String encryption_vie(String data){
        read( "Plaintext and Ciphertext\\Vietnamese_Alphabet");
        String result="";
        for (int i=0; i < data.length();i++) {
            String kytu = String.valueOf(data.charAt(i));
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer key : set) {
                    if (kytu.toUpperCase().equals(p.get(key))) {
                        result += Character.isLowerCase(data.charAt(i)) ? p.get(28 - key).toLowerCase() : p.get(28 - key) ;
                    }
                }
            }else{
                result += data.charAt(i);
            }
        }
        return result;
    }

    public String decryption_vie(String data){
        read( "Plaintext and Ciphertext\\Vietnamese_Alphabet");
        String result="";
        for (int i=0; i < data.length();i++) {
            String kytu = String.valueOf(data.charAt(i));
            if(Character.isLetter(data.charAt(i))){
                Set<Integer> set = p.keySet();
                for (Integer key : set) {
                    if (kytu.toUpperCase().equals(p.get(key))) {
                        result += Character.isLowerCase(data.charAt(i)) ? p.get(28 - key).toLowerCase() : p.get(28 - key) ;
                    }
                }
            }else{
                result += data.charAt(i);
            }
        }
        return result;
    }

    public void encryFile(String sourceFile, String desFile) throws Exception {
        File file = new File(sourceFile);
        String data ="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data += byteRead;
            }
            fos.write(encryption(data));
            fis.close();
            fos.flush();
            fos.close();

        }else {
            System.out.println("This is not file!");
        }
    }

    public void decryFile(String sourceFile, String desFile) throws Exception {
        File file = new File(sourceFile);
        String data="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            BufferedWriter fos = new BufferedWriter(new FileWriter(desFile));

            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data+=byteRead;

            }
            fos.write(decryption(data));
            fis.close();
            fos.flush();
            fos.close();
        }else {
            System.out.println("This is not file!");
        }
    }


    public void read(String path){
        new ReadFile().readFile(p, path);
    }

}
