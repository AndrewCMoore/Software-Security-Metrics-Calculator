package ssmc;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Attribute {
	private String Identifier;
	private String modifier;
	private int usage;
	private boolean finalized;
	private CompilationUnit orginFile;
	private int links;
	
	public Attribute(String identifier, CompilationUnit orginFile) {
		this.orginFile = orginFile;
		this.Identifier = identifier;
		this.modifier = "";
		this.usage = 0;
		this.finalized = false;
		this.links = 0;
	}
	
	public void addUsage() {
		this.usage = this.usage + 1;
	}
	
	
	public String getIdentifier() {
		return this.Identifier;
	}
	
	public int getUsage() {
		return this.usage;
	}
	public void setFinalized(boolean b) {
		this.finalized = b;
	}
	
	/**
	 * The modifiers set values important to this project are:
	 * Public: 1
	 * Private: 2
	 * Protected: 4
	 * Static: 8
	 * Final: 16
	 * Synchronized: 32
	 * 
	 * This method will calculate the log_2() of the input function
	 * this will then be subtracted from the value until all 
	 * parameters are set
	 * @param the attribute value to be set
	 * @param modifier input value from the getModifiers() method
	 * @return 
	 */
	public void setModifier(int modifier) {
		int value = modifier;
		// Case Statement for setting Attribute a's Modifier and Finalized varaibles 
		while(value > 0) {
			// Finds the highest base 2 value, that is the modifier
			int result = (int)(Math.log(value)/ Math.log(2));
			switch(result) {
				case(0):
					this.modifier = "Public";
					break;
				case(1):
					this.modifier = "Private";
					break;
				case(2):
					this.modifier = "Protected";
					break;
				case(3):
					this.modifier = "Static";
					break;
				case(4):
					this.setFinalized(true);
					break;
				case(5):
					this.modifier = "Synchronized";
					break;
				default:
					break;
			}
			// Subtract the value from the value and repeat:
			value = (int) (value - Math.pow(2d, result));
		}
	}
	
	
	
	public String toString() {
		String s = new String();
		s += "Identifier: " + this.Identifier + "\n";
		s += "Modifiers: " + this.modifier + "\n";
		/*
		for(int i = 0; i < this.modifier.length; i++) {
			s += "" + this.modifier[i] + "\n";
		}*/
		
		s += "Usage: " + usage + "\n";
		s += "Finalized: ";
		
		if(this.finalized) {
			s += " Final\n";
		} else {
			s += " N/A\n";
		}
					
		return s;
	}
}
