package ma.meditel.reporting.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHelper {

	
	/*public static void main(String[] str)
	{
		System.out.println(PasswordHelper.hashingString("123456"));
	}*/
	//source du code : Java SHA Hashing Example _ http://www.mkyong.com/java/java-sha-hashing-example/
	public static String hashingString (String password)
	{
		
		
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(password.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
 
//        System.out.println("Hex format : " + sb.toString());
 
        //convert the byte to hex format method 2
        StringBuffer hexString = new StringBuffer();
    	for (int i=0;i<byteData.length;i++) {
    		String hex=Integer.toHexString(0xff & byteData[i]);
   	     	if(hex.length()==1) hexString.append('0');
   	     	hexString.append(hex);
    	}
    	return hexString.toString();
    }
	
}
