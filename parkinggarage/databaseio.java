<<<<<<< Updated upstream
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

=======
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
        log.log("Starting newCar()");
        //stores the customer id and time they came in
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\timesIn.txt";
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(id + " " + time + "\n");
        output.close();
    }

    public static int[] getUnusedIDs() throws FileNotFoundException, IOException {
        log.log("Starting getUnusedIDs()");
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
        log.log("Starting getUsedIDs()");
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
        log.log("Starting returnUsedUnusedIDs()");
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
   public static void returnUsed(int id) throws FileNotFoundException, IOException{
       log.log("Starting returnUsed()");
       /** Used**/
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UsedIDs.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        
        /**Creates and fills an array bigger than the amount of spots in garage with the used IDs**/
        int[] UsedIDs = new int[500];
        //Amount of IDs read from file into the array
        int ctr = 0;
        
        //read used Ids into UsedIDs[]
        while((strLine = br.readLine()) != null){
            String line = strLine;
            UsedIDs[ctr] = Integer.parseInt(line);
            ctr++;
            }
        int[] newUsed = new int[ctr-1];
        //transfers UsedIDs into newUsed - leaves out the ID that is no longer used
        for(int x = 0; x<ctr-1; x++){
            if(UsedIDs[x]!=id){
                newUsed[x] = UsedIDs[x];
            }
        }
        
        
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<newUsed.length; x++){
            output.write(String.valueOf(newUsed[x]) + "\n");
        }
        output.close();
        
        //Adds the newly UnusedID to the list of Unused IDs
        String filePath2 = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UnusedIDs.txt";
        FileInputStream stream2 = new FileInputStream(filePath2);
        DataInputStream in2 = new DataInputStream(stream2);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
        String strLine2;
        int[] UnusedIDs = new int[500];
        int ctr2 = 0;
        
        //read the UnusedIDs into the UnusedIDs array
        while((strLine2 = br.readLine()) != null){
            UnusedIDs[ctr2] = Integer.parseInt(strLine2);
            ctr2++;
            }
        //creates array with enough room for all the UnusedIDs and the new one
        int[] newUnused = new int[ctr2+1];
        
        //placing newly UnusedID into array in numerical order (least -> most)
        boolean placed = false;
        int y;
        
        //base case
        if(id<UnusedIDs[0]){
            newUnused[0] = id;
            placed = true;
        }
        int index = 0;
        while(!placed && index < newUnused.length){
            if(UnusedIDs[index]<id){
                newUnused[index] = UnusedIDs[index];                
            }
        }
        
        /** Unused**/
        output = new BufferedWriter(new FileWriter(filePath2, false));
        for(int x = 0; x<newUnused.length; x++){
            System.out.println(newUnused[x]);
            output.write(newUnused[x] + "\n");
        }
        output.close();
        
        
   }

   public static String getTimeIn(int id) throws FileNotFoundException, IOException {
        log.log("Starting getTimeIn()");
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

    public static int[] getParkingStatus(){

        return new int[0];
    }

>>>>>>> Stashed changes
}