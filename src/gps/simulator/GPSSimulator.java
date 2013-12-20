/*
 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator
 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */

package gps.simulator;

/**
 *
 * @author Alex
 */
public class GPSSimulator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Nomadic gpsTracker = new Nomadic("192.168.0.11", 42400, 15, 2000000001, "D:\\gps_collect\\perl\\jeu_essai_positions.txt");
        gpsTracker.Run();
        
    }
    
}
