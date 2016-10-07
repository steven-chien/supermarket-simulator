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
		return "A customer started queuing at "+this.enQueueTime+" for "+this.queuingTime+" and was served for "+this.serviceTime+" and left at "+this.deQueueTime;
	}
}
