package ssmc;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Attribute {
	private CompilationUnit compilationUnit;
	private boolean finalized;
	private String Identifier;
	private int lineNum;
	private int links;
	private String modifier;
	private int usage;

	public Attribute(String identifier, CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
		this.Identifier = identifier;
		this.modifier = "";
		this.usage = 0;
		this.finalized = false;
		this.links = 0;
		this.lineNum = 0;
	}

	public void addUsage() {
		this.usage += 1;
	}

	public CompilationUnit getCompilationUnit() {
		return compilationUnit;
	}

	public String getIdentifier() {
		return Identifier;
	}

	public int getLineNum() {
		return lineNum;
	}

	public int getLinks() {
		return links;
	}

	public String getModifier() {
		return modifier;
	}

	public int getUsage() {
		return usage;
	}

	public boolean isFinalized() {
		return finalized;
	}

	public void setFinalized(boolean finalized) {
		this.finalized = finalized;
	}

	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}

	public void setLineNum(int lineNum) {
		this.lineNum = lineNum;
	}

	public void setLinks(int links) {
		this.links = links;
	}

	/**
	 * The modifiers set values important to this project are: Public: 1 Private: 2
	 * Protected: 4 Static: 8 Final: 16 Synchronized: 32
	 * 
	 * This method will calculate the log_2() of the input function this will then
	 * be subtracted from the value until all parameters are set
	 * 
	 * @param the      attribute value to be set
	 * @param modifier input value from the getModifiers() method
	 * @return
	 */
	public void setModifier(int modifier) {
		int value = modifier;

		this.modifier = "";
		// Case Statement for setting Attribute a's Modifier and Finalized variables
		while (value > 0) {
			// Finds the highest base 2 value, that is the modifier
			int result = (int) (Math.log(value) / Math.log(2));
			switch (result) {
			case (0):
				this.modifier += "Public ";
				break;
			case (1):
				this.modifier += "Private ";
				break;
			case (2):
				this.modifier += "Protected ";
				break;
			case (3):
				this.modifier += "Static ";
				break;
			case (4):
				this.setFinalized(true);
				break;
			case (6):
				this.modifier += "Volatile ";
				break;
			case (7):
				this.modifier += "Transient ";
				break;
			default:
				break;
			}
			// Subtract the value from the value and repeat:
			value = (int) (value - Math.pow(2d, result));
		}
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Override
	public String toString() {
		return "Attribute [Identifier=" + Identifier + ", modifier=" + modifier + ", usage=" + usage + ", finalized="
				+ finalized + ", compilationUnit=" + compilationUnit + ", links=" + links + ", lineNum=" + lineNum
				+ "]";
	}

}
