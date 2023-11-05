package alorithms;

import javax.crypto.Cipher;
import java.io.*;
import java.security.*;
import java.util.Base64;

public class RSACipher {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048); // Độ dài khóa RSA (2048 bits được khuyến nghị)
        return  keyPairGenerator.generateKeyPair();

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

    public void encryptionFile(String sourceFile, String desFile, PublicKey key) throws Exception{
        File file = new File(sourceFile);
        if (file.isFile()){

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(desFile)));

            byte[] input = new byte[256];

            long length = file.length();
            int byteRead = 0;
            while ((length > 0)){
                byteRead = dis.read(input);
                byte[] encryptedData = cipher.doFinal(input, 0, byteRead);
                dos.write(encryptedData);
                length -= byteRead;
            }
            dis.close();
            dos.close();
            System.out.println("File đã được mã hóa thành công.");
        }
    }

    public void descryptionFile(String sourceFile, String desFile, PrivateKey key) throws Exception{
        File file = new File(sourceFile);
        if (file.isFile()){

            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(desFile)));

            byte[] input = new byte[256];

            long length = file.length();
            int byteRead = 0;
            while ((length > 0)){
                byteRead = dis.read(input);
                byte[] encryptedData = cipher.doFinal(input, 0, byteRead);
                dos.write(encryptedData);
                length -= byteRead;
            }
            dis.close();
            dos.close();
            System.out.println("File đã được mã hóa thành công.");
        }
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

        try {
            rsa.encryptionFile("src\\example.txt","src\\e.txt",publicKey);
            rsa.descryptionFile("src\\e.txt","src\\e1.txt", privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

