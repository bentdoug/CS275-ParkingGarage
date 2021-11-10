
package parkinggarage;

import java.io.IOException;
/**
 *
 * @author btdou
 */
public class ParkingGarage {

    /**
     * 
     */
    public static int[] floorProfile;
    public static double hourlyRate = 8.25;
    
    /**
     * Instantiates the specific parking garage
     * @param floorProfile - an array of numFloors cells each of which holds a value
     *      representing the total amount of parking spots on that respective floor
     * @param hourlyRate - rate at which the customers get charged per hour of parking
     */
    public ParkingGarage(int[] floorProfile, int hourlyRate){
        this.floorProfile = floorProfile;
        this.hourlyRate = hourlyRate;
    }
    
    @Override
    public String toString(){
        String ret = "";
        String floorProfileString = "";
        String hourlyRateString = "";
        ret = "This garage has " + floorProfile.length + " floors.\n";
        for(int x = 0; x < floorProfile.length; x++){
            ret += "Floor " + (x+1) + " has " + floorProfile[x] + " spots. \n";
        }
        ret += "The hourly rate for parking here is $" + hourlyRate;
        return ret;
    }
    public static void main(String[] args) {
        newGUI GUI = new newGUI();
        GUI.show();
        
        try {
            // TODO code application logic here
            checkInOut test = new checkInOut();
            System.out.println(test.checkOut(0));
        } catch (IOException ex) {
            Logger.getLogger(ParkingGarage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
