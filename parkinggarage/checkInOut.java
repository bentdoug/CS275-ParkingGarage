
package parkinggarage;
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
    public static Object[] checkIn(){
        int id = getIdNumber();
        Date date = new Date();
        long time = date.getTime();
        //databaseIO.newCar(id, time);
        Object[] ret = new Object[]{id, time};
        return ret;
    }
    
    /**
     * 
     * @param used list of ID numbers that are in use
     * @param unused list of ID numbers that are not in use
     * @return ID number assigned to customer checking in right now
     */
    public static int getIdNumber(){
        //int[] used = databaseIO.getUsedIDs();
        
        //int[] unused = databaseIO.getUnusedIDs();
        
        int[] tempUsed = new int[used.length+1];
        int[] tempUnused = new int[unused.length-1];
        int id = unused[0];
        
        for(int x = 1; x<unused.length; x++){
            tempUnused[x-1] = unused[x];
        }
        for(int x = 0; x<used.length; x++){
            tempUsed[x] = used[x];
        }
        tempUsed[tempUsed.length-1] = id;
        //System.out.print("used ");
        for(int x = 0; x<tempUsed.length; x++){
            //System.out.print(tempUsed[x]+", ");
        }
        //System.out.print("\nunused ");
        for(int x = 0; x<tempUnused.length; x++){
            //System.out.print(tempUnused[x]+", ");
        }
        
        
        used = tempUsed;
        unused = tempUnused;
        //databaseIO.returnUsedUnusedIDs(tempUsed, tempUnused);
        
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
    public double checkOut(int numRecieved){
        double charge = 0;
        int numDigits = Integer.toString(numRecieved).length();
        
        if(numDigits > 3){
            
        }
        else{
            charge = hourlyParking(numRecieved);
        }
        return charge;
    }
    
    private static double hourlyParking(int Id){
        double charge = 0;
        //long timeIn = databaseIO.getTimeIn(Id);
        long timeIn = 1633625627268L;
        Date date = new Date();
        long timeOut = date.getTime();
        long difference = timeOut-timeIn;
        long minutesInside = difference/60000;
        System.out.println(minutesInside+" minutes inside");
        System.out.println((double)minutesInside/60);
        double timeCharged = (double)minutesInside/60;
        charge = (double)timeCharged*ParkingGarage.hourlyRate; //hourlyRate is currently hard coded to 8.25
        return charge;
    }
    
    public static void main(String[] args){
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
        System.out.println(returned[0]);
        System.out.println(returned[1]);
    }
}
