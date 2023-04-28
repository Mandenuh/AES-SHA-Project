import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Driver {
	 public static void main(String[] args) {
	        try {
	            AES aes_encryption = new AES();
	            aes_encryption.init();

	            byte[] fileData, fileDataEncrypted;
	            Path path = Paths.get("C:\\Misc_Folder\\plainText.txt");
	            Path encryptedBytesToFile = Paths.get("C:\\Misc_Folder\\cipherTextFile");
	            Path decryptedBytesToFile = Paths.get("C:\\Misc_Folder\\reconstructedTextFile");

	            fileData = Files.readAllBytes(path);
	            fileDataEncrypted = aes_encryption.encryptFile(fileData);
	            
	            
	            System.out.println("Orginial file bytes as string: \t\t" + aes_encryption.encode(fileData));
	            
	            System.out.println("AES Encrypted file bytes as string: \t" + aes_encryption.encode(fileDataEncrypted));
	            
	            System.out.println("Hash signature of encrypted bytes: \t" + SHA3.hash(fileDataEncrypted));
	            
	            Files.write(encryptedBytesToFile, fileDataEncrypted);
	            
	            System.out.println("Decrypted file bytes: \t\t\t" + aes_encryption.encode(aes_encryption.decryptFile(fileDataEncrypted)));
	            
	            Files.write(decryptedBytesToFile, aes_encryption.decryptFile(fileDataEncrypted));

	            //System.out.println(fileDataEncrypted);
	            //String encryptedData = aes_encryption.encryptString("Hello, welcome to the encryption world");
	            //String decryptedData = aes_encryption.decryptString(encryptedData);
	            //System.out.println("Encrypted Data : " + encryptedData);
	            //System.out.println("Decrypted Data : " + decryptedData);
	        } catch (Exception ignored) {
	        }
	    }
}
