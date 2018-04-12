
/**
 * Car class that implements the abstract methods of the super class. It provides the functionality to create a car and calculate the efficiency 
 * of the car.
 *
 * @author Group-19
 * @version 11/04/18
 */

//Importing JOptionPane class to use the GUI components for I/O
import javax.swing.*;

public class Car extends Vehicle
{
    // Declaring field variables and constants of int and double dataypes.
    
    private int taxBand;
    private final int avgCostOfRunning = 2250;
    private final double[] fuelCost = {0.10,0.115,0.135,0.14,0.16};
    private final int[] roadTax = {150,250,350,450,500};
    private double efficiency;
    
    /**
     * Constructor for objects of class Car
     * @param This method does not take any formal parameter.
     * @return This method does not return any value
     */
    public Car()
    {
        super("na",0,0,0,0);
    }
    
    /**
     * Constructor for objects of class Car
     * @param This method takes a formal parameter Id of string datatype
     * @param This method takes a formal parameter noOfDays of int datatype
     * @param This method takes a formal parameter noOfDeliveries of int datatype
     * @param This method takes a formal parameter valOfDeliveries of double datatype
     * @param This method takes a formal parameter milesCovered of double datatype
     * @param This method takes a formal parameter band of int datatype
     * @return This method does not return any value
     */
    public Car(String id,int noOfDays, int noOfDeliveries, double valOfDeliveries, double milesCovered, int band)
    {
        super(id,noOfDays, noOfDeliveries, valOfDeliveries, milesCovered);
        taxBand = band;
            
    }
    
    public int getTaxBand()
    {
        return taxBand;
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
     * Mutator Method to calculate efficiency of a car 
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    @Override
    public void calculateEfficiency()
    {   
        int band = getTaxBand();
        
        //Exception Handler
        try
        {
                        
            efficiency = (getValueOfDeliveries()) / ((fuelCost[band]*getNumberOfMilesCovered()) + 
            (roadTax[band] + getAvgCostOfRunning()));
            
        }
        catch(Exception e)
        {
            //Message displayed in a dialogue box if error occurs in the calculation
            JOptionPane.showMessageDialog(null,"Error occured while calculating Efficiency !","Error Message",JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    /**
     * Method to display all the details of a Moped 
     * @param This method does not take any parameter
     * @return This method does return a value of string datatype
     */
    
    public String toString()
    {
        return ("Vehicle Id : " + getVehicleId() +", Days in Service : " + getNumberOfDays() + ", Value Of Deliveries: " 
        + getValueOfDeliveries()+ ", Miles Covered : " + getNumberOfMilesCovered()+ ", Efficiency : " + String.format("%.2f",getEfficiency()) );
    }
}
