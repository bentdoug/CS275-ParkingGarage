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