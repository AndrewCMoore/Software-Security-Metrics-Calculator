package factoryClasses;


import java.util.*;

public class Inspectors extends FactoryObject_InheritanceLevel2{
	
	//###########################################################################################################################################################	
																			//Current Variables 	
	//###########################################################################################################################################################  
	private HashMap<Components, ArrayList<WorkStations>> linkComponentsToWorkstationsThatUseThem;  //A hashmap storing the linkage between each component and what workstations use that spesific component
    private HashMap<Components, Queue<Double>> linkComponentsToServiceTimesFromData;               //a hashmap linking components and the 300 (in this case) service times of operation.
    private HashMap<WorkStations, Integer> currentWorkStationPrioritiyPolocies;         //A mapping of priorities to WorkStationes ex:. {W1: 1, W2: 2, W3: 3}
 
    
    private Components currentComponentForInspection;                  //Current Components under inspection
    private Random randomNumber;                                               //Random number generator

    public Inspectors (String name) {
        super(name);
        this.linkComponentsToWorkstationsThatUseThem = new HashMap<Components, ArrayList<WorkStations>>();
        this.linkComponentsToServiceTimesFromData = new HashMap<Components, Queue<Double>>();
        this.currentWorkStationPrioritiyPolocies = new HashMap<WorkStations, Integer>();
        this.randomNumber = new Random();
    }

	//###########################################################################################################################################################	
		//Constructor and a methods used once when Setting Up the workshop. Example:  serviceTimes read from the historialData files. Example2: set priority etc	
    //###########################################################################################################################################################
    public void setComponentServiceTimes(Components Components, ArrayList<Double> serviceTimes){
        pointlessMethod();
        pointlessProtectedMethod();
    	Queue<Double> serviceTimeQueue = new LinkedList<Double>();
        for (Double serviceTime : serviceTimes){
            serviceTimeQueue.add(serviceTime);
        }
        this.linkComponentsToServiceTimesFromData.put(Components, serviceTimeQueue);
    }

    //set inspector priority for the workstations.
    public void setWorkStationPriority(WorkStations WorkStation, Integer priority){this.currentWorkStationPrioritiyPolocies.put(WorkStation, priority);}
    
    //Link what components are requiered for what workstation for the inspectors to know who to send too.
    public void setComponentsForWorkStation(Components Components, WorkStations WorkStation){
        if (this.linkComponentsToWorkstationsThatUseThem.containsKey(Components)){
            ArrayList<WorkStations> WorkStationes = this.linkComponentsToWorkstationsThatUseThem.get(Components);
            WorkStationes.add(WorkStation);
        } else {
            ArrayList<WorkStations> WorkStationes = new ArrayList<WorkStations>();
            WorkStationes.add(WorkStation);
            this.linkComponentsToWorkstationsThatUseThem.put(Components, WorkStationes);
        }
    }
	
    
    //###########################################################################################################################################################	
					//Methods controlling the workshops STATE, recording the STATE'S DURATION and USING COMPONENTS TO PRODUCE PRODUCTS WHEN ACCEPTABLE	
    //###########################################################################################################################################################


    //get serviceTime and state.
    //If the state is ACTIVE and the inspector is still inspeacting (ie service time remaining) decrement remaining service time.
    //if ACTIVE and inspector is done inspecting (service time == 0) then try to put that component onto  workstations buffer depending on inspectors polocies.
    //if inspector is WAITING he was in this state due to the buffers being full, he will keep trying to place a component onto a workstations buffer.
    public void updateServiceTimeDuration(Double interval){
        Double remainingServiceDuration = this.getRemainingServiceDuration();
        ObjectStates currentState = this.getCurrentObjectState();
        this.incrementCurrentStateDuration(currentState, interval);

        if (currentState == ObjectStates.ACTIVE && (remainingServiceDuration <= 0)){
            this.tryToPutComponentToWorkStationsBuffer();
        } else if (currentState == ObjectStates.ACTIVE && (remainingServiceDuration > 0)){
            this.decrementRemainingServiceDuration(interval);
        } else if (currentState == ObjectStates.WAITING){
            this.tryToPutComponentToWorkStationsBuffer();
        } else if (currentState == ObjectStates.IDLE) {
        	//do nothing
        } else {
            this.getComponentsForInspection();
        }
    }

