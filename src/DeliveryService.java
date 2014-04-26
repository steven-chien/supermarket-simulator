import java.util.*;

class DeliveryService implements Listener
{
	public void execute(Simulator simulator, Event event) {
		Customer customer;
		if(event instanceof DeQueueEvent) {
			if(Math.random()>0.5) {
				System.out.println(event+": requires delivery service");
			}
		}
	}
}