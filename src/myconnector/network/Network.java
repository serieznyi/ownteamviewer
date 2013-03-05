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
public class Network extends Thread {
    
    public static final String NET_CLOSE_CONNECTION = ":close";
    public static final String NET_ERROR_CLOSE = ":error";
    
    protected Socket socket;
    protected PrintWriter out;
    protected BufferedReader in;

    public Network() {
    }

    public void send_message(String message) {
        MyConnector.log.message(message, MyConnector.main.getMode().equals("Client")
                ? Log.LOG_CLIENT
                : Log.LOG_SERVER);
        this.out.println(message);
        this.out.flush();
    }
    
    public void read_message() {
        try {
            String input, out;
                
                while ((input = this.in.readLine()) != null) {
                    
                     if (input.equalsIgnoreCase(Network.NET_CLOSE_CONNECTION)) 
                     {
                            String message = MyConnector.main.getMode().equals("Client") 
                                    ? "Соединение прервано сервером!" 
                                    : "Соединение прервано клиентом!";
                            MyConnector.log.message(message , Log.LOG_SERVER);
                            
                        break;
                      }
                     
                     if (input.equalsIgnoreCase(Network.NET_ERROR_CLOSE)) 
                     {
                        MyConnector.log.message("Неожиданное завершение!", Log.LOG_SERVER);
                        break;
                     }
                     MyConnector.log.message(input, Log.LOG_CLIENT);
                }
        } catch (IOException ex) {
            Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            this.out.close();
            this.in.close();
            this.socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}