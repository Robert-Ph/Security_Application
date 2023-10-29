package algorithm;


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

    public static void main(String[] args) {
        VigenereCipher vigenereCipher = new VigenereCipher();
        String text = "This";
        String key = "cip";

        String y = vigenereCipher.encryption(text, key);

        System.out.println(y);
        System.out.println(vigenereCipher.decryption(y,key));

    }
}
