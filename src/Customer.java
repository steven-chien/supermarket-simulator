import java.util.*;
import java.io.*;

class Customer
{
	double enQueueTime = 0.0;
	double queuingTime = 0.0;
	double serviceTime = 0.0;
	double deQueueTime = 0.0;

	int customerID;
	String eventName;
	
	public Customer(double enQueueTime) {  
		this.enQueueTime = enQueueTime;
		//System.out.println("enQueue = "+this.enQueueTime);
	}

	@Override public String toString() {
		return this.enQueueTime+","+this.queuingTime+","+this.serviceTime+","+this.deQueueTime;
	}

	//public void execute() {}
}