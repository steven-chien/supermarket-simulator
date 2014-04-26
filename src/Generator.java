import java.util.*;
import java.io.*;

class Generator implements Listener
{
	public void execute(Simulator simulator, Event event) {
		if(simulator.getTime()<10 && (event instanceof SimulatorStartEvent || event instanceof GenerateCustomerEvent)) {
			double currentTime = simulator.getTime();
			Customer customer;
			double randomVariable = Math.random();
			simulator.addEvent(new EnQueueEvent(currentTime+randomVariable,new Customer(simulator.getTime()+randomVariable)));
			simulator.addEvent(new GenerateCustomerEvent(currentTime+randomVariable));
		}
	}
}