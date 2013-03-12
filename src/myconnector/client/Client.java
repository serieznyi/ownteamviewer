/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnector.MyConnector;
import myconnector.log.Log;
import myconnector.network.Network;

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
            this.socketViewer = new Socket(ip, this.portViewer);
            this.socketViewer = new Socket(ip, this.portLog);
            MyConnector.main.setMode("Client");

            MyConnector.log.message("Conected to server " + ip, Log.LOG_CLIENT);
           
            ViewFrame viewframe = new ViewFrame();
            
            new ScreenReciever(
                    new ObjectInputStream(this.socketViewer.getInputStream()), viewframe.getRecieverPanel()
            );
            
           // this.keep_alive();
           // this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
          //  this.out = new PrintWriter(this.socket.getOutputStream(), true);

        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
       // this.read_message();
    }
}
