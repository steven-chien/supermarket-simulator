import java.util.*;
import java.math.*;

class Cashier extends Counter
{
	Counter counterA, counterB, counterC, counterD;

	public Cashier() {
		this.time = 0.0;
		this.name = "Cashier";
		this.queue = new ArrayList<Customer>();
	}

	@Override public void run() {
		while(counterA.isAlive() || counterB.isAlive() || counterC.isAlive() || counterD.isAlive() || queue.size()>0) {
			if(queue.size()>0) {
				servingCustomer = queue.remove(0);
				serviceTime = Math.random();
				this.time = this.time + serviceTime;
				servingCustomer.setTime(servingCustomer.getTime()+(this.time-servingCustomer.getArrivalTime()));
				//System.out.println("Customer "+servingCustomer.getID()+" left cashier at "+servingCustomer.getTime()+", total spent time is "+(servingCustomer.getTime()-servingCustomer.getCreatedTime()));
				System.out.println("Cashier,"+servingCustomer.getID()+","+servingCustomer.getArrivalTime()+","+servingCustomer.getTime());
			}
		}
	}
}