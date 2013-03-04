/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnector.MyConnector;
import myconnector.log.Log;

/**
 *
 * @author serieznyi
 */
public class Network extends Thread{
    
    protected Socket socket;
    protected PrintWriter    out;
    protected BufferedReader in;
    
    public Network() {
        
    }
    
    public void send_message(String message)
    {
        this.out.println(message);
    }
    
    public void disconnect()
    {
        try {
            this.out.close();
            this.in.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
