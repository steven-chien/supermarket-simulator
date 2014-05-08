import java.util.*;
import java.io.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

		if(args.length!=1) {
			System.out.println("usage: java SimulatorRuntime (time limit)");
			System.exit(0);
		}
		
		Event simulatorStart = new SimulatorStartEvent(0.0);	//initializing event
		Simulator simulator = new Simulator(simulatorStart);	//create simulator with initial event
		Listener cashier = new Cashier();						//create listener cashier
		Listener customerGenerator = new Generator(Integer.parseInt(args[0]));			//create customer generator

		simulator.addListener(customerGenerator);
		simulator.addListener(cashier);

		simulator.start();

		//simulation ends, report transaction processed
		System.out.println(cashier);
	}
}