
/**
 * Abstract class Vehicle is a super class and consists of methods and field variables which can be used by the base classes car, moped
 * and bicycle. It consists of abstract method definitions. 
 *
 * @author Group-19
 * @version 11/04/18
 */

public abstract class Vehicle
{
    // Declaring field variables of String, int, and double datatypes
    private String vehicleId;
    private int numberOfDays;
    private int numberOfDeliveries;
    private double valueOfDeliveries;
    private double numberOfMilesCovered;
    
    /**
     * Constructor for class Vehicle
     * @param This method takes a formal parameter Id of string datatype
     * @param This method takes a formal parameter noOfDays of int datatype
     * @param This method takes a formal parameter noOfDeliveries of int datatype
     * @param This method takes a formal parameter valOfDeliveries of double datatype
     * @param This method takes a formal parameter milesCovered of double datatype
     * @return This method does not return any value
     */

    public Vehicle(String Id,int noOfDays, int noOfDeliveries, double valOfDeliveries, double milesCovered)
    {
        vehicleId = Id;
        numberOfDays = noOfDays;
        numberOfDeliveries = noOfDeliveries;
        valueOfDeliveries = valOfDeliveries;
        numberOfMilesCovered = milesCovered;
        
    }
    
    /**
     * Abstract method definition
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    public abstract void calculateEfficiency();
    
    /**
     * Abstract method definition
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    public abstract double getEfficiency();
        
    /**
     * Set Mutator method to set the vehicle Id.
     * @param This method takes formal parameter id of string datatype.
     * @return This method does not return any value.
     */
    public void setVehicleId(String id)
    {
        this.vehicleId = id;
    }
    
    /**
     * Set Mutator method to set the number of days.
     * @param This method takes formal parameter days of int datatype.
     * @return This method does not return any value.
     */
    public void setNumberOfDays(int days)
    {
        this.numberOfDays = days;
    }
    
    /**
     * Set Mutator method to set the number of deliveries.
     * @param This method takes formal parameter deliveries of int datatype.
     * @return This method does not return any value.
     */
    public void setNumberOfDeliveries(int deliveries)
    {
        this.numberOfDeliveries = deliveries;
    }
    
    /**
     * Set Mutator method to set the value of deliveries.
     * @param This method takes formal parameter amount of double datatype.
     * @return This method does not return any value.
     */
    public void setValueOfDeliveries(double amount)
    {
        this.valueOfDeliveries = amount;
    }
    
    /**
     * Set Mutator method to set the number of miles covered.
     * @param This method takes formal parameter miles of double datatype.
     * @return This method does not return any value.
     */
    public void setNumberOfMilesCovered(double miles)
    {
        this.numberOfMilesCovered = miles;
    }
    
    /**
     * Get Accessor method to get the vehicle Id
     * @param This method does not take any formal parameter.
     * @return This method returns vehicle Id of String datatype.
     */
    public String getVehicleId()
    {
        return vehicleId;
    }
    
    /**
     * Get Accessor method to get the number of days.
     * @param This method does not take any formal parameter. 
     * @return This method returns days of int datatype.
     */
    public int getNumberOfDays()
    {
        return numberOfDays;
    }
    
    /**
     * Get Accessor method to get the number of deliveries.
     * @param This method does not take any formal parameter. 
     * @return This method returns number of deliveries of int datatype.
     */
    public int getNumberOfDeliveries()
    {
        return numberOfDeliveries;
    }
    
    /**
     * Get Accessor method to get the value of deliveries.
     * @param This method does not take any formal parameter. 
     * @return This method returns value of deliveries of double datatype.
     */
    public double getValueOfDeliveries()
    {
        return valueOfDeliveries;
    }
    
    /**
     * Get Accessor method to get the number of miles covered.
     * @param This method does not take any formal parameter. 
     * @return This method returns number of miles covered of double datatype.
     */
    public double getNumberOfMilesCovered()
    {
        return numberOfMilesCovered;
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
        + getValueOfDeliveries()+ ", Miles Covered : " + getNumberOfMilesCovered() );
    }
}
