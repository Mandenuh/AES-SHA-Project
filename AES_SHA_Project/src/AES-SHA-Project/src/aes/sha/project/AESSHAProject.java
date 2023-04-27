/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aes.sha.project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 *
 * @author Sam
 */
public class AESSHAProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AES aes_encryption = new AES();
            aes_encryption.init();

            byte[] fileData, fileDataEncrypted;
            Path path = Paths.get("C:\\Users\\Sam\\Documents\\test.txt");

            fileData = Files.readAllBytes(path);
            fileDataEncrypted = aes_encryption.encryptFile(fileData);
            
            System.out.println(aes_encryption.encode(fileData));
            
            System.out.println(aes_encryption.encode(fileDataEncrypted));
            
            System.out.println(SHA3.hash(fileDataEncrypted));
            System.out.println(aes_encryption.encode(aes_encryption.decryptFile(fileDataEncrypted)));

            //System.out.println(fileDataEncrypted);
            //String encryptedData = aes_encryption.encryptString("Hello, welcome to the encryption world");
            //String decryptedData = aes_encryption.decryptString(encryptedData);
            //System.out.println("Encrypted Data : " + encryptedData);
            //System.out.println("Decrypted Data : " + decryptedData);
        } catch (Exception ignored) {
        }
    }

}
