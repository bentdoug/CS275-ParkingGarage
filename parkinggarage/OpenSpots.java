package parkinggarage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


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
    //Returns a boolean which represents whether or not the parking garage is full.
    //True means the garage is full. False means the garage is not full.
        
        String source1 = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UnusedIDs.txt";
        FileInputStream inStream1 = new FileInputStream(source1);
        DataInputStream daStream1 = new DataInputStream(inStream1);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(daStream1));
        
        /*
        String source2 = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\currentGarageStatus.txt";
        FileInputStream inStream2 = new FileInputStream(source2);
        DataInputStream daStream2 = new DataInputStream(inStream2);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(daStream2));
        
        int spotsTaken;
        int numFloors = Integer.parseInt(reader2.readLine());
        boolean isFull = true;
        
        int i = 1;      
        while(i <= numFloors && isFull == true) {
            spotsTaken = Integer.parseInt(reader1.readLine());
            if(spotsTaken < Integer.parseInt(reader2.readLine())) {
                isFull = false;
            }
            i++;
        }
        */
        
        boolean isFull = (reader1.readLine() == null);
        daStream1.close();
        //daStream2.close();
        return isFull;
    }
    
    public static int getNumOpenSpots() throws FileNotFoundException, IOException {
    //Written by Ethan Cuthbertson
    //Returns an int which tell how many open spots are left in the whole parking garage
        
        String source1 = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UsedIDs.txt";
        //THIS VERSION WORKS FOR ETHAN (Who has messed up package organization) String source1 = new File("").getAbsolutePath() + "\\src\\ParkingGarage\\CS275-ParkingGarage\\txtfiles\\database\\UsedIDs.txt";
        FileInputStream inStream1 = new FileInputStream(source1);
        DataInputStream daStream1 = new DataInputStream(inStream1);
        BufferedReader reader1 = new BufferedReader(new InputStreamReader(daStream1));
        
        String source2 = new File("").getAbsolutePath() + "\\src\\txtfiles\\config\\floorProfiles.txt";
        //THIS VERION WORKS FOR ETHAN (Who has messed up package organization) String source2 = new File("").getAbsolutePath() + "\\src\\ParkingGarage\\CS275-ParkingGarage\\txtfiles\\config\\floorProfiles.txt";
        FileInputStream inStream2 = new FileInputStream(source2);
        DataInputStream daStream2 = new DataInputStream(inStream2);
        BufferedReader reader2 = new BufferedReader(new InputStreamReader(daStream2));
        
        int numFloors = Integer.parseInt(reader2.readLine());
        int totalSpots = 0;
        for(int i = 0; i < numFloors; i++) {
            int intFloorSpots = Integer.parseInt(reader2.readLine());
            totalSpots += intFloorSpots;
        }
        
        int totalOpenSpots = totalSpots;
        
        while(reader1.readLine() != null) {
            totalOpenSpots--;
        }
        /*
        for(int i = 1; i <= numFloors; i++) {
            spotsTaken = Integer.parseInt(reader1.readLine());
            int curFloor = Integer.parseInt(reader2.readLine());
            if(spotsTaken < curFloor) {
                totalOpenSpots += curFloor - spotsTaken;
            }
        }*/
        daStream1.close();
        daStream2.close();
        return totalOpenSpots;
    }
    
    // Do a PSVM and try to fix
    public static void main (String[] args) throws FileNotFoundException, IOException {
        /*String source = new File("").getAbsolutePath() + "\\src\\ParkingGarage\\CS275-ParkingGarage\\txtfiles\\database\\currentGarageStatus.txt";
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
        daStream.close();*/
        System.out.println(getParkingStatus());
        System.out.println(getNumOpenSpots());
    }
    
    /*
      11/21/21
      Ethan Cuthbertson: Fixed four glitches
      1. NO In the code's body, there was improper capitilization, as parkinggarage was written as "ParkingGarage".
      2. I mistook the first line of currentGarageStatus.txt as the line which stores the number of floors instead of the first line of floorProfiles.
      3. Given the above mistake, the use of indexes represented by "int i" was all mixed up and incorrect.
      4. By using the BufferedReader method "read()" I was getting incorrect numbers. I know use "readLine" then convert it from String to int.
    */
}
