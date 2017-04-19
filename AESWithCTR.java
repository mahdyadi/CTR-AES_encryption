/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.security.SecureRandom;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Drianka Mahdy
 */
public class AESWithCTR {
    
    
    public Cipher encryptCipher(byte[] IV, byte[] key) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding", "SunJCE");
        SecretKeySpec keyUsed = new SecretKeySpec(key,"AES");
        IvParameterSpec initVec = new IvParameterSpec(IV);

        cipher.init(Cipher.ENCRYPT_MODE, keyUsed,initVec);
        return cipher;
    }

    public Cipher decryptCipher(byte[] IV, byte[] key) throws Exception{

        Cipher cipher = Cipher.getInstance("AES/CTR/PKCS5Padding", "SunJCE");
        SecretKeySpec keyUsed = new SecretKeySpec(key,"AES");
        IvParameterSpec initVec = new IvParameterSpec(IV);
        
        cipher.init(Cipher.DECRYPT_MODE, keyUsed,initVec);
        return cipher;
    }

    public byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }        
       
    public byte[] generateBytes(int length){
            SecureRandom randomGenerator = new SecureRandom();
            byte randomKey[] = new byte[length];
            randomGenerator.nextBytes(randomKey);
            return randomKey;
    }
    
}
