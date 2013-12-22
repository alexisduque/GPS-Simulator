/*

 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator

 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */
package gps.simulator.modele;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class Nomadic extends StandardGPS implements Runnable {

    public Nomadic(String address, int port, int period, int code, String file) {
        super(address, port, period, code, file);
    }

    @Override
    public void run() {
        this.timeZone = "Europe/Paris";

        //Try to read position file
        try {
            Scanner scanner = new Scanner(new FileReader(fileGPS));
            String line = null;
            try {
                while (scanner.hasNextLine()) {
                    // Check if stop thread msg send
                    testStop();
                    // Format and print Date
                    Date date = new Date();
                    TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
                    String dateString = dateFormat.format(date);
                    //Show formated Date
                    System.out.println(dateString);
                    // Read line frome file
                    line = scanner.nextLine();
                    String[] splits = line.split(",");
                    System.out.println("Param number : " + splits.length);
                    // Build trame
                    String gpsTrame = this.imei + "," + dateString + "," + splits[2]
                            + "," + splits[3] + "," + splits[4] + "," + splits[5] + ","
                            + splits[6] + "," + splits[7] + "," + splits[8] + "\r\n";

                    // Try to connect to server and send data
                    sendTrame(gpsTrame);
                    //Sleep
                    sleep();
                }

            } catch (InterruptedException e) {
                scanner.close();
                System.err.println("GPS Stopped by user");
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.err.println("Oops ! Error reading positions file");
        }
    }
}