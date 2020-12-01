import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FullPrototype extends Frame implements ActionListener{
	enum Job{
		REGULARCLIENT,
		PREMIUMCLIENT,
		FINANCIALADVISOR,
		FINANCIALPLANNER,
		INVESTMENTADVISOR,
		COMPLIANCEOFFICER,
		TELLER,
		TECHNICALSUPPORT
	}
	enum Access{
		NONE,
		READ,
		WRITE,
		OWN
	}

	private Access ClientAccountBalance;
	private Access ClientInvestmentPortfolio;
	private Access FAContactDetails;
	private Access FPContactDetails;
	private Access IAContactDetails;
	private Access PrivateConsumerInstruments;
	private Access MoneyMarketInstruments;
	private Access DerivativesTrading;
	private Access InterestInstruments;
	private boolean givenPermission;
	private SecureRandom random = new SecureRandom();
	private Label pwLabel;
	private Label idLabel;
	private TextField pwTF;
	private TextField idTF;
	private Button register;
	private Button login;
	private TextField errorOutput;
	private Label errorlbl;
	private String username;
	private String password;
	private static final String filename = "passwd.txt";

	
	public FullPrototype() {
		// Initialize all accesses to none 
		this.ClientAccountBalance = Access.NONE;
		this.ClientInvestmentPortfolio = Access.NONE;
		this.FAContactDetails = Access.NONE;
		this.FPContactDetails = Access.NONE;
		this.IAContactDetails = Access.NONE;
		this.PrivateConsumerInstruments = Access.NONE;
		this.MoneyMarketInstruments = Access.NONE;
		this.DerivativesTrading = Access.NONE;
		this.InterestInstruments = Access.NONE;
		this.givenPermission = false;
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
		
		
		register = new Button("Register");
		register.addActionListener(this);
		add(register);
		
		login = new Button("Login");
		login.addActionListener(this);
		add(login);
		
		
		setTitle("Prototype");
		setSize(300,200);
		setVisible(true);
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
	private void AccessLevels(Job job) {
		switch(job) {
		case REGULARCLIENT:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.READ;
			this.FAContactDetails = Access.READ;
			break;
		
		case PREMIUMCLIENT:
			this.ClientAccountBalance = Access.WRITE;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.FAContactDetails = Access.READ;
			this.FPContactDetails = Access.READ;
			this.IAContactDetails = Access.READ;
			break;
			
		case FINANCIALADVISOR:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.FAContactDetails = Access.WRITE;
			this.PrivateConsumerInstruments = Access.READ;
			break;
			
		case FINANCIALPLANNER:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.FPContactDetails = Access.WRITE;
			this.PrivateConsumerInstruments = Access.READ;
			this.MoneyMarketInstruments = Access.READ;
			break;
			
		case INVESTMENTADVISOR:
			this.ClientAccountBalance = Access.READ;
			this.ClientInvestmentPortfolio = Access.WRITE;
			this.IAContactDetails = Access.WRITE;
			this.PrivateConsumerInstruments = Access.READ;
			this.MoneyMarketInstruments = Access.READ;
			this.InterestInstruments = Access.READ;
			break;
			
		case COMPLIANCEOFFICER:
			this.ClientInvestmentPortfolio = Access.OWN;
			break;
			
		case TELLER:
			if(Calendar.DAY_OF_WEEK == (Calendar.MONDAY | 
										Calendar.TUESDAY | 
										Calendar.WEDNESDAY | 
										Calendar.THURSDAY | 
										Calendar.FRIDAY)
									& ((Calendar.HOUR_OF_DAY > 9) 
									&  (Calendar.HOUR_OF_DAY <=  17))) {
				this.ClientAccountBalance = Access.READ;
				this.ClientInvestmentPortfolio = Access.READ;
			}
			break;
			
		case TECHNICALSUPPORT:
			if(this.givenPermission) {
				this.ClientAccountBalance = Access.READ;
				this.ClientInvestmentPortfolio = Access.READ;
			}
						
			break;
			
		default:
			break;
			
		}
	}
	public Access[] getAccess(Job job) {
		this.AccessLevels(job);
		Access[] accessArray = new Access[9];
		accessArray[0] = this.ClientAccountBalance;
		accessArray[1] = this.ClientInvestmentPortfolio;
		accessArray[2] = this.FAContactDetails;
		accessArray[3] = this.FPContactDetails;
		accessArray[4] = this.IAContactDetails;
		accessArray[5] = this.PrivateConsumerInstruments;
		accessArray[6] = this.MoneyMarketInstruments;
		accessArray[7] = this.DerivativesTrading;
		accessArray[8] = this.InterestInstruments;
		return accessArray;
		
	}
	public String accessString(Job job) {
		Access[] accessArray = this.getAccess(job);

		String s = "";
		s += "Client Account Balance: " + accessArray[0] + "\n";
		s += "Client Investment Portfolio: " + accessArray[1] + "\n";
		s += "Financial Advisor Contact Details: " + accessArray[2] + "\n";
		s += "Financial Planner Contact Details: " + accessArray[3] + "\n";
		s += "Invesment Advisor Contact Details: " + accessArray[4] + "\n";
		s += "Private Consumer Instruments: " + accessArray[5] + "\n";
		s += "Money Making Instruments: " + accessArray[6] + "\n";
		s += "Derivatives Trading: " + accessArray[7] + "\n";
		s += "Interest Instruments: " + accessArray[8] + "\n";
		
		
		return s;
		
		
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
		Pattern licensePattern = Pattern.compile("^[A-Z]{4} [0-9]{3}$");
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
	private byte[] generateSalt() {
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		return salt;
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

	public static void main(String[] args) {
		FullPrototype o = new FullPrototype();
		o.gui();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.username = idTF.getText();
		this.password = pwTF.getText();
		
		if(arg0.getSource() == register) {
			if(this.checkID(this.username)) {}
			else if(this.checkPW(this.password)) {}
			else {
				// register user
				this.addRecord(this.username, this.password, 1000);
				errorOutput.setText("User successfully registered");
			}
		}
		if(arg0.getSource() == login) {
			// This will give us the salt value, and the hashed password
			ArrayList<byte[]> record = this.retrieveRecord(username);
			
			byte[] storedHash = record.get(1);
			byte[] generatedHash = this.generateHash(record.get(2), this.password.getBytes(StandardCharsets.UTF_8));
			
			if(Arrays.equals(storedHash, generatedHash)) {
				errorOutput.setText("User successfully logged in");
				// Print all user data to the command line 
				// User ID, Role/Attributes/Labels
				//List of access rights or permissions
				String userID = new String(record.get(0), StandardCharsets.UTF_8);
				String userIDnum = new String(record.get(3), StandardCharsets.UTF_8);
				String jobIdentifier = new String(record.get(4), StandardCharsets.UTF_8);
				String output = new String("User ID: " + userID + "\n");
				output += "User ID# = " + userIDnum + "\n";
				switch(jobIdentifier) {
				case "1000":
					output += "Job Identification# = " + jobIdentifier + " Regular Client\n";
					output += this.accessString(Job.REGULARCLIENT);
					break;
				case "1001":
					output += "Job Identification# = " + jobIdentifier + " Preimum Client\n";
					output += this.accessString(Job.PREMIUMCLIENT);
					break;
				case "2000":
					output += "Job Identification# = " + jobIdentifier + " Finanical Advisor\n";
					output += this.accessString(Job.FINANCIALADVISOR);
					break;
				case "2001":
					output += "Job Identification# = " + jobIdentifier+ " Finanical Planner\n";
					output += this.accessString(Job.FINANCIALPLANNER);
					break;
				case "2002":
					output += "Job Identification# = " + jobIdentifier + " Investment Advisor\n";
					output += this.accessString(Job.INVESTMENTADVISOR);
					break;
				case "2003":
					output += "Job Identification# = " + jobIdentifier + " Compliance Officer\n";
					output += this.accessString(Job.COMPLIANCEOFFICER);
					break;
				case "3000":
					output += "Job Identification# = " + jobIdentifier + " Teller\n";
					output += this.accessString(Job.TELLER);
					break;
				case "5000":
					output += "Job Identification# = " + jobIdentifier + " Techincal Support\n";
					output += this.accessString(Job.TECHNICALSUPPORT);
					break;
				default:
					output += "Job Identification# = " + jobIdentifier + " unable to identify\n";
					break;
				}
				System.out.println(output);
			} else { 
				errorOutput.setText("Username/password is incorrect, please try again");
			}
		}
	}
}