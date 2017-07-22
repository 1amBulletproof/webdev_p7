/** ThreadManager class
 * - Manage Server threads
 *
 * @author Brandon Tarney
 * @since 7/15/2017
 */

package com.brandontarney.server;
import java.io.*;
import java.net.*;

public class ThreadManager {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(20019);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 20019.");
            System.exit(1);
        }

        Socket clientSocket = null;
        //int threads = 0;
        //while (threads < 2) {
        while (true) {
            clientSocket = serverSocket.accept();
            Server thread = new Server(clientSocket);
            thread.start();
            //threads++;
        }
    }
}