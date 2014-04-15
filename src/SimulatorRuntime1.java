import java.util.*;
import java.math.*;

class SimulatorRuntime
{
	public static void main(String[] args) {

        Simulator simulator = new Simulator();
        Cashier cashier = new Cashier();
        Generator generator = new Generator();
        Customer customer = new Customer(0.0);

        simulator.addCustomer(customer);
        simulator.addListener(generator);
        simulator.addListener(cashier);

		simulator.start();
	}
}

class Customer
{
	double enQueueTime = 0.0;
    double queuingTime = 0.0;
    double serviceTime = 0.0;
	double deQueueTime = 0.0;

    String eventName;
	
	public Customer(double enQueueTime) {
	    this.enQueueTime = enQueueTime;
	}

    @Override public String toString() {
        return "enQueueTime = "+this.enQueueTime+"; queuingTime = "+this.queuingTime+"; serviceTime = "+this.serviceTime+"; deQueueTime = "+this.deQueueTime;
    }

    public void execute() {}
}

class PointOfSales
{
    private int transactionCount = 0;
    private ArrayList<Double> transactionTime = new ArrayList<Double>();

    public PointOfSales() {

    }

    public double newTransaction(double time) {
        this.transactionTime.add(time);
        transactionCount++;

        return Math.random();
    }
}

abstract class Listener
{
    abstract void execute(Simulator simulator, Customer customer);
}

class Cashier extends Listener
{
    PointOfSales pos = new PointOfSales();

    public void execute(Simulator simulator, Customer customer) {
        double currentTime = simulator.getCurrentTime();
        customer.serviceTime = pos.newTransaction(currentTime);
        customer.queuingTime = currentTime - customer.enQueueTime;
        customer.deQueueTime = currentTime + customer.serviceTime;
        simulator.updateCurrentTime(customer.deQueueTime);
        System.out.println(customer);
    }
}

class Generator extends Listener
{
    public void execute(Simulator simulator, Customer customer) {
        if(simulator.getCurrentTime()<10.0) {
            double currentTime = simulator.getCurrentTime() + Math.random();
            System.out.println("new customer time "+currentTime);
            Customer newCustomer = new Customer(currentTime);
            simulator.addCustomer(newCustomer);
        }
    }
}

class Simulator
{
    private ArrayList<Customer> customerQueue = new ArrayList<Customer>();
    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    /* declare servers */
    private double time = 0.0;
    private Customer currentCustomer;
    
    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        /* sort the queue here */
        Collections.sort(customerQueue, new Comparator<Customer>() {
            public int compare(Customer x, Customer y) {
                return (int)(x.enQueueTime - y.enQueueTime);
            }
        });
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public double getCurrentTime() {
        return this.time;
    }

    public void updateCurrentTime(double time) {
        this.time = time;
    }
    
    public void start() {
        while(customerQueue.size()>0) {
            currentCustomer = customerQueue.remove(0);
            for(int i=0; i<listeners.size(); i++) {
                listeners.get(i).execute(this, currentCustomer);
            }
            System.out.println("Current time = "+this.time);
        }
    }
}