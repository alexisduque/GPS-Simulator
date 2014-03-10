/*
 * JAVA GPS Simulator
 * ISO Raid Project
 * 
 * From Perl oPhone GPS Simulator
 * Author: Alexis DUQUE - alexisd61@gmail.com - 2013
 *
 * Multhread Server listening on 2947 port. 10min Timeout. Build to test 
 * Coded to test GPS Simulator app
 *
 */
package gps.server;

import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Pattern;

public class ServerTK102 implements Runnable {
   
    // Pattern to parse TK102 position frame
    static private Pattern pattern = Pattern.compile(
            "\\[.\\d{10}.\\(\\p{Upper}+"
            + "(\\d{2})(\\d{2})(\\d{2})" + // Time (HHMMSS)
            "([AV])" + // Validity
            "(\\d{2})(\\d{2}\\.\\d{4})" + // Latitude (DDMM.MMMM)
            "([NS])"
            + "(\\d{3})(\\d{2}\\.\\d{4})" + // Longitude (DDDMM.MMMM)
            "([EW])"
            + "(\\d{3}\\.\\d{3})" + // Speed
            "(\\d{2})(\\d{2})(\\d{2})" + // Date (DDMMYY)
            "\\d+\\)");

    protected int serverPort = 2947;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;

    public ServerTK102(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        System.out.println("Collect Server Started on port " + this.serverPort);
        while (!isStopped()) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
            } catch (IOException e) {
                if (isStopped()) {
                    System.out.println("Server Stopped.");
                    return;
                }
                throw new RuntimeException(
                        "Error accepting client connection", e);
            }
            new Thread(
                    new WorkerRunnable(
                            clientSocket, "Collect Server")
            ).start();
        }
        System.out.println("Server Stopped.");
    }

    private synchronized boolean isStopped() {
        return this.isStopped;
    }

    public synchronized void stop() {
        this.isStopped = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            throw new RuntimeException("Error closing server", e);
        }
    }

    private void openServerSocket() {
        try {
            this.serverSocket = new ServerSocket(this.serverPort);
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port", e);
        }
    }

    public static void main(String[] args) {
        ServerTK102 server = new ServerTK102(2947);
        new Thread(server).start();

        try {
            Thread.sleep(600000 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Stopping Server");
        server.stop();
    }

    class WorkerRunnable implements Runnable {

        protected Socket clientSocket = null;
        protected String serverText = null;

        public WorkerRunnable(Socket clientSocket, String serverText) {
            this.clientSocket = clientSocket;
            this.serverText = serverText;
        }

        public void run() {
            try {

                InputStream input = clientSocket.getInputStream();
                OutputStream output = clientSocket.getOutputStream();

                BufferedReader in = new BufferedReader(new InputStreamReader(input));
                char[] received = new char[200];
                while (in.read(received) != -1) {

                    String trame = new String(received);
                    trame = trame.trim();
                    System.out.println(trame);
                    //Reception d'un JOIN
                    if (trame.startsWith("[!")) {
                        //On change simplemement le type de message
                        String ack = trame.replaceFirst("!", ".");
                        //Envoie l'ACK
                        output.write((ack).getBytes());
                        output.flush();
                        System.out.println("> " + ack);
                        //Reception d'un KEEP ALIVE
                    } else if (trame.startsWith("[%")) {
                        //On change simplemement le type de message
                        String ack = trame.replaceFirst("%", "&");
                        //Envoie l'ACK
                        output.write((ack).getBytes());
                        output.flush();
                        System.out.println("> " + ack);

                    } else if (trame.startsWith("[=")) {
                        // Traitement
                    }
                    received = new char[200];
                }
                output.close();
                input.close();
            } catch (IOException e) {
                //report exception somewhere.
                e.printStackTrace();
            }
        }
    }
}
