import java.util.*;
import java.io.*;

class Generator extends Listener
{
	public void execute(Simulator simulator, Customer customer) {
		if(simulator.getCurrentTime()<simulator.getCustomerSize()) {
			double currentTime = simulator.getCurrentTime();
			//System.out.println("new customer time "+currentTime);
			Customer newCustomer = new Customer(currentTime);
			simulator.addCustomer(newCustomer);
		}
	}
}