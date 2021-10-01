/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkinggarage;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
/**
 *This class will be called whenever the program starts in order to configure and instantiate
 * the appropriate objects and classes properly
 * @author bentdoug
 */
public class parkingGarageConfig {
    
    
    /**
     * @floorProfiles - an array in which each cell represents a floor of the garage and the value within
     * each cell is the number of spots that are on that floor total
     */
    private static int[] floorProfiles;
    /**
     * @hourlyRate - the hourly rate at which customers are charged
     */
    private static int hourlyRate;
    
    public static void config() throws IOException{
        
        /**
         * Reads floorProfiles.txt - uses that info to determine how many floors there are as well as total spots on each 
         * of those floors number of floors is stored in numFloors while spots on each floor is housed in floorProfiles array
         */
        int numFloors = 0;
        int[] floorProfilesConfig = {0};
        int lineNum = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\Fall 2021 Semester\\Software Engineering\\ParkingGarage\\txtfiles\\floorProfiles.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
            // process the line.
                if(lineNum == 0){
                    numFloors = Integer.parseInt(line);
                }
                else if(lineNum == 1){
                    floorProfilesConfig = new int[numFloors];
                    floorProfilesConfig[lineNum-1] = Integer.parseInt(line);
                }
                else{
                    floorProfilesConfig[lineNum-1] = Integer.parseInt(line);
                }
                
                lineNum += 1;
            }
        }
        floorProfiles = floorProfilesConfig;
        
        /**
         * reads hourlyRate.txt and sets the hourly rate of the parking garage
         * IMPORTANT: directory path is specific to the hard drive on which the program is saved
         */
        int hourlyRateConfig = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(new File("D:\\Fall 2021 Semester\\Software Engineering\\ParkingGarage\\txtfiles\\hourlyRate.txt")))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                hourlyRateConfig = Integer.parseInt(line);
            }
            hourlyRate = hourlyRateConfig;
        }
    }
    
    public static void main(String[] args) throws IOException{
        config();
        
        for(int x = 0; x<floorProfiles.length; x++){
            System.out.println(floorProfiles[x]);
        }
        System.out.println(hourlyRate);
        
        ParkingGarage garage = new ParkingGarage(floorProfiles, hourlyRate);
        System.out.println(garage.toString());
    }
}
