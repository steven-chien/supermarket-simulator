import java.util.*;
import java.io.*;

class PointOfSales
{
    private int transactionCount = 0;
    private ArrayList<Double> transactionTime = new ArrayList<Double>();

    public double newTransaction(double time) {
        System.out.println("PointOfSales System: processed transaction at: "+time);
        this.transactionTime.add(time);
        transactionCount++;

        return Math.random();
    }
}