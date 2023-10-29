package algorithm;

import java.util.*;

public class TranspositionCipher {

    public TranspositionCipher() {

    }

    public String encryption(String data, String key) {
        String result = "";
        int index=0;
        Map<Integer, Integer> textKey = textConverter(key);
        String[][] textMatrix = matrixCalculation(data, key.length());
        for (int i=0; i<textMatrix.length; i++){
            for (int j=0; j <key.length(); j++){
                textMatrix[i][j] = String.valueOf(data.charAt(index));
                index++;
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
                textMatrix[i][j] = String.valueOf(data.charAt(index));
                index++;
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
            String p = text.replace(" ", "");
            if ((p.length() % key) == 0) {
                result = new String[p.length() / key][key];
            } else {
                result = new String[p.length() / key + 1][key];
            }
            return result;
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

    public static void main(String[] args) {

        TranspositionCipher t = new TranspositionCipher();
        String data = "Nong Lam h";
        String key = "hh";
        String[][] r= t.matrixCalculation(data,key.length());
        String y = t.encryption(data,key);
        System.out.println(y);
        System.out.println(t.decryption(y,key));

    }
}


