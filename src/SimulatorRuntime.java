import java.util.*;
import java.io.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

    	Simulator simulator = new Simulator(args[0], Integer.parseInt(args[1]));
        Cashier cashier = new Cashier();
        Generator generator = new Generator();
        Customer customer = new Customer(0.0);

        simulator.addCustomer(customer);
        simulator.addListener(generator);
        simulator.addListener(cashier);

	    simulator.start();
	}
}