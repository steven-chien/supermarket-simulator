import java.util.*;
import java.io.*;

class Cashier implements Listener
{
	PointOfSales pos = new PointOfSales();

	public void execute(Simulator simulator, Event event) {
		//check if event is of correct type
		if(event instanceof EnQueueEvent) {
			double currentTime = simulator.getTime();	//get current time from simulator
			Customer customer = event.customer;			//get customer of the event
			customer.serviceTime = pos.newTransaction(currentTime);	//post a new transaction, simple factory
			customer.deQueueTime = customer.serviceTime + customer.enQueueTime;	//calculate new time
			Event left = new DeQueueEvent(customer.deQueueTime, customer);	//dequeue event
			simulator.setTime(customer.deQueueTime);	//update time of simulator after this event
		}
	}

	@Override public String toString() {
		return "Number of transaction recorded: "+pos.getTransactionCount();
	}
}