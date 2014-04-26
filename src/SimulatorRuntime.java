import java.util.*;
import java.io.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

		Event simulatorStart = new SimulatorStartEvent(0.0);
		Simulator simulator = new Simulator(simulatorStart);
		Cashier cashier = new Cashier();
		DeliveryService delivery = new DeliveryService();
		Generator customerGenerator = new Generator();
		
		simulator.addListener(customerGenerator);
		simulator.addListener(cashier);
		simulator.addListener(delivery);

		simulator.start();
	}
}