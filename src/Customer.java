import java.util.*;
import java.math.*;

class Customer {
	private double createdTime;
	private double arrivalTime;
	private double time;
	private int customerID;

	public Customer(int customerID, double currentTime) {
		this.customerID = customerID;
		this.createdTime = currentTime;
		this.time = this.createdTime;
		//System.out.println("Customer "+getID()+" entered queue at "+this.createdTime);
		System.out.println(this.getID()+",Enqueue,"+this.getCreatedTime()+",,");
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getID() {
		return customerID;
	}

	public double getCreatedTime() {
		return createdTime;
	}

	public double getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(double arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}