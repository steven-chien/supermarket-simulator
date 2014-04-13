import java.util.*;
import java.math.*;

class Counter extends Thread
{
	protected String name;
	protected double time;
	protected double serviceTime;
	protected Customer servingCustomer;

	ArrayList<Customer> queue;

	public void add(Customer customer) {
		customer.setArrivalTime(this.time);
		queue.add(customer);
		Collections.sort(queue, new Comparator<Customer>() {
			@Override public int compare(Customer x, Customer y) {
				return (int)(y.getArrivalTime() - x.getArrivalTime());
			}
		});
	}

}

class FoodCounter extends Counter
{
	Cashier cashier;
	Beverages beverages;

	public FoodCounter(Cashier cashier) {

		this.cashier = cashier;
		super.time = 0.0;
		super.name = "FoodCounter";
		super.queue = new ArrayList<Customer>();
	}

	public void run() {
		while(queue.size()>0) {
			servingCustomer = queue.remove(0);
			serviceTime = Math.random();
			this.time = this.time + serviceTime;
			servingCustomer.setTime(servingCustomer.getTime()+(this.time-servingCustomer.getArrivalTime()));
			//System.out.println("Customer "+servingCustomer.getID()+" getting food at "+servingCustomer.getTime());
			System.out.println("FoodCounter,"+servingCustomer.getID()+","+servingCustomer.getArrivalTime()+","+servingCustomer.getTime());
			if(Math.random()>0.5) {
				beverages.add(servingCustomer);
			}
			else {
				cashier.add(servingCustomer);
			}
		}
	}
}

class Beverages extends Counter
{
	Cashier cashier;
	FoodCounter counterA, counterB, counterC;

	public Beverages(Cashier cashier) {
		this.cashier = cashier;
		super.time = 0.0;
		super.name = "Beverages counter";
		super.queue = new ArrayList<Customer>();
	}

	public void run() {
		while(counterA.isAlive() || counterB.isAlive() || counterC.isAlive() || queue.size()>0) {
			if(queue.size()>0) {
				servingCustomer = queue.remove(0);
				serviceTime = Math.random();
				this.time = this.time + serviceTime;
				servingCustomer.setTime(servingCustomer.getTime()+this.time);
				//System.out.println("Customer "+servingCustomer.getID()+" getting beverage at "+servingCustomer.getTime());
				System.out.println("Beverages,"+servingCustomer.getID()+","+servingCustomer.getArrivalTime()+","+servingCustomer.getTime());
				cashier.add(servingCustomer);
			}
		}
	}
}