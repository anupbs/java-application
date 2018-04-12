
/**
 * Moped class that implements the abstract methods of the super class. It provides the functionality to create a moped and calculate the efficiency 
 * of the moped.
 *
 * @author Group-19
 * @version 11/04/18
 */

//Importing JOptionPane class to use the GUI components for I/O
import javax.swing.*;

public class Moped extends Vehicle
{
    // Declaring field variables and constants of int and double datatype
    private final double fuelCost = 0.065;
    private final int avgCostOfRunning = 1000;
    private double efficiency;

    /**
     * Constructor for class Moped
     * @param This method does not take any formal parameter 
     * @return This method does not return any value
     */
    public Moped()
    {
        super("na",0,0,0,0);
    }
    
    /**
     * Constructor for objects of class Moped
     * @param This method takes a formal parameter Id of string datatype
     * @param This method takes a formal parameter noOfDays of int datatype
     * @param This method takes a formal parameter noOfDeliveries of int datatype
     * @param This method takes a formal parameter valOfDeliveries of double datatype
     * @param This method takes a formal parameter milesCovered of double datatype
     * @return This method does not return any value
     */
    public Moped(String id,int noOfDays, int noOfDeliveries, double valOfDeliveries, double milesCovered)
    {
        
        super( id, noOfDays, noOfDeliveries, valOfDeliveries, milesCovered);
        
    }
    
    public double getFuelCost()
    {
        return fuelCost;
    }
    
    public int getAvgCostOfRunning()
    {
        return avgCostOfRunning;
    }
    
    @Override
    public double getEfficiency()
    {
        return efficiency;
    }
    
    /**
     * Mutator Method to calculate efficiency of a Moped 
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    @Override
    public void calculateEfficiency()
    {   
        //Exception Handler
        try
        {
            efficiency = getValueOfDeliveries() / ((getFuelCost()*getNumberOfMilesCovered()) + getAvgCostOfRunning());
        }
        catch(Exception e)
        {
            //Message displayed in a dialogue box if error occurs in the calculation
            JOptionPane.showMessageDialog(null,"Error occured while calculating Efficiency of Moped !","Error Message",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Method to display all the details of a Moped 
     * @param This method does not take any parameter
     * @return This method does return a value of string datatype
     */
    @Override
    public String toString()
    {
        return ("Vehicle Id : " + getVehicleId() +", Days in Service : " + getNumberOfDays() + ", Value Of Deliveries: " 
        + getValueOfDeliveries()+ ", Miles Covered : " + getNumberOfMilesCovered()+ ", Efficiency : " + String.format("%.2f",getEfficiency()) );
    }
}
