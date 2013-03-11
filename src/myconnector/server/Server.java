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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import myconnector.MyConnector;
import myconnector.client.ViewFrame;
import myconnector.log.Log;
import myconnector.network.Network;

/**
 *
 * @author serieznyi
 */
public class Server extends Network {

    private ServerSocket servers;    //Сокет сервера
    private int port = 4444;
    private Rectangle rectangle = null;
    private Robot robot = null;

    public Server() throws UnknownHostException {
        super();
        MyConnector.log.message("Start server", Log.LOG_SERVER);

        // create server socket
        try {
            servers = new ServerSocket(port);
            //  String ip = servers.getInetAddress().toString();
            MyConnector.main.setIP("172.27.242.201");
            
            //Get default screen device
            GraphicsEnvironment gEnv=GraphicsEnvironment.getLocalGraphicsEnvironment();//Получение списка стройств системы
            GraphicsDevice gDev=gEnv.getDefaultScreenDevice(); // Получить устройство вывода графики

            //Get screen dimensions
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            rectangle = new Rectangle(dim);

            //Prepare Robot object
            robot = new Robot(gDev);
            
            
        } catch (AWTException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            MyConnector.log.message("Couldn't listen to port" + port, Log.LOG_SERVER);
            System.exit(-1);
        }
    }

    @Override
    public void run() {
        try {
            //   System.out.println("IP:"+this.getCurrentIP());
            MyConnector.log.message("Waiting for a client...", Log.LOG_SERVER);
            MyConnector.main.setMode("Server");
            this.socket = this.servers.accept();
            MyConnector.log.message("Client connected", Log.LOG_SERVER);
        //    System.out.println(this.socket.getKeepAlive());
            
           // this.keep_alive();
            MyConnector.main.showPanel("working_panel");

           // this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
           // this.out = new PrintWriter(this.socket.getOutputStream(), true);
            
            new ScreenCapture(socket, robot, rectangle);
            
           // this.read_message();
            
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

    }

    public String getCurrentIP() {
        String result = null;
        try {
            BufferedReader reader = null;
            try {
                URL url = new URL("http://myip.by/");
                InputStream inputStream = null;
                inputStream = url.openStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder allText = new StringBuilder();
                char[] buff = new char[1024];

                int count = 0;
                while ((count = reader.read(buff)) != -1) {
                    allText.append(buff, 0, count);
                }
                // Строка содержащая IP имеет следующий вид 
                // <a href="whois.php?127.0.0.1">whois 127.0.0.1</a> 
                Integer indStart = allText.indexOf("\">whois ");
                Integer indEnd = allText.indexOf("</a>", indStart);

                String ipAddress = new String(allText.substring(indStart + 8, indEnd));
                if (ipAddress.split("\\.").length == 4) { // минимальная (неполная) 
                    //проверка что выбранный текст является ip адресом.
                    result = ipAddress;
                }
            } catch (MalformedURLException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
