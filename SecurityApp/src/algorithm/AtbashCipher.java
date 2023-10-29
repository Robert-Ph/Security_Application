package algorithm;

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
        new ReadFile().readFile(p);
    }

    public String encryptionAndDecryption(String data){
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

    public static void main(String[] args) {
        AtbashCipher a = new AtbashCipher();
        String text = "Nong Lam";
        String y = a.encryptionAndDecryption(text);
        System.out.println(y);
        String x = a.decryption(y);
        System.out.println(x);
    }
}
