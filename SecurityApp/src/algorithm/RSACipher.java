package algorithm;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSACipher {


    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Độ dài khóa RSA (2048 bits được khuyến nghị)
        return keyPairGenerator.generateKeyPair();
    }

    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decryptRSA(String encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        // Khởi tạo dữ liệu cần mã hóa
        String originalData = "Hello, RSA!";

        // Tạo cặp khóa RSA (khóa công khai và khóa bí mật)
        KeyPair keyPair = generateRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Mã hóa dữ liệu bằng khóa công khai
        String encryptedData = encryptRSA(originalData, publicKey);
        System.out.println("Encrypted Data: " + encryptedData);

        // Giải mã dữ liệu bằng khóa bí mật
        String decryptedData = decryptRSA(encryptedData, privateKey);
        System.out.println("Decrypted Data: " + decryptedData);
    }
}

