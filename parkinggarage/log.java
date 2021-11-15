/*
 * Program that logs things to Log.txt for debugging purposes
 */
package parkinggarage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author bentdoug
 */
public class log {

    /**
     * @param args the command line arguments
     */
    public static void log(String logThis) throws IOException{
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\log\\log.txt";
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, true));
        output.append(logThis+"\n");
        output.close();
    }
    public static void main(String args[]) {
        // TODO code application logic here
    }
}
