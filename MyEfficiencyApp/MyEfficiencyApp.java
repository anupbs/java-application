/**
 * MyEfficiencyApp is a controller class for the application. It provides the functionality to Add daily data about the vehicle,
 * Run efficiency report to display the efficiency of a vehicle, add a vehicle, display fleet report, add vehicle data to file and 
 * fetch the data fromthe file.
 * 
 * @author Group-19
 * @version 11/04/2018
 * 
 */

//Importing JOptionPane class to use the GUI components for I/O and Java packages for IO and using collections
import java.io.*;
import javax.swing.*; 
import java.util.*;

public class MyEfficiencyApp
{
    // Declaring field variables and array list of double, string and object datatype
    private double milesCovred, totalNoDeliveries, valueOfDelivery, vehicleIdentifier;
    String fileLocation ="vechicleDetails.txt";
    ArrayList<Vehicle> vehicleList = new ArrayList<Vehicle>();
    ArrayList<Vehicle> vehEfficiencyList = new ArrayList<Vehicle>();

    /**
     * Constructor for objects of class userInterface
     */
    public MyEfficiencyApp()
    {
        // initialise instance variables
    }
    
    /**
     * Main method called starting of the application
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    public void userInterface()
    {
        //while used to give user back control once he complete each operations
        try{
            boolean status = false; // boolean Status is used to
            vehicleList.clear();
            updateArrayList();

            while(!status)
            {
                String selectOptions= JOptionPane.showInputDialog(null,"Please select an menu option below:\n\n[1] Add Daily Data:\n\n[2] Run Efficiency Report\n\n[3] Uility Menu\n\n[4] Delete Vehicle\n\n[5] Exit Application\n","WELCOME TO MyPIZZA",JOptionPane.PLAIN_MESSAGE);
                //if statement is used to handle cancle button in user input interface
                if(selectOptions != null){
                    switch(selectOptions) // 
                    {
                        case "1": addDailyData();
                        break;

                        case "2": runEfficiencyReport();
                        break;

                        case "3":  String utilityOptions= JOptionPane.showInputDialog(null,"Please select an utility option below:\n\n[1] Add Delivery Vehicle:\n\n[2] Display Fleet Report\n\n","Utility Menu",JOptionPane.PLAIN_MESSAGE);
                        if(utilityOptions.equals("1"))
                        {
                            addDeliveryVehicle();
                            break;
                        }
                        else{
                            displayFleetReport();
                            break;
                        }
                        
                        //Method called to delete vehicle from the arraylist
                        case "4": deleteVehicle();
                        break;
                        
                        //Method called to write things to arraylist and close application.
                        case "5": writeToFile();
                        System.exit(0);

                        default: JOptionPane.showMessageDialog(null,"Invalid input, please try again !!","Warning",JOptionPane.WARNING_MESSAGE);
                        break;
                    }
                }
                else
                { 
                    return;
                }
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error Found\n"+e+"","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Method used to delete specific Vehicle details through Vehicle ID.
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    private void deleteVehicle()
    {
        //String Arraylist to add all the Vehicle ID's.
        ArrayList<String> tempVehicleIDList = new ArrayList<String>(vehicleList.size());
        for(Vehicle v: vehicleList) {
            tempVehicleIDList.add(v.getVehicleId());
        }
        //Users input vehicle ID that needs to be deleted.    
        String inputValue = JOptionPane.showInputDialog("Enter unique identifier of Vehicle");

        if (tempVehicleIDList.contains(inputValue) && inputValue != null)
        {

            //Foreach loop to check vehicle present in the arrayList
            for(Vehicle v: vehicleList)
            {
                if(v.getVehicleId().equals(inputValue))
                {
                    //Delete Vehicle object from the ArrayList.
                    vehicleList.remove(v);
                    JOptionPane.showMessageDialog(null,inputValue+"Deleted successfully","Warning",JOptionPane.WARNING_MESSAGE);

                    break;
                }
            }
        }
        else
        {
            //Throw an error id if the application couldn't find the Vehicle Identifer
            JOptionPane.showMessageDialog(null,"Vehicle cannot be found, please try again !!","Warning",JOptionPane.WARNING_MESSAGE);

        }

    }

    /**
     * Method to add Daily Data to the File by updating vehicle details
     * @param This method does not take any parameter
     * @return This method does not return any value
     */    
    private void addDailyData()
    {

        try
        {
            // Declaring variables which is used for getters and setters 
            String inputValue,VehicleID;
            int totalDelivery,noOfServiceDays;
            double totalMiles,totalValue;
            ArrayList<String> tempVehicleIDList = new ArrayList<String>(vehicleList.size()); // used to get the Vehicle ID list

            //Add vehicle Id's to the tempVehicleIDList Arraylist
            for(Vehicle v: vehicleList) {
                tempVehicleIDList.add(v.getVehicleId());
            }

            //inputValue will take Vehicle ID from the user which data need to be added.
            inputValue = JOptionPane.showInputDialog(null,"Enter unique identifier of Vehicle to update ","Enter Identifer",JOptionPane.PLAIN_MESSAGE);

            //Condition to check weather tempVehicleList contains the Vehicle ID or not.
            if(tempVehicleIDList.contains(inputValue))
            {
                //Values given by user to update on vehicle object.
                totalDelivery=Integer.parseInt(JOptionPane.showInputDialog(null,"Enter total number of deliveries"," Input Details",JOptionPane.PLAIN_MESSAGE));
                totalValue=Double.parseDouble(JOptionPane.showInputDialog(null,"Enter total value of deliveries","Input Detsils",JOptionPane.PLAIN_MESSAGE));
                totalMiles=Double.parseDouble(JOptionPane.showInputDialog(null,"Enter total number of miles","Input details",JOptionPane.PLAIN_MESSAGE));  
                for(Vehicle v: vehicleList)
                {
                    //Respective Vehicle's data will be updated using getters and setters
                    if(v.getVehicleId().equals(inputValue))
                    {
                        VehicleID = v.getVehicleId();
                        noOfServiceDays = v.getNumberOfDays();
                        totalDelivery += v.getNumberOfDeliveries();
                        totalValue += v.getValueOfDeliveries();
                        totalMiles += v.getNumberOfMilesCovered();                  
                        v.setVehicleId(VehicleID);
                        v.setNumberOfDays(noOfServiceDays+1);
                        v.setNumberOfDeliveries(totalDelivery);            
                        v.setValueOfDeliveries(totalValue);
                        v.setNumberOfMilesCovered(totalMiles);
                        break; // come out of the loop once the 
                    }
                }
                // Success message will be display to user
                JOptionPane.showMessageDialog(null,"Vehicle details has been updated successfully","Vehicle updated",JOptionPane.PLAIN_MESSAGE);
            }

            //If the value is null or if user press cancel, control will be passed to Home page
            else if(inputValue == null ){
                userInterface(); 
            }
            else
            {
                //Message to show wrong ID and will be directed to the addDailyData function to retry Vehicle ID.
                JOptionPane.showMessageDialog(null,"Unique Identifer cannot be found, please try again !!","Warning",JOptionPane.WARNING_MESSAGE);
                addDailyData();
            }

            //Method called to write ArrayList to the File.
            writeToFile();
            //To avoid duplication we are updating the Arraylist by calling this method.
            updateArrayList();
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"Error Found\n"+ex+"","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Mutator Method to display the efficieny report  
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    private void runEfficiencyReport()
    {
        //Declaring and initializing local variables, arrays and arraylist of int, double and object datatype
        String efficiencyReport = ""; 
        String vehicleType = "";

        //Loop to traverse through the vehicle objects in vehicle array list
        for(Vehicle v : vehicleList)
        {
            //Method call to objects own calculate efficiency method
            v.calculateEfficiency();

            //Concating details of each vehicle to a string to display 
            //efficiencyReport += "Vehicle Id: "+ v.getVehicleId()+" Efficiency: "+ String.format("%.2f",v.getEfficiency()) + "\n";
            efficiencyReport += v + "\n";
            //Adding the vehicle object with calculated efficiency into an array list
            vehEfficiencyList.add(v);
        }

        vehicleType = efficientVehicleType();

        //Efficiency report displayed with car details, efficiency and most efficient vehicle type
        JOptionPane.showMessageDialog(null,efficiencyReport + "\n\n Most Efficient Vehicle Type : "+vehicleType,
            "Efficiency Report",JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Method that calculates the most efficient vehicle type  
     * @param This method does not take any parameter
     * @return This method does return vehicleType of String dataype
     */
    private String efficientVehicleType()
    {
        //Declaring local variables and array of string, int and double datatype
        String vehicleType = "";
        double numeratorCar = 0.0;
        int denominatorCar = 0;
        double numeratorMoped = 0.0;
        int denominatorMoped = 0;
        double numeratorBicycle = 0.0;
        int denominatorBicycle = 0;
        double efficiencyCar = 0.0;
        double efficiencyMoped = 0.0;
        double efficiencyBicycle = 0.0;
        int index = 0;
        double max = 0.0;
        double[] efficiency = new double[3];

        //Loop to traverse through the vehicle objects in vehicle efficiency array list
        for(Vehicle v : vehEfficiencyList)
        {
            //Condition to check if the vehicle contains car
            if(v.getVehicleId().contains("Car"))
            {
                numeratorCar += (v.getNumberOfDays()*v.getEfficiency());
                denominatorCar += (v.getNumberOfDays());
            }
            //Condition to check if the vehicle contains moped
            else if(v.getVehicleId().contains("Moped"))
            {
                numeratorMoped += (v.getNumberOfDays()*v.getEfficiency());
                denominatorMoped += (v.getNumberOfDays());
            }
            //Condition to check if the vehicle contains bicycle
            else if(v.getVehicleId().contains("Bicycle"))
            {
                numeratorBicycle += (v.getNumberOfDays()*v.getEfficiency());
                denominatorBicycle += (v.getNumberOfDays());
            }
        }
        //Clearing the vehicle efficiency array list
        vehEfficiencyList.clear();

        //Exception Handler
        try
        {
            //Calculating weighted average of car, moped and bicycle efficiency
            efficiencyCar = numeratorCar/denominatorCar;
            efficiencyMoped = numeratorMoped/denominatorMoped;
            efficiencyBicycle = numeratorBicycle/denominatorBicycle;

            //Adding average efficiency of car, moped and bicycle into efficiency array
            efficiency[0] = efficiencyCar;
            efficiency[1] = efficiencyMoped;
            efficiency[2] = efficiencyBicycle;
        }
        catch(Exception e)
        {
            //Message displayed if error occurs while calculating average efficiency
            JOptionPane.showMessageDialog(null,"Error occured while calculating Efficiency !","Error Message",JOptionPane.ERROR_MESSAGE);
        }

        max = efficiency[0];
        //Traversing through the loop to get highest average efficiency vehicle type
        for(int i = 0; i<efficiency.length;i++)
        {
            if(efficiency[i] > max)
            {
                max = efficiency[i];
                index = i;
            }

        }

        //To assign the value to a local variable to be displayed 
        switch(index)
        {
            case 0:
            vehicleType = "Car";
            break;

            case 1:
            vehicleType = "Moped";
            break;

            case 2:
            vehicleType = "Bicycle";
            break;
        }
        return vehicleType;
    }

    /**
     * Method to add New DeliveryVehicle
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    private void addDeliveryVehicle()
    {
        try{
            String regID="";
            //Options to select CAR, MOPED, BICYCLE
            String selectOptions= JOptionPane.showInputDialog(null,"Please select an menu option below:\n\n[1] Car:\n\n[2] Mopeds:\n\n[3] Bicycles\n\n[4] Go Back\n","MyEfficiencyApp",JOptionPane.PLAIN_MESSAGE);

            if(selectOptions != null){
                switch(selectOptions) 
                {
                    // Select the Car engine type. (Band1, Band2, Band3, Band4 and Band5)
                    case "1": String selectBand= JOptionPane.showInputDialog(null,"Please select Band of the vehicle:\n\n[1] Band1:\n\n[2] Band2:\n\n[3] Band3:\n\n[4] Band4:\n\n[5] Band5:\n","MyEfficiencyApp",JOptionPane.PLAIN_MESSAGE);
                    String temp ="";
                    int bandIndex = 0;

                    //Option for Users to input Registration Number
                    regID =  JOptionPane.showInputDialog(null,"Enter a Registration Number","Input Dialog",JOptionPane.PLAIN_MESSAGE);
                    if(selectBand != null){
                        /*Switch case used to concatenate the Car with respective Band, which is essential
                         * beacuse it can differentialte when we are calculating efficiency 
                         */
                        switch(selectBand)
                        {
                            //If user inpits Registration ID: "22": It is stored in  this format "CarBand22"
                            //similarly for all other vehicle which is defined for different casses
                            case "1": temp ="Car"+"Band1"+regID;
                            bandIndex = 0;
                            break;

                            case "2": temp = "Car"+"Band2"+regID;
                            bandIndex = 1;
                            break;

                            case "3":  temp = "Car"+"Band3"+regID;
                            bandIndex = 2;
                            break;

                            case "4": temp = "Car"+"Band4"+regID;
                            bandIndex = 3;
                            break;

                            case "5": temp = "Car"+"Band5"+regID;
                            bandIndex = 4;
                            break;

                        }
                        regID = temp;
                        // Creating a Car Object and assigning registration ID with default ID
                        Car car = new Car(regID,0,0,0,0,bandIndex);
                        //if the registration ID is unique, add the data to Main vehicle array list
                        if(!uniqueTest(regID))
                        {
                            vehicleList.add(car); //add the car vehicle details to Vehicle array list
                            JOptionPane.showMessageDialog(null,"Vehicle Added Successfully : "+regID,"Success",JOptionPane.PLAIN_MESSAGE);
                        }
                        else
                        {
                            //Display error message if the vehicle is vehicle registration ID not Unique
                            JOptionPane.showMessageDialog(null,regID+" : Regestration ID Exist !!","Warning",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                    else
                    { 
                        return;
                    }
                    break;

                    // Case 2 used to get the Registration ID for Moped Vehicle
                    case "2": regID =  JOptionPane.showInputDialog(null,"Enter a Registration Number","Input Dialog",JOptionPane.PLAIN_MESSAGE);
                    regID = "Moped"+regID;
                    /* Creating a Car Object and assigning registration ID with default ID */
                    Moped moped = new Moped(regID,0,0,0,0);
                    if(!uniqueTest(regID))
                    {
                        vehicleList.add(moped); //add the Moped vehicle details to Vehicle array list
                        JOptionPane.showMessageDialog(null,"Vehicle Added Successfully : "+regID,"Success",JOptionPane.PLAIN_MESSAGE);
                    }
                    else
                    {
                        /*Display error message to user, if the vehicle registration ID is not Unique*/
                        JOptionPane.showMessageDialog(null,regID+" : Regestration ID Exist !! Please give Unique ID","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                    // Case 2 used to get the Registration ID for Moped Vehicle
                    case "3": regID =  JOptionPane.showInputDialog(null,"Enter a Registration Number","Input Dialog",JOptionPane.PLAIN_MESSAGE);
                    regID = "Bicycle"+regID;
                    Bicycle bicycle = new Bicycle(regID,0,0,0,0);
                    /* Creating a Bicycle Object and assigning registration ID with default ID */
                    if(!uniqueTest(regID))
                    {
                        vehicleList.add(bicycle); //add the Bicycle vehicle details to Vehicle array list
                        JOptionPane.showMessageDialog(null,"Vehicle Added Successfully : "+regID,"Success",JOptionPane.PLAIN_MESSAGE);
                    }
                    else
                    {
                        /*Display error message to user, if the vehicle registration ID is not Unique*/
                        JOptionPane.showMessageDialog(null,regID+" : Regestration ID Exist !!","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                    break;

                    /* default case will used to display the error when user click out of Menu options */
                    default: JOptionPane.showMessageDialog(null,"Invalid input, please try again !!","Warning",JOptionPane.WARNING_MESSAGE);
                    break;
                }
            }
            else
            { 
                return;
            }
        }
        // Handling any error when user input wrong input
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error Found: \n"+ e +"","Warning",JOptionPane.WARNING_MESSAGE);
        }

    }
    
    /**
     * uniqueTest() method is used to check the Uniqueness for vehicle ie. registration ID
     * @param This method takes string registration ID value 
     * @return This method return true if found and false if it dose not found
     */
    private boolean uniqueTest(String regId)
    {
        boolean status = false; // using boolen variable to assign the status
        //for each loop to iterate the arraylist loop
        for(Vehicle vehicle : vehicleList)
        {
            if(vehicle.getVehicleId().equals(regId))
            {
                status = true;
            }
        }
        
        return status;
    }
     /**
     * Method to add New DeliveryVehicle
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    private void updateArrayList()
    {
        try{
            //FileInputstream used to open the respective file.
            File file = new File(fileLocation);
            FileInputStream fis = new FileInputStream(file);
            
            //BufferedReader object is used to read line.
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            boolean flag = true;
            //Flag is used to check the end of line in file.
            while (flag) {
                //Reads first line and assigns values to name variable
                String name = br.readLine();
                if (name != null) {
                    String vehicleId = name;
                    int numberOfDays = Integer.parseInt(br.readLine());
                    int numberOfDeliveries = Integer.parseInt(br.readLine());
                    double valueOfDeliveries = Double.parseDouble(br.readLine());
                    double numberOfMilesCovered = Double.parseDouble(br.readLine());
                    br.readLine();
                    
                    //Conditions to check and differentiate vehicle
                    if(name.contains("carBand1"))
                    {
                        
                        // Creating car object with parameters passing
                         
                        Car car = new Car(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered,0);
                        //Adding car object to Vehicle Arraylist
                        vehicleList.add(car);
                    }
                    else if(name.contains("carBand2"))
                    {
                        Car car = new Car(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered,1);
                        vehicleList.add(car);
                    }
                    else if(name.contains("carBand3"))
                    {
                        Car car = new Car(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered,2);
                        vehicleList.add(car);
                    }
                    else if(name.contains("carBand4"))
                    {
                        Car car = new Car(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered,3);
                        vehicleList.add(car);
                    }
                    else if(name.contains("carBand5"))
                    {
                        Car car = new Car(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered,4);
                        vehicleList.add(car);
                    }
                    else if(name.contains("Moped"))
                    {
                        Moped moped = new Moped(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered);
                        vehicleList.add(moped);
                    }
                    else
                    {
                        Bicycle bicycle = new Bicycle(name, numberOfDays,numberOfDeliveries, valueOfDeliveries,numberOfMilesCovered);
                        vehicleList.add(bicycle);
                    }

                }
                else
                {
                    //Says end of line in file
                    flag = false; 
                }

            }
            //Close the file
            br.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error Found\n"+e+"","Warning",JOptionPane.WARNING_MESSAGE);
        }

    }
    /*
     * Method write the details all the information to text file, this method will be called by different menthod whenever it is necessary
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    private void writeToFile()
    {
        try{
            //To create vechicleDetails text file and write all vehicle details to file
            FileWriter file = new FileWriter(fileLocation,false);
            BufferedWriter bufferedW = new BufferedWriter(file);
            
            //Loop used to write each vehicle details to the file
            for(Vehicle vehicle : vehicleList)
            {
                bufferedW.write(vehicle.getVehicleId()+"\r\n");
                bufferedW.write(vehicle.getNumberOfDays()+"\r\n");
                bufferedW.write(vehicle.getNumberOfDeliveries()+"\r\n");
                bufferedW.write(vehicle.getValueOfDeliveries()+"\r\n");
                bufferedW.write(vehicle.getNumberOfMilesCovered()+"\r\n");
                bufferedW.write("++++++++++++++++"+"\r\n");//Used to differentiate from each account.
            }
            //Always close buffereader object created
            bufferedW.close();
            file.close();
            vehicleList.clear();
            //updateArrayList();
        }
        //To handle exception and popup appripriate error message
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error Found\n"+e+"","Warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    /*
     * Method write the displays all the information to text file
     * @param This method does not take any parameter
     * @return This method does not return any value
     */
    private void displayFleetReport()
    {
        String line = "";
        //Assigning each vehicle details to a String Variable to display fleet report
        for(Vehicle v : vehicleList)
        {
            line += "Registration Id: "+v.getVehicleId() + "\t\t";
            line += " :: Service Days: "+v.getNumberOfDays() +"\t\t";
            line += " :: Number of deliveries: "+v.getNumberOfDeliveries() + "\t\t";
            line += " :: Value of Delivery: "+v.getValueOfDeliveries() + "\n";
        }
        
        JOptionPane.showMessageDialog(null,line,"Fleet Report",JOptionPane.INFORMATION_MESSAGE);
        line="";

    }
}