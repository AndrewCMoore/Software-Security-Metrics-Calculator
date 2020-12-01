import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class problem2c {
	
	private final static String filename = "passwd.txt";
	private SecureRandom random = new SecureRandom();
	
	public problem2c() {
	}
	
	private List<String> readFile(String filename)
	{
	  List<String> records = new ArrayList<String>();
	  try
	  {
	    BufferedReader reader = new BufferedReader(new FileReader(filename));
	    String line;
	    while ((line = reader.readLine()) != null)
	    {
	      records.add(line);
	    }
	    reader.close();
	    return records;
	  }
	  catch (Exception e)
	  {
	    System.err.format("Exception occurred trying to read '%s'.", filename);
	    e.printStackTrace();
	    return null;
	  }
	}
	
	private void writeFile(byte[] ID, byte[] hash, byte[] salt, int jobIdentifier, String filename) {
		/*
		 * The format of the file is 
		 * userID:saltedhash:saltvalue:userID#:Job#Identifier
		 * 
		 * userID: user login ID
		 * saltedhash : hashed value of the appended password and salt value
		 * salt value: randomly generate value associated to the password
		 * userID#: randomly generated value
		 * Job#Identifier: Signifies which jobs the person has and the security implications that gives them. 
		 * 1000: Regular Client
		   1001: Premium Client
		   2000: Financial Advisor
		   2001:Financial Planner
		   2002: Investment Analyst
		   2003: Compliance Officer
		   3000: Teller
	   	   5000: Technical Support
		 * 
		 */
		//Convert values to string for text document
		String IDString = new String(ID, StandardCharsets.UTF_8);
		// Binary values are best encoded with Base64
		String hashString = Base64.getEncoder().encodeToString(hash);
		String saltString = Base64.getEncoder().encodeToString(salt);
		int userIDnum = random.nextInt(1000);
		
		

		try ( FileWriter fileWriter = new FileWriter(filename, true);
			  BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			  PrintWriter printWriter = new PrintWriter(bufferedWriter);) 
		{
			  // Each password file contains the user ID, the hashed password, and the salt value
			  printWriter.println(IDString + ":" + hashString+ ":" + saltString + ":" + userIDnum + ":" + jobIdentifier);
			
		} catch (IOException e) {
			System.out.println("There has been a failure");
			e.printStackTrace();
		}
	}
	
	public byte[] generateSalt() {
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	public byte[] generateHash(byte[] salt, byte[] password) {
		// SHA-256 encoding statement
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// saltedPassword = salt with password appended on the end
		byte[] saltedPassword = new byte[salt.length + password.length];
		System.arraycopy(salt, 0, saltedPassword, 0, salt.length);
		System.arraycopy(password, 0, saltedPassword, salt.length, password.length);
		
		// Hash the new salted password
		byte[] hash = digest.digest(saltedPassword);
		
		return hash;
	}
	
	public void addRecord(String ID, String password, int jobIdentifier) {
		
		byte[] IDBytes = ID.getBytes(StandardCharsets.UTF_8);
		byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
		
		// Create randomly generated salt value
		byte[] salt = generateSalt();
		
		// Generate the hashed password
		byte[] hash = generateHash(salt, passwordBytes);
		
		// Write data to the file
		writeFile(IDBytes, hash, salt, jobIdentifier, filename);
			
	}
	
	public ArrayList<byte[]> retrieveRecord(String ID) {
		byte[] IDBytes, hash, salt, userIDnumBytes, jobIdentifierBytes;
		ArrayList<byte[]> returnList = new ArrayList<byte[]>();
		
		List<String> fileValues = this.readFile(filename);

		for(int i = 0; i < fileValues.size(); i++) {
			if(fileValues.get(i).contains(ID)) {
				
				String[] s= fileValues.get(i).toString().split(":");
				
				// Covert the values from strings back to byte arrays
				IDBytes = s[0].getBytes();
				hash = Base64.getDecoder().decode(s[1]);
				salt = Base64.getDecoder().decode(s[2]);
				userIDnumBytes = s[3].getBytes();
				jobIdentifierBytes = s[4].getBytes();
								
				// Add the results to a ArrayList of byte arrays
				returnList.add(IDBytes);
				returnList.add(hash);
				returnList.add(salt);
				returnList.add(userIDnumBytes);
				returnList.add(jobIdentifierBytes);
				
				
				return returnList;
			}
		}
		// If the user ID is not found
		System.out.println("The user ID was not found");
		return null;
	}
	
	
	public static void main(String[] args) {
		problem2c c = new problem2c();
		c.addRecord("Coolman3", "password", 1000);
		c.retrieveRecord("Coolman3");
		System.out.println(c.readFile(filename));
	}
}
