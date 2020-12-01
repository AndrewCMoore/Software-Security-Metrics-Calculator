package sfm;
import java.io.BufferedReader;      //to parse input data
import java.io.FileReader;			//to read files.
import java.io.IOException;			//requirerd.
import java.util.ArrayList;

import factoryClasses.*;
import pointless.pointlessClass;

public class SimulateFactoryModel {
	//###########################################################################################################################################################
																	//Current Variables 
	//###########################################################################################################################################################
	
	private static final int BUFFER_SIZE = 2; //the current factory model has buffer(queue's) of size 2.
		
	private static final Double TIME_INCREMENT_AMOUNT = 0.001;  //since the historical data is accurate to 3 sig digs after the decimal point. 
																//Time increments in our simulation by the most significant digit's amount.
																//It was found that it takes about 2-4 minits to run a full simulation.
	
	private static final boolean DEBUG = true; //when testing output, we can activate/deactivate degub print statment here.

	private static final int MINITS_TO_SECONDS = 60;
	
	public static String getAbsolutePath() { return System.getProperty("user.dir");} //Acquire current absolute path of the project files.
			
	pointlessClass pc = new pointlessClass();
	//###########################################################################################################################################################	
																	//SetUp Factory Objects Database 	
	//###########################################################################################################################################################

    private static ArrayList<FactoryObject> setUpFactoryObjects(){
        ArrayList<FactoryObject> objects = new ArrayList<FactoryObject>();

        //SetUp Workstations
        WorkStations WS1 = new WorkStations("WorkStation#1", Products.P1, BUFFER_SIZE);
        WS1.worksWith(Components.C1);
        WS1.setServiceTimes(readData(getAbsolutePath()+"/src/historicalData/ws1.dat"));

        WorkStations WS2 = new WorkStations("WorkStation#2", Products.P2, BUFFER_SIZE);
        WS2.worksWith(Components.C1);
        WS2.worksWith(Components.C2);
        WS2.setServiceTimes(readData(getAbsolutePath()+"/src/historicalData/ws2.dat"));

        WorkStations WS3 = new WorkStations("WorkStation#3", Products.P3, BUFFER_SIZE);
        WS3.worksWith(Components.C1);
        WS3.worksWith(Components.C3);
        WS3.setServiceTimes(readData(getAbsolutePath()+"/src/historicalData/ws3.dat"));

        //SetuP inspectors
        Inspectors I1 = new Inspectors("Inspector#1");
        I1.setComponentsForWorkStation(Components.C1, WS1);
        I1.setComponentsForWorkStation(Components.C1, WS2);
        I1.setComponentsForWorkStation(Components.C1, WS3);
        I1.setWorkStationPriority(WS1, 1);
        I1.setWorkStationPriority(WS2, 2);
        I1.setWorkStationPriority(WS3, 3);
        I1.setComponentServiceTimes(Components.C1, readData(getAbsolutePath()+"/src/historicalData/servinsp1.dat"));

        Inspectors I2 = new Inspectors("Inspector#2");
        I2.setComponentsForWorkStation(Components.C2, WS2);
        I2.setComponentsForWorkStation(Components.C3, WS3); //!!!
        I2.setWorkStationPriority(WS2, 1);
        I2.setWorkStationPriority(WS3, 2);
        I2.setComponentServiceTimes(Components.C2, readData(getAbsolutePath()+"/src/historicalData/servinsp22.dat"));
        I2.setComponentServiceTimes(Components.C3, readData(getAbsolutePath()+"/src/historicalData/servinsp23.dat"));

        objects.add(I1);
        objects.add(I2);
        objects.add(WS1);
        objects.add(WS2);
        objects.add(WS3);

        return objects;
    }
		
	//###########################################################################################################################################################
																		//Methods as functions.
	//###########################################################################################################################################################
	
	//currently used to read the historical data files.
	private static ArrayList<Double> readData(String filelocation) {
		if (DEBUG) System.out.println("In: ["+filelocation+"]");
		ArrayList<Double> serviceTimes = new ArrayList<Double>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(filelocation));
			String line = reader.readLine();
			Double serviceTimeInMinutes = new Double (line);
            Double serviceTimeInSeconds = serviceTimeInMinutes * MINITS_TO_SECONDS;
            serviceTimes.add(serviceTimeInSeconds);
            if (DEBUG)System.out.println("First line found in file: ["+serviceTimes.get(0)/MINITS_TO_SECONDS+"]");
            line = reader.readLine();
			while (line != null) {
				
				//if (DEBUG) System.out.println(line);
								
				serviceTimeInMinutes = new Double (line);
                serviceTimeInSeconds = serviceTimeInMinutes * MINITS_TO_SECONDS;
                serviceTimes.add(serviceTimeInSeconds);
                line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {e.printStackTrace();}
		if (DEBUG)System.out.println("Last line found in file: ["+serviceTimes.get(299)/MINITS_TO_SECONDS+"]");
		if (DEBUG) System.out.println("Total Service Times Found: ["+serviceTimes.size()+"]\n");
		return serviceTimes;
	}
	
	
	private static void printSimulationResults(ArrayList<FactoryObject> fo){
        for (FactoryObject object : fo){
            System.out.println(object.printSimulationResults());
        }
    }
	
	//###########################################################################################################################################################
																//Simulate Manufacturing Model.
	//###########################################################################################################################################################	
	
	
    public SimulateFactoryModel() {
    	pc.pointlessMethod();
        ArrayList<FactoryObject> factoryObjects = setUpFactoryObjects();
        boolean done = false;

        //Run simulation until all entities are in either the DONE or BLOCKED state.
        while (!done) {
            ArrayList<ObjectStates> ObjectStatess = new ArrayList<ObjectStates>();             //This is used track the states of all entities for each clock cycle

            //for each Factory Object, update it's current serviceTime
            for (FactoryObject fo : factoryObjects) {
                
            	ObjectStates objectState = fo.getCurrentObjectState();
                ObjectStatess.add(objectState);

                if (objectState != ObjectStates.IDLE) {
                	fo.updateServiceTimeDuration(TIME_INCREMENT_AMOUNT);
                }
            }

            //assume that all objects are done processing their ServiceTimes events.
            //If an object is found to not be idle or restricted form performing their duties
            //Continue the simulation 
            done = true;
            for (ObjectStates objectState : ObjectStatess){
                if (objectState != ObjectStates.IDLE && objectState != ObjectStates.WAITING) {
                    done = false;
                    break;
                }
            }
        }
        printSimulationResults(factoryObjects);
    }	
}
