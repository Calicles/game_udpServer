package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class IP_reader {
	
	public static String readSocket() {
		String socket= null;
		
		try(BufferedReader reader= 
				new BufferedReader(new FileReader(new File("ressources/ip/ip.txt")))){
			
			socket= reader.readLine();
			
			reader.close();
			
		}catch(Throwable t) {System.out.println(t);}
		
		return socket;
	}

}
