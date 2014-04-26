import java.util.*;
import java.io.*;

class Cashier implements Listener
{
	PointOfSales pos = new PointOfSales();

	public void execute(Simulator simulator, Event event) {
		if(event instanceof EnQueueEvent) {
			double currentTime = simulator.getTime();
			Customer customer = event.customer;
			customer.serviceTime = pos.newTransaction(currentTime);
			customer.deQueueTime = customer.serviceTime + customer.enQueueTime;
			simulator.addEvent(new DeQueueEvent(customer.deQueueTime, customer));
		}
	}
}