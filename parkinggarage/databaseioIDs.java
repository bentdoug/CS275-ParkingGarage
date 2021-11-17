package parkinggarage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 *
 * @author bentdoug
 */
public class databaseioIDs {
    
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
        log.log("Starting returnUsedUnusedIDs() \n Starting to work on Used IDs");
        /** Used**/
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UsedIDs.txt";
        FileInputStream stream = new FileInputStream(filePath);
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<used.length; x++){
            output.append(used[x] + "\n");
        }
        output.close();
        log.log("Starting to work on Unused IDs");
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
       log.log("Starting returnUsed() \n Starting to work on UsedID list");
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
        //base case
        int oldList = 0;
        int newList = 0;
        if(UsedIDs[0]==id){
            oldList++;
        }
        while(newList<ctr-1){
            if(UsedIDs[oldList]!=id){
                newUsed[newList] = UsedIDs[oldList];
                oldList++;
                newList++;
            }
            else{
                oldList++;
            }
        }
        
        
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<newUsed.length; x++){
            if(newUsed[x] != 0){
                output.write(String.valueOf(newUsed[x]) + "\n");
            }
        }
        output.close();
        log.log("Starting to work on UnusedID list");
        //Adds the newly UnusedID to the list of Unused IDs
        String filePath2 = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UnusedIDs.txt";
        FileInputStream stream2 = new FileInputStream(filePath2);
        DataInputStream in2 = new DataInputStream(stream2);
        BufferedReader br2 = new BufferedReader(new InputStreamReader(in2));
        String strLine2;
        int[] UnusedIDs = new int[500];
        int ctr2 = 0;
        
        //read the UnusedIDs into the UnusedIDs array
        while((strLine2 = br2.readLine()) != null){
            UnusedIDs[ctr2] = Integer.parseInt(strLine2);
            ctr2++;
            }
        //creates array with enough room for all the UnusedIDs and the new one
        int[] newUnused = new int[ctr2+1];
        
        //placing newly UnusedID into array in numerical order (least -> most)
        boolean placed = false;
        int y;
        int index = 0;
        //base case
        if(id<UnusedIDs[0]){
            newUnused[0] = id;
            placed = true;
            index++;
        }
        
        while(!placed && index < newUnused.length){
            if(UnusedIDs[index]<id){
                newUnused[index] = UnusedIDs[index];
                index++;
            }
            else{
                newUnused[index] = id;
                placed = true;
                index++;
            }
        }
        while(placed && index < newUnused.length){
            newUnused[index] = UnusedIDs[index-1];
            index++;
        }
        
        /** Unused**/
        output = new BufferedWriter(new FileWriter(filePath2, false));
        for(int x = 0; x<newUnused.length; x++){
            
            output.write(newUnused[x] + "\n");
        }
        output.close();
        
        
   }


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
