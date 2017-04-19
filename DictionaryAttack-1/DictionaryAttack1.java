//My code - Anushreya Mahapatra

import java.io.*;
import java.util.*;

public class DictionaryAttack1 {
	
	
	public static void main(String[] args) throws Exception {
		//Notify the user the program is starting.
		System.out.println("Let's get things started.");
		final long startTime = System.currentTimeMillis() ;
		
		//Load the provided password file into stream and buffer
        File passwords_file = new File("password.txt");
    	FileInputStream password_stream = new FileInputStream(passwords_file);
    	BufferedReader password_buffer = new BufferedReader(new InputStreamReader(password_stream));
     
    	//Initialise Map for non salted passwords
    	Map<String, String> non_salted_passwords = new HashMap<String, String>();
    	
    	//We parse the buffer to extract user account names and passwords
    	String password_file_line = null;
    	while ((password_file_line = password_buffer.readLine()) != null) {
    		String[] splited = password_file_line.split("\\s+");
    		//System.out.println(Arrays.toString(splited)) ;
    		
    		//First case: password hashed with no salt
    		if(splited.length == 3){
    			non_salted_passwords.put(splited[0], splited[2]);
    		}
    		
    		
    	}
    	
    	password_buffer.close();
    	PreComputeHashes.populate();

    	
    	//We first iterate through the non salted passwords
    	Iterator non_salted_passwords_it = non_salted_passwords.entrySet().iterator();
    	while (non_salted_passwords_it.hasNext()) {
    			//We extract the key,value pair from the HashTable entry
    			Map.Entry pair = (Map.Entry)non_salted_passwords_it.next();
    	        String account_name = pair.getKey().toString(); 
    	        String account_password_hash = pair.getValue().toString();

    	        if (PreComputeHashes.hashes_dict.containsKey(account_password_hash))
    	        	System.out.println(account_name+"'s password is '"+PreComputeHashes.hashes_dict.get(account_password_hash)+"'");
    	        
    		}
     

    	
    	//Notify the user our program is done running.
    	final long endTime = System.currentTimeMillis() ;
    	System.out.println("The program terminated.");
    	System.out.println("Time taken "+(endTime-startTime));
	}
}
