package alorithms;

import java.io.*;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class RSACipher {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Độ dài khóa RSA (2048 bits được khuyến nghị)
        return  keyPairGenerator.generateKeyPair();

    }
    private static SecretKey generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // Độ dài khóa AES (128, 192 hoặc 256 bits)
        return keyGenerator.generateKey();
    }
    public static String encryptRSA(String data, PublicKey publicKey) throws Exception {
        if (publicKey==null) generateRSAKeyPair();

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

    private static void saveKeyToFile(String fileName, Key key) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(key);
        }
    }

    private static byte[] readFile(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName);
             ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            return baos.toByteArray();
        }
    }

    private static void saveToFile(String fileName, byte[] data) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(fileName)) {
            fos.write(data);
        }
    }

    private static byte[] encryptAES(byte[] data, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    private static byte[] decryptAES(byte[] encryptedData, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(encryptedData);
    }

    private static byte[] encryptRSA(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    private static byte[] decryptRSA(byte[] encryptedData, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encryptedData);
    }
    public static void main(String[] args) throws Exception {
        // Khởi tạo dữ liệu cần mã hóa
        String originalData = "Thịnh, RSA!";
        RSACipher rsa = new RSACipher();
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

//        try {
//            rsa.encryptionFile("src\\test.pdf","src\\e.pdf",publicKey);
//            rsa.descryptionFile("src\\e.pdf","src\\e1.pdf", privateKey);
//        } catch (Exception e) {
//            throw new RuntimeException(e);        }
    }
}

