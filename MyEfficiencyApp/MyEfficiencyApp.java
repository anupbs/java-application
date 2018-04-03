/**
 * 
 * @author Group -
 * @version 1.0
 * @date
 */
import javax.swing.*; 
public class MyEfficiencyApp
{
    // instance variables
    private double milesCovred, totalNoDeliveries, valueOfDelivery, vehicleIdentifier;
    private double avgRunCstBicycle= 250.00,avgRunCstMoped = 1000.00, avgRunCstCar = 2250.00;
    /**
     * Constructor for objects of class userInterface
     */
    public MyEfficiencyApp()
    {
        // initialise instance variables
    }
    public void userInterface()
    {
        //while used to give user back control once he complete each operations
        boolean status = false;
        while(!status)
        {
            String selectOptions= JOptionPane.showInputDialog(null,"Please select an menu option below:\n\n[1] Make Daily Data:\n\n[2] Run Efficiency Report\n\n[3] Uility Menu\n\n[4] Exit Application\n","MyEfficiencyApp",JOptionPane.PLAIN_MESSAGE);
            //if statement is used to handle cancle button in user input interface
            if(selectOptions != null){
            switch(selectOptions) // 
            {
                case "1": addDailyData();
                break;
                
                case "2": runEfficiencyReport();
                break;
                
                case "3":  String utilityOptions= JOptionPane.showInputDialog(null,"Please select an utility option below:\n\n[1] Add Delivery Vehicle:\n\n[2] Display Fleet Report\n\n","Utility Menu",JOptionPane.PLAIN_MESSAGE);
                if(utilityOptions=="1")
                {
                    addDeliveryVehicle();
                    break;
                }
                else{
                    displayFleetReport();
                    break;
                }
                
                case "4": return;
                
                default: JOptionPane.showMessageDialog(null,"Invalid input, please try again !!","Warning",JOptionPane.WARNING_MESSAGE);
                break;
            }}
            else
            { 
                return;
            }
        }
    }
    private void addDailyData()
    {
    }
    private void runEfficiencyReport()
    {
    }
    private void addDeliveryVehicle()
    {
    }
    private void displayFleetReport()
    {
    }
}
