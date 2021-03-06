import java.io.*;
import java.util.*;

//general event
class Event
{
	double timestamp;
	Customer customer;

	public Event(double timestamp, Customer customer) {
		this.timestamp = timestamp;
		this.customer = customer;
	}

	public Event(double timestamp) {
		this.timestamp = timestamp;
	}

	@Override public String toString() {
		return "Time = "+timestamp;
	}
}

//enqueue event
class EnQueueEvent extends Event
{
	public EnQueueEvent(double timestamp, Customer customer) {
		super(timestamp, customer);
		System.out.println(this);
	}

	//decorate
	@Override public String toString() {
		return super.toString() + " ready to serve a new customer";
	}
}

//dequeue
class DeQueueEvent extends Event
{
	public DeQueueEvent(double timestamp, Customer customer) {
		super(timestamp, customer);
		this.customer.deQueueTime = this.timestamp;
		System.out.println(this);
	}

	//decorate
	@Override public String toString() {
		return super.toString()+" a customer was served at "+customer.enQueueTime+" for "+customer.serviceTime+" and left at "+customer.deQueueTime;
	}
}

//first event
class SimulatorStartEvent extends GenerateCustomerEvent
{
	public SimulatorStartEvent(double timestamp) {
		super(timestamp);
		System.out.println(this);
	}

	//decorate
	@Override public String toString() {
		return super.toString()+" Simulator starts!";
	}
}

//generate new customer
class GenerateCustomerEvent extends Event
{
	public GenerateCustomerEvent(double timestamp) {
		super(timestamp);
		System.out.println(this);
		//this.customer = new Customer(timestamp);
	}

	//decorate
	@Override public String toString() {
		return super.toString()+" insert customer generator";
	}
}
