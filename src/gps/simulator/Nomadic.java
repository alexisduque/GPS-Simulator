/*
 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator

 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */
package gps.simulator;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Alex
 */
public class Nomadic implements Runnable {

    private String timeZone;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private int sockPort;
    private String sockAddress;
    private int sendPeriod;
    private int imei;
    private String fileGPS;
    private boolean stopGPS;

    public Nomadic() {
        this.sockAddress = "localhost";
        this.sockPort = 42400;
        this.sendPeriod = 20;
        this.imei = 2000000001;
        this.fileGPS = "D:\\gps_collect\\perl\\jeu_essai_positions.txt";
        this.stopGPS = false;
    }

    public Nomadic(String address, int port, int period, int code, String file) {
        this.sockAddress = address;
        this.sockPort = port;
        this.sendPeriod = period;
        this.imei = code;
        this.fileGPS = file;
        this.stopGPS = false;
    }

    public void run() {

        timeZone = "Europe/Paris";

        // Trame NS90 with 9 parameters
        String tram0 = "356307040983040,20120703172143,4.735957,44.533772,0,270,227,4,2";
        String trame4 = "2000000001,20120630071416,4.882276,45.780171,0,0,0,5,2,0.0,0,0.01,0.01,0";

        //Try to read position file
        try {
            Scanner scanner = new Scanner(new FileReader(fileGPS));
            String line = null;
            try {
                while (scanner.hasNextLine()) {
                        testStop();
                        Date date = new Date();
                        //Format and print Date
                        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
                        String dateString = dateFormat.format(date);
                        System.out.println(dateString);

                        line = scanner.nextLine();
                        String[] splits = line.split(",");
                        System.out.println("Param number : " + splits.length);
                        String gpsTrame = this.imei + "," + dateString + "," + splits[2]
                                + "," + splits[3] + "," + splits[4] + "," + splits[5] + ","
                                + splits[6] + "," + splits[7] + "," + splits[8] + "\r\n";

                        // Try to connect to server
                        try {
                            Socket socket = new Socket(sockAddress, sockPort);
                            System.out.println("**** Connexion start ****");

                            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintStream out = new PrintStream(socket.getOutputStream());

                            out.println(gpsTrame);
                            System.out.print("Sended : " + gpsTrame);

                            System.out.println(in.readLine());
                            socket.close();

                        } catch (IOException e) {
                            System.err.println("Oops ! Coudn't send data");
                            //e.printStackTrace();
                        }
                                                    //Sleep
                        try {
                              Thread.currentThread().sleep(sendPeriod * 1000);
                            } catch (InterruptedException e) {
                              
                            }
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

    public synchronized void stopGPS() {
        this.stopGPS = true;
    }

    public synchronized void testStop() throws InterruptedException {
        if (stopGPS) {
            throw new InterruptedException();
        }
    }

    public void setAddress(String address) {
        this.sockAddress = address;
    }

    public String getAddress() {

        return this.sockAddress;
    }

    public void setPort(int port) {
        this.sockPort = port;
    }

    public int getPort() {
        return this.sockPort;
    }
}
