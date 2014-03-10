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
public class TK102 extends StandardGPS {

    public TK102(String address, int port, int period, int code, String file, GPSimulatorGUI sim) {
        super(address, port, period, code, file, sim);
        this.modele = "TK-102";
    }

    @Override
    public void run() {
       try {
            socket = new Socket(sockAddress, sockPort);
            out = new PrintStream(socket.getOutputStream());
            this.timeZone = "Europe/Paris";
            this.listen = new ListenThread(socket, this);
            listen.start();
            InputStream stream = TK102.class.getResourceAsStream("/gps/resources/tk102.txt");
            try (Scanner scanner = new Scanner(stream)) {
               System.out.println("**** Connexion start - "+this.modele+" GPS****");
               String line = null;
               try {
                   while (scanner.hasNextLine()) {
                       // Check if stop thread msg send
                       testStop();
                       
                       if (filsBufer != null) {
                           sendTrame(filsBufer);
                           filsBufer = null;
                       }
                       
                       // Format and print Date
                       //Date date = new Date();
                       //TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
                       //String dateString = dateFormat.format(date);
                       //Show formated Date
                       //System.out.println(dateString);
                       // Read line frome file
                       line = scanner.nextLine();
                       String gpsTrame = line + "\n";
                       sendTrame(gpsTrame);
                       stopIsSend();
                       //Sleep
                       sleep();
                   }
                   
               } catch (InterruptedException e) {
                   out.close();
                   //socket.close();
                   scanner.close();
                   System.err.println("GPS Stopped by user");
               }
           } catch(Exception e) {
               System.err.println("Oops ! Coudn't read frome file");
           }
                
        } catch (IOException e) {
            System.err.println("Oops ! Coudn't send data");
            //e.printStackTrace();
        }
    }
}