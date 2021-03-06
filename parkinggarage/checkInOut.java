
package parkinggarage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
//import parkinggarage.databaseio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.text.SimpleDateFormat; 
/**
 * checkInOut is the class housing the programs that are utilized when a customer checks
 * in or out.
 * @author bentdoug
 */
public class checkInOut{
    
    static int[] unused = {0,1,2,3,4,5};
    static int[] used = {};
    
    public int comTestInt(int numRecieved){
        int ret = numRecieved;
        return ret;
    }
    
    public String comTestString(int numRecieved){
        if(numRecieved == 420){
            return("lit");
        }
        else{
            return("bummer");
        }
    }
    
    
    /**
     * checkIn is called through the GUI when a customer checks in
     * 
     * @return - returns customers Id, time they arrived to the GUI, and the floor
     * they chose to park on
     */
    public static Object[] checkIn() throws IOException{
        log.log("Starting checkIn()");
        int id = getIdNumber();
        Date date = new Date();
        long time = date.getTime();
        System.out.println("time = " + time);
        databaseio.newCar(id, time);
        Object[] ret = new Object[]{id, time};
        return ret;
        
    }
    
    /**
     * 
     * @param used list of ID numbers that are in use
     * @param unused list of ID numbers that are not in use
     * @return ID number assigned to customer checking in right now
     */
    public static int getIdNumber() throws IOException{
        log.log("Starting getIdNumber()");
        /**Get Used and Unused arrays from DB**/
        int[] used = databaseioIDs.getUsedIDs();
        int[] unused = databaseioIDs.getUnusedIDs();
        /**Create tempUsed and tempUnused (new/edited used & unused)**/
        int[] tempUsed = new int[used.length+1];
        int[] tempUnused = new int[unused.length-1];
        int id = unused[0];
        /**Fill tempUnused with all unused except the first one (the ID)**/
        for(int x = 1; x<unused.length; x++){
            tempUnused[x-1] = unused[x];
        }
        /**Fill tempUsed with all used including the ID**/
        for(int x = 0; x<used.length; x++){
            tempUsed[x] = used[x];
        }
        tempUsed[tempUsed.length-1] = id;
        /**Return updated arrays to DB**/
        databaseioIDs.returnUsedUnusedIDs(tempUsed, tempUnused);
        
        return id;
    }
    
    
    
    
    
    /**
     * checkOut is called through the GUI when a customer check out
     * if only an ID number was received, hourly parking is called, if an
     * account number is given, the customers account is searched for and payment
     * is processed accordingly
     * @param numRecieved - the number entered by the customer trying to check out - either a parking
     * id (1-3 digits) or account number (5 digits)
     * @return - returns the charge due from the customer at the time of exiting 
     * the garage
     */
    public static double checkOut(int numRecieved) throws IOException{
        log.log("Starting checkOut()");
        System.out.println("Starting Checkout");
        double charge = 0;
        int numDigits = Integer.toString(numRecieved).length();
        
        if(numDigits > 3){
            
        }
        else{
            charge = hourlyParking(numRecieved);
            databaseioIDs.returnUsed(numRecieved);
        }
        databaseioTimes.removeTimeIn(numRecieved);
        return charge;
    }
    
    private static double hourlyParking(int Id) throws IOException{
        log.log("Starting hourlyParking()");
        double charge = 0;
        System.out.println("In hourly parking");
        String dbTime = databaseio.getTimeIn(Id);
        long timeIn = Long.parseLong(dbTime);
        //long timeIn = 1633625627268L;
        Date date = new Date();
        long timeOut = date.getTime();
        long difference = timeOut-timeIn;
        long minutesInside = difference/60000;
        //System.out.println(minutesInside+" minutes inside");
        int timeCharged = (int)minutesInside/60;
        charge = (double)timeCharged*ParkingGarage.hourlyRate; //hourlyRate is currently hard coded to 8.25
        if(charge < ParkingGarage.hourlyRate){
            charge = ParkingGarage.hourlyRate;
        }
        return charge;
    }
    public static boolean isTimeIn(int Id) throws FileNotFoundException, IOException{
        String filePath = new File("").getAbsolutePath() + "/src/txtfiles/database/timesIn.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        String strId = Integer.toString(Id);
        
        //read the file line by line
        while((strLine = br.readLine()) != null){
            String[] line = strLine.split(" ");
            if(line[0].equals(strId)){
                return true;
            }
        }
        in.close();
        return false;
    }
    public static String timeIn(int Id)throws IOException{
        String java_date = "";
        try {
            String dbTime = databaseio.getTimeIn(Id);
            long timeIn = Long.parseLong(dbTime);
            Date date = new Date(timeIn);
            SimpleDateFormat jdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            java_date = jdf.format(date);
        } catch (IOException ex) {
            Logger.getLogger(checkInOut.class.getName()).log(Level.SEVERE, null, ex);
        }
        return java_date;
        
    }
    
     private static LocalDateTime timeOut(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");  
        LocalDateTime now = LocalDateTime.now();  
        return(now);
    }
    public static void main(String[] args) throws IOException{
        /**System.out.println("Beginning getIdNumber test");
        //testing getIdNumber
        for(int x = 0; x<5; x++){
            int Id = getIdNumber();
            System.out.println("\nId " + Id);
        }
        System.out.println("Beginning hourlyParking test");
        //testing hourlyParking
        System.out.println("$" + hourlyParking(2));
        */
        Object[] returned = checkIn();
        System.out.println("ID = " + returned[0] + " Time in = "+ returned[1]);
        
        checkOut(1);
        
    }
}
