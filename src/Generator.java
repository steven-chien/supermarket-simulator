import java.util.*;
import java.io.*;

class Generator extends CustomerCreator implements Listener
{
	public void execute(Simulator simulator, Event event) {
		if(simulator.getTime()<10 && (event instanceof SimulatorStartEvent || event instanceof GenerateCustomerEvent)) {
			double currentTime = simulator.getTime();
			double randomVariable = Math.random();
			simulator.addEvent(new EnQueueEvent(currentTime+randomVariable,createCustomer(simulator.getTime()+randomVariable)));
			simulator.addEvent(new GenerateCustomerEvent(currentTime+randomVariable));
		}
	}
}
