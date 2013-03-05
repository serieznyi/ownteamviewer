/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnector.MyConnector;
import myconnector.log.Log;

/**
 *
 * @author serieznyi
 */
public class Client extends Network {

    public Client(String ip) {
        try {
            // System.out.println("Welcome to Client side");
            MyConnector.log.message("Start client", Log.LOG_CLIENT);
            MyConnector.log.message("Trying to connect to the server with the address " + ip, Log.LOG_CLIENT);
            this.socket = new Socket(ip, 4444);
            MyConnector.main.setMode("Client");

            MyConnector.log.message("Conected to server " + ip, Log.LOG_CLIENT);

            this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.out = new PrintWriter(this.socket.getOutputStream(), true);

        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        this.read_message();
    }
}