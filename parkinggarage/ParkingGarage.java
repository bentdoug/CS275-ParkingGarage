/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package parkinggarage;

/**
 *
 * @author btdou
 */
public class ParkingGarage {

    /**
     * 
     */
    private static int[] floorProfile;
    private static int hourlyRate;
    
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
        // TODO code application logic here
    }
    
}
