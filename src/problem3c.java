import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class problem3c extends Frame implements ActionListener {
	private TextField errorOutput;
	private Label errorlbl;
	private SecureRandom random = new SecureRandom();
	private String username;
	private String password;
	/**
	 * Goals:
	 * Design UI
	 * Create enrollment 
	 */
	
	
	private static final String filename = "passwd.txt";
	
	
	
	private Label pwLabel;
	private Label idLabel;
	private TextField pwTF;
	private TextField idTF;
	private Button submit;

	public problem3c() {
		
	}
	public void gui() {
		setLayout(new FlowLayout());
		idLabel = new Label("User ID     ");
		add(idLabel);
		idTF = new TextField(20);
		add(idTF);
	
		pwLabel = new Label("Password");
		add(pwLabel);
		pwTF = new TextField(20);
		add(pwTF);
		
		errorlbl = new Label("Registration Information: ");
		add(errorlbl);
		errorOutput = new TextField(30);
		errorOutput.setEditable(false);
		add(errorOutput);
		
		
		submit = new Button("Enroll");
		submit.addActionListener(this);
		add(submit);
		setTitle("Enroll User");
		setSize(300,200);
		setVisible(true);
		;
	}
	
	private ArrayList<String> readFile(String filename){
		ArrayList<String> records = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
		    String line;
		    while ((line = reader.readLine()) != null) {
		      records.add(line);
		    }
		    reader.close();
		    return records;
			}
		  catch (Exception e) {
		    System.err.format("Exception occurred trying to read '%s'.", filename);
		    e.printStackTrace();
		    return null;
		  }
	}
	private byte[] generateSalt() {
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
	}
	private byte[] generateHash(byte[] salt, byte[] password) {
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
	private ArrayList<byte[]> retrieveRecord(String ID) {
		byte[] IDBytes, hash, salt, userIDnumBytes, jobIdentifierBytes;
		ArrayList<byte[]> returnList = new ArrayList<byte[]>();
		
		ArrayList<String> fileValues = this.readFile(filename);

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
	private void writeFile(byte[] ID, byte[] hash, byte[] salt, int jobIdentifier, String filename) {
		/*
		 * The format of the file is 
		 * userID:saltedhash:saltvalue:userID#:Job#Identifier
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

	public boolean checkID(String ID) {

		if(ID == "") {
			errorOutput.setText("Please input a username");
			return true;
		}
		
		ArrayList<String> fileValues = this.readFile(filename);

		for(int i = 0; i < fileValues.size(); i++) {
			if(fileValues.get(i).contains(ID)) {
				errorOutput.setText("The username is already in use. Please try another one");
				return true;
			}
		}
		return false;
	}
	
	public boolean checkPW(String password) {
		if(password.length() > 12 || password.length() < 8) {
			errorOutput.setText("The password must be 8-12 characters long");
			return true;
		} 
		
		if(this.password == this.username) {
			errorOutput.setText("The password cannot be the same as the user ID");
			return true;
		}
		
		// Checking for character requirements
		boolean upperCase = false;
		boolean lowerCase = false;
		boolean number = false;
		boolean specialCharacter = false;
		String specialCharacters = "!@#$%?*";
		
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				upperCase = true;
			}
			else if(Character.isLowerCase(password.charAt(i))) {
				lowerCase = true;
			}
			else if(Character.isDigit(password.charAt(i))) {
				number = true;
			}
			else if(specialCharacters.contains(Character.toString(password.charAt(i)))){
				specialCharacter = true;
			}
		}
	
		if(!(upperCase && lowerCase && number && specialCharacter)) {
			errorOutput.setText("The password requires an uppercase character, lowercase character, a number, and a special character from the list of '!@#$%?*''");
			return true;
		}
		
		// Check if password resembles phone number aka 10 digits in a row
		Pattern phonePattern = Pattern.compile("[0-9]{10}");
		Matcher phoneMatcher = phonePattern.matcher(password);
		if(phoneMatcher.find()) {
			errorOutput.setText("The password cannot resemble a phone number");
			return true;
		}
		
		// Check if password resembles a date: YYYYMMDD
		Pattern datePattern = Pattern.compile("([12]\\d{3}(0[1-9]|1[0-2])(0[1-9]|[12]\\d|3[01]))");
		Matcher dateMatcher = datePattern.matcher(password);
		if(dateMatcher.find()) {
			errorOutput.setText("The password cannot resemble a date");
			return true;
		}
		
		// Check if password resembles a license plate 
		Pattern licensePattern = Pattern.compile("^[A-Z]{1-4} [0-9]{1-3}$");
		Matcher licenseMatcher = licensePattern.matcher(password);
		if(licenseMatcher.find()) {
			errorOutput.setText("The password cannot resemble a license plate");
			return true;
		}
		
		ArrayList<String> fileValues = this.readFile("weakpasswd.txt");

		for(int i = 0; i < fileValues.size(); i++) {
			if(fileValues.get(i).contains(password)) {
				errorOutput.setText("The password is well known weak password. Please try again");
				return true;
			}
		}	
		return false;
	}
	public static void main(String[] args) {
		problem3c c = new problem3c();
		c.gui();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.username = idTF.getText();
		this.password = pwTF.getText();
		
		if(this.checkID(this.username)) {}
		else if(this.checkPW(this.password)) {}
		else {
			// register user
			this.addRecord(this.username, this.password, 1000);
			errorOutput.setText("User successfully registered");
		}
		
		
	}
}
