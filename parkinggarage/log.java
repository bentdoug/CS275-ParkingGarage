/*
 * Program that logs things to Log.txt for debugging purposes
 */
package parkinggarage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;  

/**
 *
 * @author bentdoug
 */
public class log {

    
      
    public static String getTimeDate(){   
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd__HH_mm");  
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    /**
     * @param args the command line arguments
     */
    public static void log(String logThis) throws IOException{
        String startTimeDate = getTimeDate();
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\log\\log-" + startTimeDate + ".txt";
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(logThis+"\n");
        output.close();
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
