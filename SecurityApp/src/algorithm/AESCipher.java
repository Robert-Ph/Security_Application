package algorithm;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESCipher {

    private SecretKey key;
    private int lengthKey;

    public AESCipher()  {

    }

    public SecretKey generateAESKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = new SecureRandom();
        keyGenerator.init(lengthKey, secureRandom); // 256-bit AES key
        key = keyGenerator.generateKey();
        return key;
    }
    public  String secretKeyToString(SecretKey secretKey) {
        byte[] keyBytes = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(keyBytes);
    }

    public  String encryptAES(String data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public String decryptAES(String encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
    public void encryFile(String sourceFile, String desFile) throws Exception{
        if(key == null) throw new FileNotFoundException();

        File file = new File(sourceFile);
        if (file.isFile()){
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(desFile));

            byte[] input = new byte[64];
            int byteRead;
            while ((byteRead = fis.read(input)) != -1){
                byte[] ouput = cipher.update(input, 0, byteRead);
                if(ouput!=null) fos.write(ouput);
            }
            byte[] ouput = cipher.doFinal();
            if (ouput!=null) fos.write(ouput);

            fos.flush();
            fos.close();
            fis.close();
        }else {
            System.out.println("This is not a file!");
        }
    }

    public void decryFile(String sourceFile, String desFile) throws Exception{
        if(key == null) throw new FileNotFoundException();

        File file = new File(sourceFile);
        if (file.isFile()){
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
            BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(desFile));

            byte[] input = new byte[64];
            int byteRead;
            while ((byteRead = fis.read(input)) != -1){
                byte[] ouput = cipher.update(input, 0, byteRead);
                if(ouput!=null) fos.write(ouput);
            }
            byte[] ouput = cipher.doFinal();
            if (ouput!=null) fos.write(ouput);

            fos.flush();
            fos.close();
            fis.close();
        }else {
            System.out.println("This is not a file!");
        }
    }

    public SecretKey getKey() {
        return key;
    }

    public void setKey(SecretKey key) {
        this.key = key;
    }

    public int getLengthKey() {
        return lengthKey;
    }

    public void setLengthKey(int lengthKey) {
        this.lengthKey = lengthKey;
    }

    public static void main(String[] args) throws Exception {
        // Khởi tạo dữ liệu cần mã hóa
        String originalData = "Hello, AES!";

        AESCipher aesCipher = new AESCipher();
        aesCipher.setLengthKey(128);
        // Tạo khóa bí mật AES
        SecretKey secretKey = aesCipher.generateAESKey();

        // Mã hóa dữ liệu
        String encryptedData = aesCipher.encryptAES(originalData, secretKey);
        System.out.println("Encrypted Data: " + encryptedData);

        // Giải mã dữ liệu
        String decryptedData = aesCipher.decryptAES(encryptedData, secretKey);
        System.out.println("Decrypted Data: " + decryptedData);
    }
}
