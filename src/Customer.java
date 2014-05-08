import java.util.*;
import java.io.*;

class Customer
{
	//data
	double enQueueTime = 0.0;
	double queuingTime = 0.0;
	double serviceTime = 0.0;
	double deQueueTime = 0.0;
	
	public Customer(double enQueueTime) {  
		this.enQueueTime = enQueueTime;
	}

	@Override public String toString() {
		return this.enQueueTime+","+this.queuingTime+","+this.serviceTime+","+this.deQueueTime;
	}
}