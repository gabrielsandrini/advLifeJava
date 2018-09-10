package modelo.pojo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {
	public static String stringToMd5(String texto) 
	{
		String hash = texto;
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.update(texto.getBytes(),0,texto.length());
     	    hash = new BigInteger(1,m.digest()).toString(16);

		} 
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		
		return hash;
	 }

}
