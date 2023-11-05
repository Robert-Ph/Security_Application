package alorithms;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashCipher {
    public String SHA_1 = "SHA-1";
    public String SHA_224 = "SHA-224";
    public String SHA_256 = "SHA-256";
    public String SHA_384 = "SHA-384";
    public String SHA_512_224 = "SHA-512/224";
    public String SHA_512_256 = "SHA-512/256";



    public String hash(String input, String alorithms){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(alorithms);
            byte[] mess = messageDigest.digest(input.getBytes());
            BigInteger number = new BigInteger(1, mess);
            String hashText = number.toString(16);
            return hashText;
        } catch (NoSuchAlgorithmException e) {
           e.printStackTrace();
        }
        return "";
    }

    public String hashfile(String filePath, String alorithms) throws Exception{
        File file = new File(filePath);
        if(file.isFile()){
            MessageDigest messageDigest = MessageDigest.getInstance(alorithms);
            InputStream inputFile = new BufferedInputStream(new FileInputStream(filePath));
            DigestInputStream digestInputStream = new DigestInputStream(inputFile, messageDigest);

            byte[] buffer = new byte[1024];
            int read;

            while ((read = inputFile.read(buffer))!=-1){
                messageDigest.update(buffer,0,read);
            }

            byte[] hast = messageDigest.digest();
            BigInteger number = new BigInteger(1, hast);
            String hashText = number.toString(16);
            return hashText;
//            do{
//                read = inputFile.read(buffer);
//            }while (read != -1);
//            BigInteger number = new BigInteger(1, digestInputStream.getMessageDigest().digest());
//            inputFile.close();
//            return number.toString(16);

        }else {
            return "not file";
        }
    }

    public static void main(String[] args) throws Exception {
        HashCipher sha = new HashCipher();
        String out = sha.hashfile("F:\\Lab\\Security_Application\\SecurityApp\\src\\algorithm\\text", "SHA-1");
        System.out.println(out);
    }
}
