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
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\timesIn.txt";
        FileInputStream stream = new FileInputStream(filePath);
        DataInputStream in = new DataInputStream(stream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine;
        
        int[][] timesIn = new int[500][2];
        //Amount of times read from file into the array
        int ctr = 0;
        
        //read used times into timesIn
        while((strLine = br.readLine()) != null){
            String[] line = strLine.split(" ");
            timesIn[ctr][0] = Integer.parseInt(line[0]);
            timesIn[ctr][1] = Integer.parseInt(line[1]);
            ctr++;
        }
        
        int[][] updatedTimes = new int[ctr-1][2];
        int OldList = 0;
        int NewList = 0;
        while(NewList<ctr-1){
            if(timesIn[OldList][0] != id){
                updatedTimes[NewList][0] = timesIn[OldList][0];
                updatedTimes[NewList][1] = timesIn[OldList][1];
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
            output.append(updatedTimes[x][0] + " " + updatedTimes[x][1] + "\n");
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
