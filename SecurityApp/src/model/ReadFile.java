package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReadFile {


    public void readFile(Map<Integer, String> p, String path){
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            int num=0;
            String i;
            while ((i = br.readLine()) != null) {
                p.put(num, i);
                num++;
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public ArrayList<Integer> textConverter(String text, Map<Integer, String> p){
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<String> tachChuoi = new ArrayList<>();


        for (int i=0; i < text.length();i++){
            String kytu = String.valueOf(text.charAt(i));
            tachChuoi.add(kytu);
        }

        for (String i:tachChuoi) {
            if(i.equals(" ")){
                result.add(32);
            }else{
                Set<Integer> set = p.keySet();
                for (Integer key : set) {
                    if((i.toUpperCase()).equals(p.get(key))){
                        result.add(key);
                    }

                }
            }

        }

        return result;
    }


    public   String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }

    public String readFiletoString(String pathFile) throws Exception{
        File file = new File(pathFile);
        String data="";
        if(file.isFile()){
            BufferedReader fis = new BufferedReader(new FileReader(file));
            String byteRead;
            while ((byteRead = fis.readLine())!=null){
                data+=byteRead;
            }
            fis.close();
        }else {
            System.out.println("This is not file!");
        }
        return data;
    }

    public void writeFileKeyHill(String path, int[][] arrkey){
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(path));

            for (int i=0; i<arrkey.length; i++){
                String out = "";
                for (int j=0; j<arrkey[0].length; j++){
                    out += arrkey[i][j]+",";

                }
                file.write(out);
                file.newLine();
            }
            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String data, String pathFile){
        try {
            BufferedWriter file = new BufferedWriter(new FileWriter(pathFile));
            file.write(data);

            file.flush();
            file.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) { ReadFile r = new ReadFile();
        Map<Integer, String> p = new HashMap<>();
        r.readFile(p,"Plaintext and Ciphertext\\Vietnames_Alphabet_sound");
        Set<Integer> set = p.keySet();
        for (Integer key : set) {
            System.out.println(key + "," + p.get(key));

        }
//
    }
}
