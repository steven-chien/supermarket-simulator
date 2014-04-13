import java.util.*;
import java.math.*;

class CanteenSimulator
{
	public static void main(String[] args) {
		Cashier cashier = new Cashier();
		FoodCounter twoDish = new FoodCounter(cashier);
		FoodCounter hamburger = new FoodCounter(cashier);
		FoodCounter noodles = new FoodCounter(cashier);
		Beverages beverages = new Beverages(cashier);
		int customerCount = 0;
		
		cashier.counterA = twoDish;
		cashier.counterB = hamburger;
		cashier.counterC = noodles;
		cashier.counterD = beverages;

		beverages.counterA = twoDish;
		beverages.counterB = hamburger;
		beverages.counterC = noodles;

		twoDish.beverages = beverages;
		hamburger.beverages = beverages;
		noodles.beverages = beverages;


		for(int i=0; i<10; i++) {
			twoDish.add(new Customer(i, i+Math.random()));
		}
		Collections.sort(twoDish.queue, new Comparator<Customer>() {
			@Override public int compare(Customer x, Customer y) {
				return (int)(y.getArrivalTime() - x.getArrivalTime());
			}
		});

		for(int i=10; i<20; i++) {
			hamburger.add(new Customer(i, i-10+Math.random()));
		}
		Collections.sort(hamburger.queue, new Comparator<Customer>() {
			@Override public int compare(Customer x, Customer y) {
				return (int)(y.getArrivalTime() - x.getArrivalTime());
			}
		});

		for(int i=30; i<30; i++) {
			noodles.add(new Customer(i, i-20+Math.random()));
		}
		Collections.sort(noodles.queue, new Comparator<Customer>() {
			@Override public int compare(Customer x, Customer y) {
				return (int)(y.getArrivalTime() - x.getArrivalTime());
			}
		});

		noodles.start();
		hamburger.start();
		twoDish.start();
		beverages.start();

		cashier.start();
		
	}
}