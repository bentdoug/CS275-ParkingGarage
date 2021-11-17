package parkinggarage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import parkinggarage.databaseio;

/**
 * Should I still import databaseIO? Since this is a subclass of parkingGarage, should parkingGarage import it instead?
 * I checked old CS-174 code and it doesn't loom like subclasses need to import anything their superclass imports.
 * @author cuth5
 */

public class OpenSpots{   //Doesn't need to extend. I could call the variable floorprofile from parkinggarage if I need it.

    /*
    private int[] refreshOpenSpots()   //Should garageStatus be changed to databaseIO.getParkingStatus?
    {
        int[] garageStatus = databaseio.getParkingStatus();
        int[] openSpots = new int[garageStatus.length];
        for(int i = 0; i < garageStatus.length; i++)
        {
            openSpots[i] = ParkingGarage.floorProfile[i] - garageStatus[i];   //If static status gets removed from floorProfile, this should be updated to this.floorProfile[i].
        }
        return openSpots;
    }*/
    
    public static boolean getParkingStatus() throws FileNotFoundException, IOException {
    //Written by Ethan Cuthbertson
    //Returns a boolean which represents wheter or not the parking garage is full
        
        String source = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\currentGarageStatus.txt";
        FileInputStream inStream = new FileInputStream(source);
        DataInputStream daStream = new DataInputStream(inStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(daStream));
        int spotsTaken;
        boolean isFull = true;
        
        int i = 0;      
        while(i < ParkingGarage.floorProfile.length && isFull == true) {
            spotsTaken = reader.read();
            if(spotsTaken < ParkingGarage.floorProfile[i]) {
                isFull = false;
            }
            i++;
        }
        daStream.close();
        return isFull;
    }
}
