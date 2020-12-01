package pointless;

public class pointlessLoops {
	pointlessClass pc = new pointlessClass();
	public int pointlessCounter = 0;
	private static final int ZERO = 0;
	private static final int ONE = 1;
	
	
	public void ifStatement () {
		pc.pointlessMethod();
		if (pointlessCounter == ZERO) {
			pointlessCounter++;
			if (pointlessCounter != ZERO) {
				--pointlessCounter;
			}
		}		
	}
	
	public void doWhileStatment () {		
		do {
			pointlessCounter++; 
			
		} while (pointlessCounter==ZERO);		
	}
	
	public void switchStatment() {
		
		switch (pointlessCounter) {
		
		case ZERO:
			pointlessCounter++;
			break;
		case ONE:
			pointlessCounter--;
		default:
			pointlessCounter = ONE;
			break;
		
		
		}
	}
		
		public void whileStatement() {
			
			while (pointlessCounter == ZERO) {
				pointlessCounter--;
			}
			
			
		}
		
		public void foreachStatment() {
			
			int[]  numbers  = { 125, 132, 95, 0, 110 }; 
			
			for (int num: numbers) {
				if (num == ZERO) pointlessCounter++;
			}
		
		
	}
		
		public void forLoopStatment() {
			
			for (int i=0; i<10;i++) {
				for (int j=0; j<10;j++) {
					for (int k=0; k<10;k++) {						
					}
				}				
			}		
		}
	

}
