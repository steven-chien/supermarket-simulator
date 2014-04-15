import java.util.*;
import java.math.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

        Simulator simulator = new Simulator();

		SimulatorStartEvent startEvent = new SimulatorStartEvent(runtimeQueue);

		runtimeQueue.add(startEvent);
		simulator.start();
	}
}

class Event
{
	double enQueueTime = 0.0;
	double deQueueTime = 0.0;
	double processtime = 0.0;
	
	public Event(double euQueueTime) {
	    this.enQueueTime = enQueueTime;
	}
}

class Simulator
{
    private ArrayList<Event> eventQueue = new ArrayList<Event>();
    /* declare servers */
    double time = 0.0;
    private Event currentEvent;
    
    public void addEvent(Event event) {
        eventQueue.add(event);
        /* sort the queue here */
    }
    
    public void start() {
        while(eventQueue.size()>0) {
            currentEvent = eventQueue.remove(0);
            currentEvent.execute();
            this.time = currentEvent.deQueueTime;
        }
    }
}