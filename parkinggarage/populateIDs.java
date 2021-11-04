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
 *This class simply fills the UnusedIDs.txt file with a specified number of IDs in order to start the garage off. This should
 * only happen once; when the garage is first "opening"
 * @author bentdoug
 */
public class populateIDs {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String args[]) throws FileNotFoundException, IOException {
        String filePath = new File("").getAbsolutePath() + "\\src\\txtfiles\\database\\UnusedIDs.txt";
        Writer output;
        output = new BufferedWriter(new FileWriter(filePath, false));
        for(int x = 0; x<250; x++){
            output.append(x+1 + "\n");
        }
        output.close();
}
