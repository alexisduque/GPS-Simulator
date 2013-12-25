/*

 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator

 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */
package gps.simulator.modele;

import gps.simulator.GPSimulatorGUI;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class Teltonica extends StandardGPS {

    public Teltonica(String address, int port, int period, int code, String file, GPSimulatorGUI sim) {
        super(address, port, period, code, file, sim);
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
                    stopIsSend();
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
    
    @Override
    public void sendTrame(String gpsTrame) {
        try {
            Socket socket = new Socket(sockAddress, sockPort);
            System.out.println("**** Connexion start - Teltonica GPS****");

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            // Send Trame throught socket
            out.println(gpsTrame);
            System.out.print("Sended : " + gpsTrame);
            // Waiting for ACK message
            System.out.println(in.readLine());
            socket.close();

        } catch (IOException e) {
            System.err.println("Oops ! Coudn't send data");
            //e.printStackTrace();
        }
    }
}