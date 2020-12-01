package factoryClasses;
import java.util.HashMap;
//All Factory Objects have things in common thus abstract class. 
public abstract class FactoryObject {

	//###########################################################################################################################################################	
																	//Current Variables 	
	//###########################################################################################################################################################
	
    private String ObjectName;                                            
    private ObjectStates CurrentState;                                      
    private HashMap<ObjectStates,Double> currentStateDuration;               //A running counter of Duration spent at a given state (unit-less)
    private Double remainingServiceDuration;                           			//A running counter to track the Duration remaining for the current service interval
    private Integer CompleatedTasks;                              			//A running counter to track the Number of services that have been completed

    
	//###########################################################################################################################################################	
																	//Constructor  	
    //###########################################################################################################################################################   
    
    
    public FactoryObject(String ObjectName){
        this.ObjectName = ObjectName;
        this.CurrentState = ObjectStates.STARTING_UP;
        this.currentStateDuration = new HashMap<ObjectStates, Double>(); //used to record the time spend in each state. ex, ACTIVE,RESTRICTED,IDLE etc
        this.CompleatedTasks = 0;
    }
    
	//###########################################################################################################################################################	
																		//Methods
    							//mostly getters, seters, record and increment/decrement shared by all curerent Factory Objects.
    //###########################################################################################################################################################   
    
    
    //print the results of an object.
    public abstract String printSimulationResults();
    
    //get Object name
    public String getObjectName(){ return this.ObjectName; } 

    //set and set object state
    public ObjectStates getCurrentObjectState(){ return this.CurrentState; }
    public void setCurrentObjectState(ObjectStates nextObjectState){ this.CurrentState = nextObjectState; }
   
    // get, set and decrement remaining serviceTime's
    public void setRemainingServiceDuration(Double amount){ this.remainingServiceDuration = amount; }
    public Double getRemainingServiceDuration(){return this.remainingServiceDuration;}
    public void decrementRemainingServiceDuration(Double amount){ this.remainingServiceDuration -= amount; }
   
    //increment the number of tasks compleated, in this case a workstation making a product OR an inspector sending a component to a component buffer.
    public void incrementCompleatedTasks(){ this.CompleatedTasks ++; }
    
    //return total# tasks compleated by the Object.
    public Integer getCompleatedTasks(){ return this.CompleatedTasks; }
    
    //update the time to be spent by an Object in a state.
    public abstract void updateServiceTimeDuration(Double interval);
  
    //increment by the duration spent in a spesific current state.
    public void incrementCurrentStateDuration(ObjectStates state, Double amount){
        if(this.currentStateDuration.containsKey(state)){
            Double currentStateDuration = this.currentStateDuration.get(state);
            currentStateDuration += amount;
            this.currentStateDuration.put(state, currentStateDuration);
        } else {
            this.currentStateDuration.put(state, 0.0);
        }
    }    
    
    //return total duration spend in a state. used to determine things like idle time vs time spend doing somthing.
    public Double getTotalStateDuration(){
        Double totalStateDuration = 0.0;
        for (Double stateDuration : this.currentStateDuration.values()){
            totalStateDuration += stateDuration;
        }
        return totalStateDuration;
    }    
    //return duration spent in a state
    public Double getCurrentStateDuration(ObjectStates state){
        if (this.currentStateDuration.containsKey(state)){
            return this.currentStateDuration.get(state);
        }
        return 0.0;
    }
    
}
