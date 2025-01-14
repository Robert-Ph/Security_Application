package alorithms;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class Electronic_signature {
    private HashCipher hashCipher = new HashCipher();
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public String signData(String hash, PrivateKey privateKey)  {
        try {
            return encryptRSA(hash, privateKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifySignature(String hash, PublicKey publicKey, String veri)  {
        try {
            if (veri.equals(decryptRSA(hash,publicKey))){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public String hashFile(String pathFile){
        try {
            return hashCipher.hashfile(pathFile, "MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public  KeyPair generateRSAKeyPair()  {
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyPairGenerator.initialize(2048); // Độ dài khóa RSA (2048 bits được khuyến nghị)
        return  keyPairGenerator.generateKeyPair();

    }

    public  String encryptRSA(String data, PrivateKey privateKey) throws Exception {
        if (privateKey==null) generateRSAKeyPair();

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public  String decryptRSA(String encryptedData, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }

    public   String keyToString(Key key) {
        return bytesToHex(key.getEncoded());
    }

    public   String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
    }

}
