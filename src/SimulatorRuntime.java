import java.util.*;
import java.io.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

		Event simulatorStart = new SimulatorStartEvent(0.0);
		Simulator simulator = new Simulator(simulatorStart);
		Listener cashier = new Cashier();
		//Listener delivery = new DeliveryService();
		Listener customerGenerator = new Generator();

		
		simulator.addListener(customerGenerator);
		simulator.addListener(cashier);
		//simulator.addListener(delivery);

		simulator.start();
	}
}