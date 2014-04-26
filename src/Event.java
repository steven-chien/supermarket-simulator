import java.io.*;
import java.util.*;

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

class EnQueueEvent extends Event
{
	public EnQueueEvent(double timestamp, Customer customer) {
		super(timestamp, customer);
		System.out.println(this);
	}

	@Override public String toString() {
		return super.toString()+"; customer enqueue";
	}
}

class DeQueueEvent extends Event
{
	public DeQueueEvent(double timestamp, Customer customer) {
		super(timestamp, customer);
		this.customer.deQueueTime = this.timestamp;
		System.out.println(this);
	}

	@Override public String toString() {
		return super.toString()+" customer dequeue; service = "+customer.serviceTime+"; enQueue = "+customer.enQueueTime;
	}
}

class SimulatorStartEvent extends GenerateCustomerEvent
{
	public SimulatorStartEvent(double timestamp) {
		super(timestamp);
		System.out.println(this);
	}

	@Override public String toString() {
		return super.toString()+" Simulator starts!";
	}
}

class GenerateCustomerEvent extends Event
{
	public GenerateCustomerEvent(double timestamp) {
		super(timestamp);
		System.out.println(this);
		//this.customer = new Customer(timestamp);
	}

	@Override public String toString() {
		return super.toString()+" insert generator";
	}
}