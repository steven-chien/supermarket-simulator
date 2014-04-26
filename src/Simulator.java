import java.util.*;
import java.io.*;

class Simulator
{
	private Queue<Event> eventQueue = new PriorityQueue<Event>(10, new Comparator<Event>() {
		@Override public int compare(Event e1, Event e2) {
			return (int)(e2.timestamp - e1.timestamp);
		}
	});
	private ArrayList<Listener> listeners = new ArrayList<Listener>();
	private double time = 0.0;

	public Simulator(Event event) {
		//insert first event
		this.addEvent(event);
	}

	public void addEvent(Event event) {
		this.time = event.timestamp;
		eventQueue.add(event);
	}

	public void addListener(Listener listener) {
		listeners.add(listener);
	}

	public double getTime() {
		return this.time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public void start() {
		Event currentEvent;
		while(eventQueue.size()>0) {
			currentEvent = eventQueue.poll();
			for(int i=0; i<listeners.size(); i++) {
				listeners.get(i).execute(this, currentEvent);
			}
			System.out.println("Simulator: Current Time: "+time);
		}
	}

}