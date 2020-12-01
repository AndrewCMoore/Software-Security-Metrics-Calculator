import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.security.SecureRandom;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class problem4b extends Frame implements ActionListener {

	private Label pwLabel;
	private Label idLabel;
	private TextField pwTF;
	private TextField idTF;
	private Button submit;
	private TextField errorOutput;
	private Label errorlbl;
	private String username;
	private String password;
	private static final String filename = "passwd.txt";
	
	public problem4b() {
		
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
		
		
		submit = new Button("Login");
		submit.addActionListener(this);
		add(submit);
		
		setTitle("Login");
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



	public static void main(String[] args) {
		problem4b b = new problem4b();
		b.gui();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		this.username = idTF.getText();
		this.password = pwTF.getText();
		
		// This will give us the salt value, and the hashed password
		ArrayList<byte[]> record = this.retrieveRecord(username);
		/*
		 * [0] is ID
		 * [1] is hash
		 * [2] is salt 
		 * [3] is userIDnum
		 * [4] is jobIdentifier
		 */
		byte[] storedHash = record.get(1);
		byte[] generatedHash = this.generateHash(record.get(2), this.password.getBytes(StandardCharsets.UTF_8));
		
		if(Arrays.equals(storedHash, generatedHash)) {
			errorOutput.setText("User successfully logged in");
		} else { 
			errorOutput.setText("Username/password is incorrect, please try again");
		}
	}
}
