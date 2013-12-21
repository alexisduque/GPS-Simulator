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
        
        if (args.length  == 5 )
        {
            System.out.print("Param : ");
            for (String arg : args )
            {
                System.out.print(arg + ", ");
            }
            System.out.println();
            Nomadic gpsTracker = new Nomadic(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), Integer.parseInt(args[3]), args[4]);
            gpsTracker.Run();
            
        } else if (args[0]  == "help") {
           
            System.out.println("Usage : gpssimulator ip port period ime file");
            System.exit(0);
            
        } else if (args.length  != 5) {
            System.out.println("Use default value : 192.168.0.11, 42400, 15, D:\\gps_collect\\perl\\jeu_essai_positions.txt");
            Nomadic gpsTracker = new Nomadic("192.168.0.11", 42400, 15, 2000000001, "D:\\gps_collect\\perl\\jeu_essai_positions.txt");
            gpsTracker.Run();
              
        }
    }  
}
