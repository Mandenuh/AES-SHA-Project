/*
 * Driver file for testing code
 */


public class Driver 
{
	public static void main(String[] args) 
	{
        try 
        {
            AES aes_encryption = new AES();
            aes_encryption.init();
            
            String encryptedData = aes_encryption.encrypt("Hello, welcome to the encryption world");
            String decryptedData = aes_encryption.decrypt(encryptedData);

            System.out.println("Encrypted Data : " + encryptedData);
            System.out.println("Decrypted Data : " + decryptedData);
        } 
        catch (Exception ignored) {}
    }
}
