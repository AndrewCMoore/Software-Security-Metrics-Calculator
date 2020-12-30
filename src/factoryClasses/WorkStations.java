package factoryClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.LinkedList;


public class WorkStations extends FactoryObject  {

	//###########################################################################################################################################################	
																		//Current Variables 	
	//###########################################################################################################################################################	
	public static final int SECONDS_TO_HOURS  = 3600;
    @SuppressWarnings("unused")
	private Products products;                                          //product workstation uses
    private int bufferLimit;                                             //Maximum buffer size
    private HashMap<Components, Integer> componentsBuffers;             //records #components in the queue "value()" for each component type "key"
    private Queue<Double> serviceTimes;                                 //A queue of service times times

	//###########################################################################################################################################################	
					//Constructor and a methods used once when Setting Up the workshop. Example:  serviceTimes read from the historialData files.	
    //###########################################################################################################################################################	   
    
    
    public WorkStations(String name, Products products, int queuelimit){
        super(name); //since we inherited from FactoryObject
        this.products = products; //set the workstations products
        this.bufferLimit = queuelimit;	//set the buffer (queue) limit for each component being sent from inspector to workstation
        this.componentsBuffers = new HashMap<Components, Integer>(); //set buffer size limit "value()" for X component
    }

    //The serviceTimes read from the historicalData files are sent to the workstations
    public void setServiceTimes(ArrayList<Double> serviceTimes){
        Queue<Double> serviceTimeQueue = new LinkedList<Double>();
        for (Double serviceTime : serviceTimes){
            serviceTimeQueue.add(serviceTime);
        }this.serviceTimes = serviceTimeQueue;
    }

    //workstation works with component X and current number of that component "0"
    public void worksWith(Components Components){
        this.componentsBuffers.put(Components, 0);
    }

	//###########################################################################################################################################################	
					//Methods controlling the workshops STATE, recording the STATE'S DURATION and USING COMPONENTS TO PRODUCE PRODUCTS WHEN ACCEPTABLE	
    //###########################################################################################################################################################
    
    
    //return number of component currently in the buffer for that component, ex buffer for C1 has 2 C1 components.
    public int getBufferSize(Components Components){return componentsBuffers.get(Components);}

    //if the component buffer for X component is not full, then add the component to the buffer.
    public void addComponentsToComponentBuffer(Components Components){
        if (this.componentsBuffers.get(Components) < this.bufferLimit) {
            Integer componentsBuffer = this.componentsBuffers.get(Components);
            componentsBuffer++;
            this.componentsBuffers.put(Components, componentsBuffer); //update how many components are in X buffer for X component.
        }
    }

    //if the buffer for a component the workstation's working is exists and the buffer for that component is not full then
    public boolean isBufferFull(Components Components){
        if (this.componentsBuffers.containsKey(Components) && (this.componentsBuffers.get(Components) < bufferLimit)){return true;} else {return false;}
    }

    
    //update the sertive time spent in a state and if in a spesific state, ex: ACTIVE, preform requirers duties. ex, make a product.
    public void updateServiceTimeDuration(Double amount){
        Double remainingServiceDuration = this.getRemainingServiceDuration();
        ObjectStates currentState = this.getCurrentObjectState();
        this.incrementCurrentStateDuration(currentState, amount);

        if (currentState == ObjectStates.ACTIVE && (remainingServiceDuration <= 0)){
            this.updateComponentBuffers();
            this.incrementCompleatedTasks();
            this.tryToMakeProduct();
        } else if (currentState == ObjectStates.ACTIVE && (remainingServiceDuration > 0)){
            this.decrementRemainingServiceDuration(amount);
        } else if (currentState == ObjectStates.WAITING){
            this.tryToMakeProduct();
        } else if (currentState == ObjectStates.IDLE) {
    	//do nothing
        }else {
           this.tryToMakeProduct();
        }
    }

    //check component buffer 's value from key value pair to see how many comonents are avalible in the buffer queue for X component.
    //if all buffers have > 0 values (components waiting in the buffer (queue) for X workstation.
    //then since we assigned buffers needed for each of the 3 workstations, if all the buffers/queues for the components are >0 we know we can assemble a product.
    //if it can assemple a product the workstation is "Active" for the sertivetime read from the historicalData.
    //else the workstation is WAITING before making any products since one or more component buffers are empty (bufferValue==0)
    private void tryToMakeProduct(){
        boolean ComponentssAvailableToAssembleProducts = true;

        for (Integer bufferValue : this.componentsBuffers.values()){
            if (bufferValue == 0){
                ComponentssAvailableToAssembleProducts = false;
                break;
            }
        }
        if (ComponentssAvailableToAssembleProducts){
            this.setCurrentObjectState(ObjectStates.ACTIVE);
            this.setRemainingServiceDuration(this.serviceTimes.remove());
        } else {
            this.setCurrentObjectState(ObjectStates.WAITING);
        }
    }

   //since a product need one of each component, for each component buffer, decrement the buffer value by 1.
    private void updateComponentBuffers(){
        for (Components Components : this.componentsBuffers.keySet()){
            Integer ComponentsBuffer = this.componentsBuffers.get(Components);
            ComponentsBuffer--;
            this.componentsBuffers.put(Components, ComponentsBuffer);
        }
    }
    
    
	//###########################################################################################################################################################	
    							//@override the printSimulationResults() method inherated with a better one for this Factory Object.	
    //###########################################################################################################################################################

    //@Override the method inherated from FactoryObjects. All workstation objects report the following sumulation results.
    public String printSimulationResults(){
        double totalProductsProduced = this.getCompleatedTasks();
        double totalTimeInHours = this.getTotalStateDuration()/SECONDS_TO_HOURS;
        String result = String.format("The Object: [%s]\nProduced a total of: [%.0f] products.\nWith an avarage production of: [%.2f] Products Made/hour\n\n", this.getObjectName(), totalProductsProduced, (totalProductsProduced / totalTimeInHours));
        return result;
    }
}
