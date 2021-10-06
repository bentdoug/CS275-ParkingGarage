package parkinggarage;

/**
 * Should I still import databaseIO? Since this is a subclass of parkingGarage, should parkingGarage import it instead?
 * I checked old CS-174 code and it doesn't loom like subclasses need to import anything their superclass imports.
 * @author cuth5
 */

public class OpenSpots extends ParkingGarage{
    
    int[] floorStatus;  //An array of numFloor cells which holds a value representing how many spots are left on each floor.
    
    public OpenSpots(int[] floorProfile, int hourlyRate)
    {
        super(floorProfile, hourlyRate);    //Calls constructor on ParkingGarage.
        this.floorStatus = new int[floorProfile.length];
    }
    
    private int[] getOpenSpots()
    {
        return this.floorStatus;
    }
    
    //int[] garageStatus represents the total number of cars on each floor.
    private void refreshOpenSpots(int[] garageStatus)   //Should garageStatus be changed to databaseIO.getParkingStatus?
    {
        for(int i = 0; i < garageStatus.length; i++)
        {
            this.floorStatus[i] = floorProfile[i] - garageStatus[i];   //If static status gets removed from floorProfile, this should be updated to this.floorProfile[i].
        }
    }      
}
