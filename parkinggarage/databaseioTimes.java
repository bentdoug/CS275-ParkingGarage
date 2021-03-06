package parkinggarage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;

/**
 *
 * @author bentdoug
 */
public class databaseioTimes {

    public static void removeTimeIn(int id) throws IOException{
        log.log("Starting removeTimeIn");
       /** Used**/
        String filePath = new File("").getAbsolutePath() + "/src/txtfiles/database/timesIn.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        
        /** Pair of Arrays; ID has the customer IDs while times in
            stores the corresponding timesIn for those IDs **/
        int[] IDs = new int[500];
        Long[] timesIn = new Long[500];
        //Amount of times read from file into the array
        int ctr = 0;
        
        //read used times into timesIn
        while((strLine = br.readLine()) != null){
            String[] line = strLine.split(" ");
            IDs[ctr] = Integer.parseInt(line[0]);
            timesIn[ctr] = Long.parseLong(line[1]);
            ctr++;
        }
        
        
        int[] updatedIDs = new int[ctr-1];
        Long[] updatedTimes = new Long[ctr-1];
        
        int OldList = 0;
        int NewList = 0;
        while(NewList<ctr-1){
            if(IDs[OldList] != id){
                updatedIDs[NewList] = IDs[OldList];
                updatedTimes[NewList] = timesIn[OldList];
                OldList++;
                NewList++;
            }
            else{
                OldList++;
            }
        }
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<updatedTimes.length; x++){
            output.append(updatedIDs[x] + " " + updatedTimes[x] + "\n");
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
