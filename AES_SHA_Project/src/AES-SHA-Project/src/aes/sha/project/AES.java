/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aes.sha.project;

/**
 *
 * @author Sam
 */
/*
 * Implementation of AES crypto algorithm using
 * built in methods/classes from Java
 */
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.util.Base64;

public class AES {

    private SecretKey key;					//Key for encrypt/decrypt
    private final int KEY_SIZE = 256;		//length of key, constant
    private final int DATA_LENGTH = 128;	//USED IN GCM BLOCK CIPHER, UNUSED FOR CBC
    private Cipher encryptionCipher;		//Java Cipher class, where the magic happens

    /*
	 * Turns byte array back into a string, used after message is encoded
	 * used at the end of encryption function
     */
    public String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /*
	 * Takes in an encrypted string, and decodes it back into a byte array
	 * used in decryption function
     */
    public byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    /*
     * This is basically just a constructor but named
     * Sets the keyGenerator class to AES mode, with a 256-bit size key
     * Sets the AES class objects key to the generated key
     */
    public void init() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(KEY_SIZE);

        key = keyGen.generateKey();
    }

    /*
	 * Takes in a string of data
	 * sets the cipher class object to AES with CBC blocks
	 * 
	 * returns the byte array as a string
     */
    public String encryptString(String data) throws Exception {
        byte[] dataInBytes = data.getBytes();

        encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = encryptionCipher.doFinal(dataInBytes);

        return encode(encryptedBytes);
    }

    public byte[] encryptFile(byte[] data) throws Exception {
        encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encryptedBytes = encryptionCipher.doFinal(data);

        return encryptedBytes;
    }

    /*
	 * Takes in an encrypted string, turns it into an array of bytes
	 * 
	 * Notice there is 2 kinds of Block ciphers being used, the one online was GCM
	 * but we didn't go over that in class so I changed it to CBC,
	 * Can ask if using GCM can be used as it's probably more secure anyways
	 * 
	 * returns the decrypted byte array as a string
     */
    public String decryptString(String encryptedData) throws Exception {
        byte[] dataInBytes = decode(encryptedData);

        Cipher decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        // GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        IvParameterSpec IV = new IvParameterSpec(encryptionCipher.getIV());

        decryptionCipher.init(Cipher.DECRYPT_MODE, key, IV);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);

        return new String(decryptedBytes);
    }

    public byte[] decryptFile(byte[] data) throws Exception {
        byte[] dataInBytes = data;

        Cipher decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        // GCMParameterSpec spec = new GCMParameterSpec(DATA_LENGTH, encryptionCipher.getIV());
        IvParameterSpec IV = new IvParameterSpec(encryptionCipher.getIV());

        decryptionCipher.init(Cipher.DECRYPT_MODE, key, IV);
        byte[] decryptedBytes = decryptionCipher.doFinal(dataInBytes);

        return decryptedBytes;
    }
}
