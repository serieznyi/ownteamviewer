/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.client;

import java.awt.Window;
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
    
    private ScreenReciever screenReciever;
    private ViewFrame viewFrame;
    
    public Client(String ip) {
        try {
            this.socketLog = new Socket(ip, this.portLog);
            MyConnector.log = new Log(MyConnector.main.getLogTextArea(), this.socketLog);
            MyConnector.log.show_message("Start client "+this.getCurrentIP());
            MyConnector.log.show_message("Trying to connect to the server with the address " + ip);
            this.socketViewer = new Socket(ip, this.portViewer);
            MyConnector.main.setMode("Client");
            MyConnector.log.show_message("Conected to server " + ip);
           
            MyConnector.main.showPanel("card4");
            
            myconnector.MyConnector.view.setVisible(true);
            MyConnector.main.fixedFrame();
            
            screenReciever = new ScreenReciever(
                            new ObjectInputStream(this.socketViewer.getInputStream()), MyConnector.view.getPaintPanel()
                                            );
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() { }
    
    @Override
    public void stopConnection() { 
        this.screenReciever.setContinueLoop(false);
    }
}
