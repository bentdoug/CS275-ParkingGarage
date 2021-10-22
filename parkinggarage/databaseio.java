package parkinggarage;
import java.io.*;

public class dataBaseIo {


    public static void demoReadFile(String[] args) throws IOException {
        //demo of syntax for reading from a txt file
        // C:\Users\Connor\Documents\CS275\CS275-ParkingGarage\txtfiles\database
        //open the text file
        FileInputStream stream = new FileInputStream("C:\\Users\\Connor\\Documents\\CS275\\S275-ParkingGarage\\txtfiles\\database\\DemoRead");
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        //read the file line by line
        while((strLine = br.readLine()) != null){
            //print the content to the console
            System.out.println(strLine);
        }
        in.close();
    }
    public void newCar(id,time) {
        //stores the customer id and time they came in
    }

    public int[] getUsedIDs() {
        //returns an array of id numbers that are currently available to be assigned to customers
        int[] UsedIDs = checkInOut.used;
        return UsedIDs;
    }

    public int[] getUnusedIDs() {
        //returns an array of id numbers in use and id numbers not in use and rewrites them to the appropriate flat file
        int[] UnusedIDs = checkInOut.unused;
        return UnusedIDs;
   }

   public void returnUsedUnusedIDs(used, unused) {
        //receives an updated array of id numbers in use and id numbers not in use and re-writes them to the
         //appropriate flat file
   }

   public void getTimeIn(Id) {
        //recieves the customers id and returns the time they entered the parking garage
   }

    public static int[] getParkingStatus(){

        return new int[0];
    }

}