/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package myconnector.server;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.ServerSocket;
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
public class Server extends Network {

    private ServerSocket serverView;    
    private ServerSocket serverLog; 
    private Rectangle rectangle = null;
    private Robot robot = null;
    private ScreenCapture screenCapture;

    public Server() throws UnknownHostException {
        super();
       // MyConnector.log.message("Start server", Log.LOG_SERVER);

        // create server socket
        try {
            serverView = new ServerSocket(portViewer);
            serverLog = new ServerSocket(portLog);
            
            //Get default screen device
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();//Получение списка стройств системы
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice(); // Получить устройство вывода графики

            //Get screen dimensions
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            //Prepare Robot object
            robot = new Robot(gDev);
            
            this.start();
            
        } catch (AWTException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
          //  MyConnector.log.message("Couldn't listen to port" + portViewer, Log.LOG_SERVER);
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        try {
            //   System.out.println("IP:"+this.getCurrentIP());
       //     MyConnector.log.message("Waiting for a client...", Log.LOG_SERVER);
            MyConnector.main.setMode("Server");
            this.socketLog = this.serverLog.accept();
            MyConnector.log = new Log(MyConnector.main.getLogTextArea(), socketLog);
            
            MyConnector.log.show_message("Start server "+this.getCurrentIP());
            MyConnector.log.show_message("Waiting for a client...");
            
            this.socketViewer = this.serverView.accept();
            MyConnector.log.show_message("Client conected");

            MyConnector.main.showPanel("work_panel");

            this.screenCapture = new ScreenCapture(socketViewer, robot, rectangle);
            
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

    }
    
    @Override
    public void stopConnection() { 
        this.screenCapture.setContinueLoop(false);
    }
}
