package parkinggarage;
import parkinggarage.databaseIO;

/**
 * Should I still import databaseIO? Since this is a subclass of parkingGarage, should parkingGarage import it instead?
 * I checked old CS-174 code and it doesn't loom like subclasses need to import anything their superclass imports.
 * @author cuth5
 */

public class OpenSpots{   //Doesn't need to extend. I could call the variable floorprofile from parkinggarage if I need it.

    private int[] refreshOpenSpots()   //Should garageStatus be changed to databaseIO.getParkingStatus?
    {
        int[] garageStatus = databaseIO.getParkingStatus();
        int[] openSpots = new int[garageStatus.length];
        for(int i = 0; i < garageStatus.length; i++)
        {
            openSpots[i] = ParkingGarage.floorProfile[i] - garageStatus[i];   //If static status gets removed from floorProfile, this should be updated to this.floorProfile[i].
        }
        return openSpots;
    }      
}
