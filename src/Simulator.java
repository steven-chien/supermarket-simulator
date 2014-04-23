import java.util.*;
import java.io.*;

class Simulator
{
    private Queue<Customer> customerQueue;
    private ArrayList<Listener> listeners = new ArrayList<Listener>();
    /* declare servers */
    private double time = 0.0;
    private Customer currentCustomer;
    private String fileName;
    private int customerSize;
    
    public Simulator(String fileName, int size) {
        customerQueue = new PriorityQueue<Customer>(size, new Comparator<Customer>() {
            @Override public int compare(Customer c1, Customer c2) {
                return (int)(c1.enQueueTime - c2.enQueueTime);
            }
        });
        this.customerSize = size;
        this.fileName = fileName;

        try {
            FileWriter fw = new FileWriter(fileName,false);
            fw.write("Enqueue Time,Queuing Time,Service Time,Dequeue Time\n");
            fw.close();            
        }
        catch(IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

    }

    public int getCustomerSize() {
        return customerSize;
    }

    public String getFfileName() {
        return this.fileName;
    }

    public void addCustomer(Customer customer) {
        customerQueue.add(customer);
        /* sort the queue here */
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public double getCurrentTime() {
        return this.time;
    }

    public void updateCurrentTime(double time) {
        this.time = time;
    }
    
    public void start() {
        while(customerQueue.size()>0) {
            currentCustomer = customerQueue.poll();
            for(int i=0; i<listeners.size(); i++) {
                listeners.get(i).execute(this, currentCustomer);
            }
            System.out.println("Current time = "+this.time);
        }
    }
}