/*
 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator
 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 */
package gps.simulator;

import java.net.*;
import java.io.*;

/**
 *
 * @author Alex
 */
public class Server extends Thread {

    final static int port = 9632;
    private Socket socket;

    public static void main(String[] args) {
        try {
            ServerSocket socketServeur = new ServerSocket(port);
            System.out.println("**** Server Starting ****");
            while (true) {
                Socket socketClient = socketServeur.accept();
                Server collect = new Server(socketClient);
                collect.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        listening();
    }

    public void listening() {
        try {
            String message = "";
            System.out.println("Connexion avec le client : " + socket.getInetAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream out = new PrintStream(socket.getOutputStream());
            
            message = in.readLine();
            System.out.println(message);
            
            out.println("ACK" + message);
            socket.close();
            
        } catch (IOException e) {
            System.out.println("Oups ! Error receiving data");
            e.printStackTrace();
        }
    }
}

