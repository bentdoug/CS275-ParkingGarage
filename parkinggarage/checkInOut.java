
package parkinggarage;
import java.io.IOException;
import parkinggarage.ParkingGarage;
//import parkinggarage.databaseio;
import java.util.Date;
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
        int id = getIdNumber();
        Date date = new Date();
        long time = date.getTime();
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
        /**Get Used and Unused arrays from DB**/
        int[] used = databaseio.getUsedIDs();
        int[] unused = databaseio.getUnusedIDs();
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
        databaseio.returnUsedUnusedIDs(tempUsed, tempUnused);
        
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
        double charge = 0;
        int numDigits = Integer.toString(numRecieved).length();
        
        if(numDigits > 3){
            
        }
        else{
            charge = hourlyParking(numRecieved);
            //returnIdNumber(numRecieved);
        }
        
        return charge;
    }
    
    private static double hourlyParking(int Id) throws IOException{
        double charge = 0;
        long timeIn = Long.parseLong(databaseio.getTimeIn(Id));
        //long timeIn = 1633625627268L;
        Date date = new Date();
        long timeOut = date.getTime();
        long difference = timeOut-timeIn;
        long minutesInside = difference/60000;
        System.out.println(minutesInside+" minutes inside");
        double timeCharged = (double)minutesInside/60;
        charge = (double)timeCharged*ParkingGarage.hourlyRate; //hourlyRate is currently hard coded to 8.25
        return charge;
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
        
        double charge = checkOut(7);
        System.out.println(charge);
        /**Object[] returned = checkIn();
        System.out.println("ID,Time:");
        System.out.print(returned[0]+", ");
        System.out.println(returned[1]);
        **/
    }
}
