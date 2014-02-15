/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gps.simulator.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 * @author alex
 */
public class ListenThread extends Thread {

    protected final StandardGPS gps;
    protected final Socket sockThread;
    protected final String name;
    public boolean stop;
    private BufferedReader binput;

    public ListenThread(Socket Le_Socket, StandardGPS parent) {
        this.sockThread = Le_Socket;
        this.name = "SimulateurGPS";
        this.gps = parent;
        this.stop = false;
    }

    public synchronized void run() {
        try {
            testStop();
            String  _strCommande;
                try {
                    binput = new BufferedReader(new InputStreamReader(sockThread.getInputStream()));
                    while ((_strCommande = binput.readLine()) != null) {
                            testStop();
                            System.out.println("Tracker a reçu: " + _strCommande +"\n");
                            System.out.flush(); // on affiche tout ce qui est en attente dans le flux
                            try {
                                gps.filsBufer = "$OK:TRACK=1,5,1,0000\r\n";
                            } catch (Exception e) {
                                System.err.println(e);
                            }
                        }
                    } catch (IOException e) {
                        System.err.println("CLI input error !! ");
                        System.err.println(e);
                    }
                
        } catch (InterruptedException io) {
         
            System.out.println("CLI stop");
            System.err.println(io);
        }
        
    }

    public void testStop() throws InterruptedException {
        if (stop) {
            throw new InterruptedException();
        }
    }

}
