import java.security.*;
import java.io.*;
import java.util.*;
import java.lang.StringBuilder;
import javax.xml.bind.DatatypeConverter;

class PreComputeHashes {

    //to store encrypted version of every word in the dictionary
    public static Map<String, String> hashes_dict = new HashMap<String, String>();


	//function used in original code for encryption
	public static String bytesToHex(byte[] b) {
				 char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
					                 '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
				 StringBuffer buf = new StringBuffer();
				 for (int j=0; j<b.length; j++) {
					 buf.append(hexDigit[(b[j] >> 4) & 0x0f]);
					 buf.append(hexDigit[b[j] & 0x0f]);
				  }
				  return buf.toString();
	}
	
	//This method takes a string, computes its SHA-1 hash, 
	//and converts it into HEX using the bytesToHex method
	public static String stringToSha1(String input) throws Exception {
				//Setup a MessageDigest for SHA1 
				//System.out.println(input);
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.reset();
			//Setup the MessageDigest with our input string
			md.update(input.getBytes("UTF-8"));
				
			//Convert the string's digest to HEX
			String sha1 = bytesToHex(md.digest());

				return sha1;
	
	
	}
	
	//populate hashes_dict to store hashed values of all words in the dictionary
	public static void populate() throws Exception{
	
	//Load the provided Dictionary into stream and buffer
        File fin = new File("english.0");
    	FileInputStream fis = new FileInputStream(fin);
    	
    	//Construct BufferedReader from InputStreamReader
    	BufferedReader br = new BufferedReader(new InputStreamReader(fis));
     
    	//We parse the buffer to populate our hashes_dict (hashes for word , reversed_word 
    	String line = null;
    	while ((line = br.readLine()) != null) {
    		
    	        //for line
    	        hashes_dict.put(stringToSha1(line),line);
    	        
    	        //for reversed line
    	        String reversed_line = new StringBuilder(line).reverse().toString();
    	        hashes_dict.put(stringToSha1(reversed_line),reversed_line);
    	        
    	        //for line without vowels
    	        String line_without_vowels = line.replaceAll("[AEIOUaeiou]", "");
    	        hashes_dict.put(stringToSha1(line_without_vowels),line_without_vowels);
    		}
    		
    	}

	//not required since we don't execute main anyway
	public static void main(String[] args) throws Exception{
	
		populate() ;
    		
   }
}
