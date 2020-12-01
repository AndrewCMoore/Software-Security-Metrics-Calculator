package weibullGenerator;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.WeibullDistribution;

public class GenerateWeibullDistributionData {

	public static double WeibullDistr(double alpha, double beta) {
		  return  new WeibullDistribution(alpha, beta).sample();
		}

	
	
	public static void main(String[] args) {
		
		String absolutePath = System.getProperty("user.dir"); System.out.println(absolutePath);
		ArrayList<String> FilesPaths = new ArrayList<>();
		FilesPaths.add("C1.txt");
		FilesPaths.add("C2.txt");
		FilesPaths.add("C3.txt");
		FilesPaths.add("WS1.txt");
		FilesPaths.add("WS2.txt");
		FilesPaths.add("WS3.txt");
		
		
		ArrayList<Double> WeibullDistributionParamaters = new ArrayList<>();
		WeibullDistributionParamaters.add(1.09600229615940); //c1 alpha
		WeibullDistributionParamaters.add(10.7250489541748); //c1 beta
		WeibullDistributionParamaters.add(1.09600766386768); //c2 alpha
		WeibullDistributionParamaters.add(16.0876383178756); //c2 beta
		WeibullDistributionParamaters.add(1.03269263226412); //c3 alpha
		WeibullDistributionParamaters.add(20.9019593805947); //c3 beta
		WeibullDistributionParamaters.add(1.02396073105742); //ws1 alpha
		WeibullDistributionParamaters.add(4.65209941000056); //ws1 beta
		WeibullDistributionParamaters.add(0.922808316137963); //ws2 alpha
		WeibullDistributionParamaters.add(10.6815122330479); //ws2 beta
		WeibullDistributionParamaters.add(1.05214687927421); //ws3 alpha
		WeibullDistributionParamaters.add(8.98290847458538); //ws3 beta		
		
		
		double alpha = 0,beta = 0;
		String filename="";
		for (int i=0;i<6;i++) {
			alpha=WeibullDistributionParamaters.get(i*2);
			beta=WeibullDistributionParamaters.get(i*2+1);
			
			for (int j=0;j<300;j++) {
		//System.out.println(WeibullDistr(1.09600229615940,10.7250489541748));}
				
				System.out.println(alpha);
				System.out.println(beta);				
				try {
		            FileWriter writer = new FileWriter(FilesPaths.get(i), true);
		            writer.write(Double.toString(WeibullDistr(alpha,beta)));
		            writer.write("\r\n");  
		            writer.close();
		        } catch (IOException e) {e.printStackTrace();}
			}
		}
	}

}
