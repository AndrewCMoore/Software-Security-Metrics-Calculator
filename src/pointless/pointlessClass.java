package pointless;

public class pointlessClass implements pointlessInterface {
	
	public int globalInteger;
	
	public pointlessClass() {}
	public pointlessClass(int i) {globalInteger=i;}
	
	@Override
	public void pointlessMethod() {
		System.out.println("I'm Pointless");		
	}
}
