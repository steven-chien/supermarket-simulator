import java.util.*;
import java.io.*;

class Generator extends CustomerCreator implements Listener
{
	private int limit;

	public Generator(int limit) {
		this.limit = limit;
	}

	public void execute(Simulator simulator, Event event) {
		//generate new customers if time is less than 10
		if(simulator.getTime()<limit && (event instanceof SimulatorStartEvent || event instanceof GenerateCustomerEvent)) {
			double currentTime = simulator.getTime();
			//double randomVariable = Math.random();

			//insert itself and the en queue event into the event list
			simulator.addEvent(new EnQueueEvent(currentTime,createCustomer(simulator.getTime())));
			simulator.addEvent(new GenerateCustomerEvent(currentTime));
		}
	}
}
