import java.util.*;
import java.io.*;

class Cashier extends Listener
{
	PointOfSales pos = new PointOfSales();

	public void execute(Simulator simulator, Customer customer) {
		try {
			FileWriter fw = new FileWriter(simulator.getFfileName(),true);

			double currentTime = simulator.getCurrentTime();
			customer.serviceTime = pos.newTransaction(currentTime);
			customer.queuingTime = currentTime - customer.enQueueTime;
			customer.deQueueTime = currentTime + customer.serviceTime;
			simulator.updateCurrentTime(customer.deQueueTime);
			fw.write(customer+"\n");
			fw.close();
		}
		catch(IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
	}
}