package parkinggarage;
import java.io.*;

public class databaseio {


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
    public static void newCar(int id, long time) throws IOException {
        //stores the customer id and time they came in
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\timesIn.txt";
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(id + " " + time + "\n");
        output.close();
    }

    public static int[] getUnusedIDs() throws FileNotFoundException, IOException {
        //returns an array of id numbers that are currently available to be assigned to customers
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UnusedIDs.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        int[] UnusedIDs = new int[500];
        int ctr = 0;
        
        //read the file line by line
        while((strLine = br.readLine()) != null){
            String[] line = strLine.split(" ");
            UnusedIDs[ctr] = Integer.parseInt(line[0]);
            ctr++;
            }
        int[] ret = new int[ctr];
        for(int x = 0; x<ctr; x++){
            ret[x] = UnusedIDs[x];
        }
        return ret;
        
    }

    public static int[] getUsedIDs() throws FileNotFoundException, IOException {
        //returns an array of id numbers in use and id numbers not in use and rewrites them to the appropriate flat file
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UsedIDs.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        int[] UsedIDs = new int[500];
        int ctr = 0;
        
        //read the file line by line
        while((strLine = br.readLine()) != null){
            String line = strLine; //.split(" ");
            UsedIDs[ctr] = Integer.parseInt(line);
            System.out.println(UsedIDs[ctr]);
            ctr++;
            }
        int[] ret = new int[ctr];
        for(int x = 0; x<ctr; x++){
            ret[x] = UsedIDs[x];
        }
        return ret;
    }

   public static void returnUsedUnusedIDs(int[] used, int[] unused) throws FileNotFoundException, IOException {
        //receives an updated array of id numbers in use and id numbers not in use and re-writes them to the
         //appropriate flat file
        
        /** Used**/
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UsedIDs.txt";
        FileInputStream stream = new FileInputStream(filePath);
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<used.length; x++){
            output.append(used[x] + "\n");
        }
        output.close();
        
        /** Unused**/
        filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UnusedIDs.txt";
        stream = new FileInputStream(filePath);
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<unused.length; x++){
            output.write(unused[x] + "\n");
        }
        output.close();
        
   }
   
   /**
    * removes a used ID from UsedIDs and puts it back into UnusedIDs for reassignment to a new customer
    * @param id the ID that is no longer being used
    */
   public static void returnUsed(int id){
       
   }

   public static String getTimeIn(int id) throws FileNotFoundException, IOException {
        //recieves the customers id and returns the time they entered the parking garage
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\timesIn.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        String strId = Integer.toString(id);
        
        //read the file line by line
        while((strLine = br.readLine()) != null){
            String[] line = strLine.split(" ");
            if(line[0].equals(strId)){
                return line[1];
            }
        }
        in.close();
        return null;
   }

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
        while(i < ParkingGarage.floorProfile.length && isFull == false) {
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