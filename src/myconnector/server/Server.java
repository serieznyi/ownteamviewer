/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author serieznyi
 */
public class Server extends Thread {

    private BufferedReader in = null;       // Поток для чтения
    private PrintWriter out = null;       // Поток для записи
    private ServerSocket servers = null;    //Сокет сервера
    private Socket fromclient = null;       // Сокет подключенного клиента

    public Server() throws IOException {

        super();

        System.out.println("Welcome to Server side");
        // create server socket
        try {
            servers = new ServerSocket(4444);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port 4444");
            System.exit(-1);
        }
        /*
         in  = new BufferedReader(new 
         InputStreamReader(fromclient.getInputStream()));
         out = new PrintWriter(fromclient.getOutputStream(),true);
         String         input,output;

         System.out.println("Wait for messages");
         while ((input = in.readLine()) != null) {
         if (input.equalsIgnoreCase("exit")) break;
         out.println("S ::: "+input);
         System.out.println(input);
         }
         out.close();
         in.close();
         fromclient.close();
         servers.close();*/

    }

    @Override
    public void run() {

        try {

            System.out.print("Waiting for a client...");
            this.fromclient = this.servers.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

    }
}