    //aquirers the next component for inspection, if the inspector only has one component type, example: inspector 1,
    //get next component queued and get next service time for that component TYPE
    private void getComponentsForInspection(){
        if(this.linkComponentsToWorkstationsThatUseThem.keySet().size() == 1){
            this.currentComponentForInspection = new ArrayList<Components>(this.linkComponentsToWorkstationsThatUseThem.keySet()).get(0);
            this.setComponentServiceTime();
        
        //Project Spesifications dictate that inspector2 who deals with two components must select them at random.
        // as a result currently, all inspectors, could be changed for future inspectors, will choose a component to get at random.
        } else {
            Integer randomComponentValue = randomNumber.nextInt(this.linkComponentsToWorkstationsThatUseThem.keySet().size());
            this.currentComponentForInspection = new ArrayList<Components>(this.linkComponentsToWorkstationsThatUseThem.keySet()).get(randomComponentValue);
            this.setComponentServiceTime();
        }
    }
    
    //set the service time for the next component to be inspected by the inspector. If no service times left, then no components left then inspector is idle.
    private void setComponentServiceTime(){
        if (!this.linkComponentsToServiceTimesFromData.get(this.currentComponentForInspection).isEmpty()) {
            this.setCurrentObjectState(ObjectStates.ACTIVE);
            this.setRemainingServiceDuration(this.linkComponentsToServiceTimesFromData.get(this.currentComponentForInspection).remove());
        } else {
            this.setCurrentObjectState(ObjectStates.IDLE);
        }
    }

   //inspoector tries to put component onto a workstation with a free buffer following polocies.
    //if the buffer is full, the inspector is WAITING for free space
    //if the buffer has room, the inspector puts it into a workshop and get's next component for inspection.
    private void tryToPutComponentToWorkStationsBuffer(){
        WorkStations WorkStation = getNextWorkStation();
        if (WorkStation != null) {
            WorkStation.addComponentsToComponentBuffer(this.currentComponentForInspection);
            this.incrementCompleatedTasks();
            this.getComponentsForInspection();
        } else {
            this.setCurrentObjectState(ObjectStates.WAITING);
        }
    }

    //place component in smallest queue (routing polocy)
    //in event of a tie place component in workstation with highest priority (priority policy)
    private WorkStations getNextWorkStation(){
        int currentLowestBufferSize = 3; 
        WorkStations possibleWorkStation = null;

        for (WorkStations WorkStation : this.currentWorkStationPrioritiyPolocies.keySet()){
            if (WorkStation.isBufferFull(this.currentComponentForInspection)) {
                Integer currentWorkStationBufferSize = WorkStation.getBufferSize(this.currentComponentForInspection);
                if (currentWorkStationBufferSize < currentLowestBufferSize) {
                	currentLowestBufferSize = currentWorkStationBufferSize;
                    possibleWorkStation = WorkStation;
                } else if (currentWorkStationBufferSize == currentLowestBufferSize) {
                    Integer WorkStationPriority = this.currentWorkStationPrioritiyPolocies.get(WorkStation);
                    Integer candidateWorkStationPriority = this.currentWorkStationPrioritiyPolocies.get(possibleWorkStation);
                    if (WorkStationPriority < candidateWorkStationPriority) {
                    	possibleWorkStation = WorkStation;
                    }
                }
            }
        }
        return possibleWorkStation;
    }
    
	//###########################################################################################################################################################	
					//@override the printSimulationResults() method inherated with a better one for this Factory Object.	
    //###########################################################################################################################################################	
        
    //@Override the method inherated from FactoryObjects. All inspector objects report the following sumulation results.
    public String printSimulationResults() {
        double timeTimeWAITING = this.getCurrentStateDuration(ObjectStates.WAITING);
        double totalTimeAllStates = this.getTotalStateDuration();
        String result = String.format("The Object: [%s]\nPreformed this many component inspections: [%d]\nWas WAITING from working due to the buffers(queue) being full for this long: [%.3f] minutes\nThe Inspectors ACTIVE for a total of: [%.3f] minutes\nWas Idle for: [%%: %.3f] of the total time\n\n", this.getObjectName(), this.getCompleatedTasks(), (timeTimeWAITING/60), (totalTimeAllStates/60), (timeTimeWAITING * 100/ totalTimeAllStates));
        return result;
    }
}
