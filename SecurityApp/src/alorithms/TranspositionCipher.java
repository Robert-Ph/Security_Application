package alorithms;

import java.io.*;
import java.util.*;

public class TranspositionCipher {

    public TranspositionCipher() {

    }

    public String encryption(String data, String key) {
        String result = "";
        int index=0;
        Map<Integer, Integer> textKey = textConverter(key);
        String[][] textMatrix = matrixCalculation(data, key.length());
//        String input = data.replace(" ", "");
        for (int i=0; i<textMatrix.length; i++){
            for (int j=0; j <key.length(); j++){
                if (index<data.length()){
                    textMatrix[i][j] = String.valueOf(data.charAt(index));
                    index++;
                }else {
                    textMatrix[i][j] = "";
                }

            }
        }

        Set<Integer> keySet = textKey.keySet();
        for (Integer j : keySet) {
            for (int i=0; i< textMatrix.length; i++){
                result+=textMatrix[i][j];
            }
        }
            return result;
        }

    public String decryption(String data, String key){
        String result="";
        int index=0;
        Map<Integer, Integer> textKey = textConverter(key);
        String[][] textMatrix = matrixCalculation(data, key.length());

        Set<Integer> keySet = textKey.keySet();
        for (Integer j : keySet) {
            for (int i=0; i< textMatrix.length; i++){
                if (index<data.length()){
                    textMatrix[i][j] = String.valueOf(data.charAt(index));
                    index++;
                }else {
                    textMatrix[i][j] = "";
                }

            }
        }
        for (int i=0; i<textMatrix.length; i++){
            for (int j=0; j <key.length(); j++){
                result += textMatrix[i][j];
            }
        }

        return result;
    }


        public String[][] matrixCalculation (String text,int key){
            String[][] result;
//            String p = text.replace(" ", "");
            String p = text;
            if ((p.length() % key) == 0) {
                result = new String[p.length() / key][key];
            } else {
                result = new String[p.length() / key + 1][key];
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


    public Map<Integer, Integer> textConverter (String text){
        Map<Integer,Integer> result = new HashMap<>();
        Map<Integer,Integer> r = new HashMap<>();
        String t = text.toUpperCase();
            for (int i = 0; i < t.length(); i++) {
            Integer k = (int) t.charAt(i);
            r.put(i, k);
            }
//       // Chuyển các mục của HashMap thành danh sách
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(r.entrySet());
//
//        // Sắp xếp danh sách bằng Comparator dựa trên giá trị
        Collections.sort(entryList, (entry1, entry2) -> entry1.getValue().compareTo(entry2.getValue()));
//
//        // Tạo một LinkedHashMap để lưu trữ kết quả đã sắp xếp
        LinkedHashMap<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
            return sortedMap;
        }


}


