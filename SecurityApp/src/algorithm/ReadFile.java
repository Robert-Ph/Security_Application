package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReadFile {


    public void readFile(Map<Integer, String> p){
        FileReader fr = null;
        try {
            fr = new FileReader("Plaintext and Ciphertext\\English_Alphabet.txt");
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

    public static void main(String[] args) {
        ReadFile r = new ReadFile();
        Map<Integer, String> p = new HashMap<>();
        r.readFile(p);
        Set<Integer> set = p.keySet();
        for (Integer key : set) {
            System.out.println(key + "," + p.get(key));

        }
    }
}
