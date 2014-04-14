import java.util.*;
import java.math.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

		ArrayList<Event> runtimeQueue = new ArrayList<Event>();
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Simulator simulator = new Simulator(runtimeQueue, customers);
		Server server = new Server(runtimeQueue, customers);
		simulator.server = server;

		SimulatorStartEvent startEvent = new SimulatorStartEvent(runtimeQueue);

		runtimeQueue.add(startEvent);
		simulator.start();
	}
}

class Customer
{
	double enQueueTime = 0.0;
	double serviceTime = 0.0;
	double queuingTime = 0.0;
	double deQueueTime = 0.0;

	@Override public String toString() {
		return "enter queue at: "+enQueueTime+"; service time: "+serviceTime+"; queuing time: "+queuingTime+"; left time: "+deQueueTime;
	}
}

class Event
{
	double time;
	ArrayList<Event> runtimeQueue;

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void run(Simulator simulator) { }
}

class SimulatorStartEvent extends Event
{
	public SimulatorStartEvent(ArrayList<Event> runtimeQueue) {
		this.runtimeQueue = runtimeQueue;
		this.time = 0.0;
	}

	public void run(Simulator simulator) {
		Customer customer = new Customer();
		time = simulator.currentTime() + Math.random();
		customer.enQueueTime = time;
		simulator.addCustomer(customer);
		if(time<10.0) {
			runtimeQueue.add(this);
		}
		//simulator.addCustomer(customer);
		//System.out.println("Added new customer");
	}

}

class Simulator
{
	ArrayList<Event> runtimeQueue;
	ArrayList<Customer> customers;
	private double time = 0.0;
	Server server;

	public Simulator(ArrayList<Event> runtimeQueue, ArrayList<Customer> customers) {
		this.runtimeQueue = runtimeQueue;
		this.customers = customers;
	}

	public double currentTime() {
		return time;
	}

	public void setCurrentTime(double time) {
		this.time = time;
	}

	public void addCustomer(Customer customer) {
		if(customers.size()>0) {
			//System.out.println("added customer");
			customers.add(customer);
		}
		else {
			customers.add(customer);
			server.run(this);
		}
	}

	public void start() {
		while(runtimeQueue.size()>0) {
			System.out.println("current time: "+currentTime());
			Event event = runtimeQueue.remove(0);
			this.time = event.getTime();
			event.run(this);
		}
	}
}

class Server extends Event
{
	Customer currentCustomer;
	ArrayList<Customer> customers;

	public Server(ArrayList<Event> runtimeQueue, ArrayList<Customer> customers) {
		this.runtimeQueue = runtimeQueue;
		this.customers = customers;
	}

	@Override public void run(Simulator simulator) {
		//System.out.println("customer queue size = "+customers.size());
		if(customers.size()>0) {
			currentCustomer = customers.remove(0);
			currentCustomer.serviceTime = Math.random();
			this.time = currentCustomer.serviceTime + simulator.currentTime();
			currentCustomer.deQueueTime = time;
			currentCustomer.queuingTime = currentCustomer.deQueueTime - currentCustomer.enQueueTime - currentCustomer.serviceTime;
			System.out.println(currentCustomer);
			runtimeQueue.add(this);
		}
	}
}