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
        int numFloors = ParkingGarage.floorProfile[0];
        boolean isFull = true;
        
        int i = 1;      
        while(i <= numFloors && isFull == true) {
            spotsTaken = Integer.parseInt(reader.readLine());
            if(spotsTaken < ParkingGarage.floorProfile[i]) {
                isFull = false;
            }
            i++;
        }
        daStream.close();
        return isFull;
    }
    
    public static int getNumOpenSpots() throws FileNotFoundException, IOException {
    //Written by Ethan Cuthbertson
    //Returns an int which tell how many open spots are left in the whole parking garage
        
        String source = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\currentGarageStatus.txt";
        FileInputStream inStream = new FileInputStream(source);
        DataInputStream daStream = new DataInputStream(inStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(daStream));
        int spotsTaken;
        int numFloors = ParkingGarage.floorProfile[0];
        int totalOpenSpots = 0;
            
        for(int i = 1; i <= numFloors; i++) {
            spotsTaken = Integer.parseInt(reader.readLine());
            if(spotsTaken < ParkingGarage.floorProfile[i]) {
                totalOpenSpots += parkinggarage.floorProfile[i] - spotsTaken;
            }
        }
        daStream.close();
        return totalOpenSpots;
    }
    
    // Do a PSVM and try to fix
    public static void main (String[] args) throws FileNotFoundException, IOException {
        String source = new File("").getAbsolutePath() + "\\src\\ParkingGarage\\CS275-ParkingGarage\\txtfiles\\database\\currentGarageStatus.txt";
        System.out.println(source);
        FileInputStream inStream = new FileInputStream(source);
        DataInputStream daStream = new DataInputStream(inStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(daStream));

        for(int i = 0; i < 4; i++)
        {
            //String curLine = reader.readLine();
            int toOutput = Integer.parseInt(reader.readLine());
            System.out.println(toOutput);
        }
        daStream.close();
    }
    
    /*
      11/21/21
      Ethan Cuthbertson: Fixed four glitches
      1. WRONG THIS WAS CORRECT IN THE FIRST PLACE In the code's body, there was improper capitilization, as parkinggarage was written as "ParkingGarage".
      2. I mistook the first line of currentGarageStatus.txt as the line which stores the number of floors instead of the first line of floorProfiles.
      3. Given the above mistake, the use of indexes represented by "int i" was all mixed up and incorrect.
      4. By using the BufferedReader method "read()" I was getting incorrect numbers. I know use "readLine" then convert it from String to int.
    */
}
