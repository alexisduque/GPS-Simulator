/*
 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator
 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */
package gps.simulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StandardGPS {

    protected String timeZone;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    protected int sockPort;
    protected String sockAddress;
    protected int sendPeriod;
    protected int imei;
    protected String fileGPS;
    protected boolean stopGPS;

    public StandardGPS(){
        this.sockAddress = "localhost";
        this.sockPort = 42400;
        this.sendPeriod = 20;
        this.imei = 2000000001;
        this.fileGPS = "D:\\gps_collect\\perl\\jeu_essai_positions.txt";
        this.stopGPS = false;
    }

    public StandardGPS(String address, int port, int period, int code, String file) {
        this.sockAddress = address;
        this.sockPort = port;
        this.sendPeriod = period;
        this.imei = code;
        this.fileGPS = file;
        this.stopGPS = false;
    }

    public void run() {
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
    
    public void sleep() {
        try {
            Thread.currentThread().sleep(sendPeriod * 1000);
        } catch (InterruptedException e) {
        }
    }

    public void sendTrame(String gpsTrame) {
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
    }
}


