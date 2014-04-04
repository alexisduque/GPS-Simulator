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
public class NomadicMax extends StandardGPS {

    public NomadicMax(String address, int port, int period, int code, String file, GPSimulatorGUI sim) {
        super(address, port, period, code, file, sim);
        this.modele = "Nomadic";
    }

    @Override
    public void run() {
       try {
            socket = new Socket(sockAddress, sockPort);
            out = new PrintStream(socket.getOutputStream());
            this.timeZone = "Europe/Paris";
            this.listen = new ListenThread(socket, this);
            listen.start();
            try {
                System.out.println("**** Connexion start - "+this.modele+" GPS****");
                   while (true) {
                       // Check if stop thread msg send
                       testStop();
                       
                       if (filsBufer != null) {
                           sendTrame(filsBufer);
                           filsBufer = null;
                       }
                       
                       // Format and print Date
                       Date date = new Date();
                       TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
                       String dateString = dateFormat.format(date);
                       //Show formated Date
                       System.out.println(dateString);
                       // Build trame
                       String gpsTrame = "1000000003,20140330110354,5.268130,"
                           + "44.809970,0,160,564,8,2\r\n1000000003,2014033011"
                           + "0409,5268146,44.809970,0,068,564,9,2\r\n10000000"
                           + "03,20140330110424,5.268151,44.809986,0,010,564,8"
                           + ",2\r\n1000000003,20140330110439,5.268170,44.80998"
                           + "1,0,020,564,8,2\r\n1000000003,20140330110456,5.2"
                           + "68268,44.809956,0,122,564,9,2\r\n1000000003,2014"
                           + "0330110511,5.268253,44.809980,0,034,564,10,2\r\n100"
                           + "0000003,20140330110526,5.268255,44.809980,0,034,564"
                           + ",10,2\r\n1000000003,20140330110541,5.268253,44.80998"
                           + "0,0,034,564,9,2\r\n";
                       
                       // Try to connect to server and send data
                       sendTrame(gpsTrame);
                       stopIsSend();
                       //Sleep
                       sleep();
                   }
                   
               } catch (InterruptedException e) {
                   out.close();
                   //socket.close();
                   System.err.println("GPS Stopped by user");
               }
                
        } catch (IOException e) {
            System.err.println("Oops ! Coudn't send data");
            //e.printStackTrace();
        }
    }
}