import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.IvParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import java.util.Scanner;

public class AES {
	public static void main(String [] args) {
		try {
			/*Scanner in = new Scanner(System.in);
			System.out.println("==Input==");
			System.out.print("IV: ");
			String IV = in.nextLine();
			System.out.print("Key(in-hex): ");
			String encryptionKey = in.nextLine();			
			System.out.print("Plaintext: ");
			String plaintext = in.nextLine();*/
			String IV = "00000000000000000000000000000000";
			String encryptionKey = "FF000000000000000000000000123456";
			String plaintext1 = "FF000000000000000000000000000000";

			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding", "SunJCE");
			SecretKeySpec key = new SecretKeySpec(DatatypeConverter.parseHexBinary(encryptionKey),"AES");
			String cipherText = DatatypeConverter.printHexBinary(encrypt(IV, plaintext1, cipher, key));
			String plaintext2 = DatatypeConverter.printHexBinary(decrypt(IV, cipherText, cipher, key));
			
			System.out.println("==Output==");
			System.out.println("plaintext1:   " + plaintext1);
			System.out.println("ciphertext:   " + cipherText);
			System.out.println("plaintext2:   " + plaintext2);

			/*
			byte[] cipher = encrypt(IV, plaintext, encryptionKey);

			String decrypted = decrypt(IV, cipher, encryptionKey);

			System.out.println("decrypt: " + decrypted);
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	public static byte[] encrypt(String IV, String plaintext, Cipher cipher, SecretKeySpec key) throws Exception {
		cipher.init(Cipher.ENCRYPT_MODE, key);//,new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(DatatypeConverter.parseHexBinary(plaintext));//plaintext.getBytes("UTF-8"));
	}

	public static byte[] decrypt(String IV, String cipherText, Cipher cipher, SecretKeySpec key) throws Exception{
		cipher.init(Cipher.DECRYPT_MODE, key);//,new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(DatatypeConverter.parseHexBinary(cipherText));//cipher.doFinal(cipherText),"UTF-8");
	}
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}
}