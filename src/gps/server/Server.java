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

public class Server implements Runnable {

    protected int serverPort = 2947;
    protected ServerSocket serverSocket = null;
    protected boolean isStopped = false;
    protected Thread runningThread = null;

    public Server(int port) {
        this.serverPort = port;
    }

    public void run() {
        synchronized (this) {
            this.runningThread = Thread.currentThread();
        }
        openServerSocket();
        System.out.println("Collect Server Started.");
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
        Server server = new Server(2947);
        new Thread(server).start();

        try {
            Thread.sleep(600 * 1000);
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
                BufferedReader in = new BufferedReader(new InputStreamReader(input));;
                OutputStream output = clientSocket.getOutputStream();
                String trame = in.readLine();
                long time = System.currentTimeMillis();
                output.write(("ACK "
                        + this.serverText + " - "
                        + time
                        + "").getBytes());
                output.close();
                input.close();
                System.out.println("Request processed: " + time);
                System.out.println("Received: " + trame);
            } catch (IOException e) {
                //report exception somewhere.
                e.printStackTrace();
            }
        }
    }
}
